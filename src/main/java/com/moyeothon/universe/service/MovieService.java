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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {

  private final MovieRepository movieRepository;
  private final RecordRepository recordRepository;

  public Page<MovieResponseDto.GetInfoDto> getMovieList(Pageable pageable) {
    Page<Movie> all = movieRepository.findAll(pageable);
    return all.map(movie -> MovieResponseDto.GetInfoDto.builder()
        .id(movie.getId())
        .title(movie.getTitle())
        .directors(movie.getDirectors())
        .actors(movie.getActors())
        .posterUrl(movie.getPosterUrl())
        .subtitle(movie.getSubtitle())
        .releaseDate(movie.getReleaseDate())
        .recommendCount(recordRepository.countByMovieIdAndAndRecommend(movie.getId(), true))
        .build());
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

  public Page<MovieResponseDto.GetInfoDto> searchMovie(Pageable pageable, String keyword) {

    Page<Movie> all = movieRepository.findByTitleContaining(keyword, pageable);

    return all.map(movie -> MovieResponseDto.GetInfoDto.builder()
        .id(movie.getId())
        .title(movie.getTitle())
        .directors(movie.getDirectors())
        .actors(movie.getActors())
        .posterUrl(movie.getPosterUrl())
        .subtitle(movie.getSubtitle())
        .releaseDate(movie.getReleaseDate())
        .recommendCount(recordRepository.countByMovieIdAndAndRecommend(movie.getId(), true))
        .build());
  }
}
