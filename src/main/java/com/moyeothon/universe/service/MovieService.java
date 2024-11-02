package com.moyeothon.universe.service;

import com.moyeothon.universe.apiPayload.code.status.ErrorStatus;
import com.moyeothon.universe.apiPayload.exception.handler.MemberHandler;
import com.moyeothon.universe.domain.Movie;
import com.moyeothon.universe.domain.Record;
import com.moyeothon.universe.domain.dto.MovieResponseDto;
import com.moyeothon.universe.repository.MovieRepository;
import com.moyeothon.universe.repository.RecordRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

  private final MovieRepository movieRepository;
  private final RecordRepository recordRepository;

  public Page<MovieResponseDto.GetInfo> getMovieList(Pageable pageable) {

    Page<Movie> movies = movieRepository.findAll(pageable);

    // Movie를 MovieResponseDto.GetInfo로 매핑
    List<MovieResponseDto.GetInfo> movieDtos = movies.getContent()
        .stream()
        .map(movie -> new MovieResponseDto.GetInfo(movie, recordRepository.countByMovieIdAndRecommend(
            movie.getId(), true))) // MovieResponseDto.GetInfo 생성자 또는 변환 메서드 필요
        .collect(Collectors.toList());

    // PageImpl을 이용하여 Page<MovieResponseDto.GetInfo>로 변환
    return new PageImpl<>(movieDtos, pageable, movies.getTotalElements());

  }

  public Movie getMovie(Long id) {
    return movieRepository.findById(id)
        .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
  }

  public List<String> getMovieRecordsSummary(Long id) {
    Pageable pageable = Pageable.ofSize(5);
    Page<Record> byMovieId = recordRepository.findByMovieId(id, pageable);
    return byMovieId.getContent().stream()
        .map(Record::getSummary)
        .collect(Collectors.toList());
  }

  public Page<MovieResponseDto.GetInfo> searchMovie(Pageable pageable, String keyword) {
    // MovieRepository에서 Page<Movie> 가져오기
    Page<Movie> movies = movieRepository.findByTitleContaining(keyword, pageable);

    // Movie를 MovieResponseDto.GetInfo로 매핑
    List<MovieResponseDto.GetInfo> movieDtos = movies.getContent()
        .stream()
        .map(movie -> new MovieResponseDto.GetInfo(movie, recordRepository.countByMovieIdAndRecommend(
            movie.getId(), true))) // MovieResponseDto.GetInfo 생성자 또는 변환 메서드 필요
        .collect(Collectors.toList());

    // PageImpl을 이용하여 Page<MovieResponseDto.GetInfo>로 변환
    return new PageImpl<>(movieDtos, pageable, movies.getTotalElements());
  }
}
