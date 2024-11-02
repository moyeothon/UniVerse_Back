package com.moyeothon.universe.repository;

import com.moyeothon.universe.domain.Movie;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Page<Movie> findAll(Pageable pageable);
    Page<Movie> findByTitleContaining(String title, Pageable pageable);
    Optional<Movie> findByTitle(String title);
    Optional<Movie> findBySubtitle(String subtitle);
    Optional<Movie> findByActors(String actors);
    Optional<Movie> findByDirectors(String directors);

    Page<Movie> findByTitleContaining(String keyword, Pageable pageable);
}
