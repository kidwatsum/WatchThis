package com.example.moviesbackend.controller;

import com.example.moviesbackend.model.Movie;
import com.example.moviesbackend.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:3000")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/genres")
    public List<String> genres(){
        return movieService.getGenres();
    }
    @GetMapping("/years")
    public List<String> years(){
        return movieService.getYears();
    }
    @PostMapping("/searcher")
    public List<Movie> getMovies(@RequestParam String genre,
                                 @RequestParam String year){
        System.out.println("Genre:"+genre);
        System.out.println("Year:"+year);

        String [] parts=year.split("-");
        List<String> genres=new ArrayList<>();
        genres.add(genre);

        return movieService.getMoviesByGenresAndYears(genres,parts[0],parts[1]);

    }
}
