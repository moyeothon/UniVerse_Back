package com.moyeothon.universe.controller;

import com.moyeothon.universe.apiPayload.ApiResponse;
import com.moyeothon.universe.apiPayload.code.status.SuccessStatus;
import com.moyeothon.universe.service.MovieService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

  private final MovieService movieService;

  @GetMapping
  public ApiResponse<?> getMovieList(Pageable pageable) {
    return ApiResponse.of(SuccessStatus.MOVIE_GET_ALL, movieService.getMovieList(pageable));
  }

  @GetMapping("/{id}")
  public ApiResponse<?> getMovie(@PathVariable("id") Long id) {
    return ApiResponse.of(SuccessStatus.MOVIE_GET_ALL, movieService.getMovie(id));
  }
}
