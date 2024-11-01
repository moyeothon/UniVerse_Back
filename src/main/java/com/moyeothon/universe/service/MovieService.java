package com.moyeothon.universe.service;

import com.moyeothon.universe.apiPayload.code.status.ErrorStatus;
import com.moyeothon.universe.apiPayload.exception.handler.MemberHandler;
import com.moyeothon.universe.domain.Movie;
import com.moyeothon.universe.domain.Record;
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

  public Page<Movie> getMovieList(Pageable pageable) {
    return movieRepository.findAll(pageable);
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
}
