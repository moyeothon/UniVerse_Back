package com.moyeothon.universe.repository;

import com.moyeothon.universe.domain.Record;
import com.moyeothon.universe.domain.dto.RecordRequestDto;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

  public Page<Record> findAllByMovieId(Long movieId, Pageable pageable);
  public Page<Record> findAllByOwnerId(Long userId, Pageable pageable);
  public Optional<Record> findByOwnerIdAndMovieId(Long userId, Long movieId);
  public void deleteByOwnerIdAndMovieId(Long userId, Long movieId);
  public Record save(RecordRequestDto.SaveRecord recordDto);

}
