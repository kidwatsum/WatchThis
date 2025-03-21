package com.example.moviesbackend.repository;

import com.example.moviesbackend.model.Movie;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    Movie findByGenre(String genre);
    List<Movie> findByGenreAndYearBetween(String genre,String startYear,String endYear);
    Movie findByYearBetween(String startYear,String endYear);
    Movie findByCastContainsIgnoreCase(String cast);
    Movie findByGenreAndCastContainsIgnoreCase(String genre,String cast);

}
