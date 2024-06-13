package org.example.repository;

import org.example.dto.MovieDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

public interface MoviesRepository extends ListCrudRepository<MovieDTO, Long> {

    @Query("Select m FROM movies  m WHERE m.title = :name")
    MovieDTO findByName(@Param("name") String name);
}
