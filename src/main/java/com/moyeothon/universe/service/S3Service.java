package com.moyeothon.universe.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.moyeothon.universe.apiPayload.code.status.ErrorStatus;
import com.moyeothon.universe.apiPayload.exception.handler.FileUploadHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3Service {

  private final AmazonS3 amazonS3;
  private Set<String> uploadedFileNames = new HashSet<>();
  private Set<Long> uploadedFileSizes = new HashSet<>();

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  @Value("${spring.servlet.multipart.max-file-size}")
  private String maxSizeString;

  // 여러장의 파일 저장
  public List<String> saveFiles(List<MultipartFile> multipartFiles) {
    List<String> uploadedUrls = new ArrayList<>();

    for (MultipartFile multipartFile : multipartFiles) {

      if (isDuplicate(multipartFile)) {
        throw new FileUploadHandler(ErrorStatus.FILE_DUPLICATE);
      }

      String uploadedUrl = saveFile(multipartFile);
      uploadedUrls.add(uploadedUrl);
    }

    clear();
    return uploadedUrls;
  }

  // 파일 삭제
  public void deleteFile(String fileUrl) {
    String[] urlParts = fileUrl.split("/");
    String fileBucket = urlParts[2].split("\\.")[0];

    if (!fileBucket.equals(bucket)) {
      throw new FileUploadHandler(ErrorStatus.FILE_NOT_FOUND);
    }

    String objectKey = String.join("/", Arrays.copyOfRange(urlParts, 3, urlParts.length));

    if (!amazonS3.doesObjectExist(bucket, objectKey)) {
      throw new FileUploadHandler(ErrorStatus.FILE_NOT_FOUND);
    }

    try {
      amazonS3.deleteObject(bucket, objectKey);
    } catch (AmazonS3Exception e) {
      log.error("File delete fail : " + e.getMessage());
      throw new FileUploadHandler(ErrorStatus.FILE_FAIL_DELETE);
    } catch (SdkClientException e) {
      log.error("AWS SDK client error : " + e.getMessage());
      throw new FileUploadHandler(ErrorStatus.FILE_FAIL_DELETE);
    }

    log.info("File delete complete: " + objectKey);
  }

  // 단일 파일 저장
  public String saveFile(MultipartFile file) {
    String randomFilename = generateRandomFilename(file);

    log.info("File upload started: " + randomFilename);

    ObjectMetadata metadata = new ObjectMetadata();
    metadata.setContentLength(file.getSize());
    metadata.setContentType(file.getContentType());

    try {
      amazonS3.putObject(bucket, randomFilename, file.getInputStream(), metadata);
    } catch (AmazonS3Exception e) {
      log.error("Amazon S3 error while uploading file: " + e.getMessage());
      throw new FileUploadHandler(ErrorStatus.FILE_FAIL_UPLOAD);
    } catch (SdkClientException e) {
      log.error("AWS SDK client error while uploading file: " + e.getMessage());
      throw new FileUploadHandler(ErrorStatus.FILE_FAIL_UPLOAD);
    } catch (IOException e) {
      log.error("IO error while uploading file: " + e.getMessage());
      throw new FileUploadHandler(ErrorStatus.FILE_FAIL_UPLOAD);
    }

    log.info("File upload completed: " + randomFilename);

    return amazonS3.getUrl(bucket, randomFilename).toString();
  }

  // 요청에 중복되는 파일 여부 확인
  private boolean isDuplicate(MultipartFile multipartFile) {
    String fileName = multipartFile.getOriginalFilename();
    Long fileSize = multipartFile.getSize();

    if (uploadedFileNames.contains(fileName) && uploadedFileSizes.contains(fileSize)) {
      return true;
    }

    uploadedFileNames.add(fileName);
    uploadedFileSizes.add(fileSize);

    return false;
  }

  private void clear() {
    uploadedFileNames.clear();
    uploadedFileSizes.clear();
  }

  // 랜덤파일명 생성 (파일명 중복 방지)
  private String generateRandomFilename(MultipartFile multipartFile) {
    String originalFilename = multipartFile.getOriginalFilename();
    String fileExtension = validateFileExtension(originalFilename);
    String randomFilename = UUID.randomUUID() + "." + fileExtension;
    return randomFilename;
  }

  // 파일 확장자 체크
  private String validateFileExtension(String originalFilename) {
    String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
    List<String> allowedExtensions = Arrays.asList("jpg", "png", "gif", "jpeg");

    if (!allowedExtensions.contains(fileExtension)) {
      throw new FileUploadHandler(ErrorStatus.FILE_NOT_SUPPORTED_EXTENSION);
    }
    return fileExtension;
  }

}
