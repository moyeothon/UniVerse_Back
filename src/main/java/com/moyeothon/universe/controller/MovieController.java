package com.moyeothon.universe.controller;

import com.moyeothon.universe.apiPayload.ApiResponse;
import com.moyeothon.universe.apiPayload.code.status.SuccessStatus;
import com.moyeothon.universe.domain.Movie;
import com.moyeothon.universe.domain.dto.MovieResponseDto;
import com.moyeothon.universe.service.MovieService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

  private final MovieService movieService;

  @GetMapping
  public ApiResponse<?> getMovieList(Pageable pageable,
      @RequestParam(value = "keyword", required = false) String keyword) {
    Page<MovieResponseDto.GetInfoDto> moviePage = null;
    if (keyword == null) {
      moviePage = movieService.getMovieList(pageable);
    }
    else {
      moviePage = movieService.searchMovie(pageable, keyword);
    }

    return ApiResponse.of(SuccessStatus.MOVIE_GET_ALL, moviePage);
  }

  @GetMapping("/{id}")
  public ApiResponse<?> getMovie(@PathVariable("id") Long id) {
    Movie movie = movieService.getMovie(id);
    return ApiResponse.of(SuccessStatus.MOVIE_GET_ALL, movie);
  }

  @GetMapping("/{id}/records")
  public ApiResponse<?> getMovieRecordsSummary(@PathVariable("id") Long id) {
    List<String> movieRecordsSummary = movieService.getMovieRecordsSummary(id);
    return ApiResponse.of(SuccessStatus.MOVIE_GET_RECORD, movieRecordsSummary);
  }
}