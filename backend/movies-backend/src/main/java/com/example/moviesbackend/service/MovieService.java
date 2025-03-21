package com.example.moviesbackend.service;

import com.example.moviesbackend.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> listAll();
    Movie findMovieById(String movieId);
    Movie addMovie(List<String> cast,String description,String director,String duration,String genre,String image,String rating,String title,String trailer,String year);
    Movie updateMovie(String movieId,List<String> cast,String description,String director,String duration,String genre,String image,String rating,String title,String trailer,String year);
    void deleteMovie(String movieId);
    List<Movie> getMoviesByGenres(List<String> categories);
    List<Movie> getMoviesByGenresAndYears(List<String> genres,String startYear,String endYear);
    List<String> getGenres();
    List<String> getYears();
}
