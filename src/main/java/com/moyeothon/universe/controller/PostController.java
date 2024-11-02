package com.moyeothon.universe.controller;

import com.moyeothon.universe.domain.dto.PostInfoResDto;
import com.moyeothon.universe.domain.dto.PostListResDto;
import com.moyeothon.universe.domain.dto.PostSaveReqDto;
import com.moyeothon.universe.domain.dto.PostUpdateReqDto;
import com.moyeothon.universe.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService=postService;
    }

    //글 등록
    @PostMapping("/posts")
    public ResponseEntity<String> postSave(@RequestBody PostSaveReqDto postSaveReqDto) {
        postService.postSave(postSaveReqDto);
        return new ResponseEntity<>("게시글이 등록되었습니다.", HttpStatus.CREATED);
    }

    //작성자에 따른 게시글 조회
    @GetMapping("/member/{memberId}")
    public ResponseEntity<PostListResDto>myPostFindAll(@PathVariable("memberId") Long memberId){
        PostListResDto postListResDto = postService.postFindMember(memberId);
        return new ResponseEntity<>(postListResDto, HttpStatus.OK);
    }

    // 모든 게시글 조회
    @GetMapping
    public ResponseEntity<List<PostInfoResDto>> getAllPosts() {
        List<PostInfoResDto> posts = postService.findAllPosts();
        return ResponseEntity.ok(posts);
    }

    // 특정 게시글 상세 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostInfoResDto> getPostById(@PathVariable Long postId) {
        PostInfoResDto post = postService.findPostById(postId);
        return ResponseEntity.ok(post);
    }

    //글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<String>postUpdate(@PathVariable("postId") Long postId,
                                            @RequestBody PostUpdateReqDto postUpdateReqDto){
        postService.postUpdate(postId, postUpdateReqDto);
        return new ResponseEntity<>("게시글이 수정되었습니다.", HttpStatus.OK);
    }

    //글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<String> postDelete (@PathVariable("postId") Long postId){
        postService.postDelete(postId);
        return new ResponseEntity<>("게시물이 삭제되었습니다.", HttpStatus.OK);
    }



}

