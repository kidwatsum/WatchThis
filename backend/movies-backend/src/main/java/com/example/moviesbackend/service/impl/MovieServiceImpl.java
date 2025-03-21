package com.example.moviesbackend.service.impl;

import com.example.moviesbackend.model.Movie;
import com.example.moviesbackend.repository.MovieRepository;
import com.example.moviesbackend.service.MovieService;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.tokens.AliasToken;

import java.util.*;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> listAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findMovieById(String movieId) {
        return movieRepository.findById(movieId).orElseThrow();
    }

    @Override
    public Movie addMovie(List<String> cast, String description, String director, String duration, String genre, String image, String rating, String title, String trailer, String year) {
        Movie movie=new Movie(cast,description,director,duration,genre,image,rating,title,trailer,year);
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(String movieId, List<String> cast, String description, String director, String duration, String genre, String image, String rating, String title, String trailer, String year) {
        Movie movie= this.findMovieById(movieId);
        movie.setCast(cast);
        movie.setDescription(description);
        movie.setDirector(director);
        movie.setDuration(duration);
        movie.setGenre(genre);
        movie.setImage(image);
        movie.setRating(rating);
        movie.setTitle(title);
        movie.setYear(year);
        return movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(String movieId) {
        movieRepository.deleteById(movieId);
    }

    @Override
    public List<Movie> getMoviesByGenres(List<String> genres) {
        List<Movie> movies=new ArrayList<>();
        genres.forEach(genre->{
            movies.add(movieRepository.findByGenre(genre));
        });
        return movies;
    }

    @Override
    public List<Movie> getMoviesByGenresAndYears(List<String> genres, String startYear, String endYear) {
        List<Movie> movies=new ArrayList<>();
        genres.forEach(genre->{
            movies.addAll(movieRepository.findByGenreAndYearBetween(genre,startYear,endYear));
        });
        return movies;
    }

    @Override
    public List<String> getGenres(){
        List<String> genres=new ArrayList<>();
        this.listAll().forEach(movie -> {
            if(movie.getGenre()!=null && !genres.contains(movie.getGenre())){
                genres.add(movie.getGenre());
            }
        });

        Collections.sort(genres);
                return genres;
    }

    @Override
    public List<String> getYears() {
        List<String> years=new ArrayList<>();
        this.listAll().forEach(movie -> {
            if(movie.getYear()!=null && !years.contains(movie.getYear())){
                years.add(movie.getYear());
            }
        });
        Collections.sort(years);
        List<String> intervals=new ArrayList<>();
        int startYear=Integer.parseInt(years.get(0));
        int endYear=startYear+9;

        String startInterval=startYear+"-"+endYear;

        intervals.add(startInterval);

        for (int i=1;i<years.size();i++){
            int currentYear=Integer.parseInt(years.get(i));

            if(currentYear>endYear){
                startYear=currentYear;
                endYear=startYear+9;
                startInterval=startYear+"-"+endYear;
                intervals.add(startInterval);
            }
        }

        return intervals;
    }
}
