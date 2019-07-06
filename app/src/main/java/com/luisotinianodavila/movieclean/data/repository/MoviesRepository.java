package com.luisotinianodavila.movieclean.data.repository;

import com.luisotinianodavila.movieclean.domain.model.Movie;

import java.util.List;

public interface MoviesRepository {
    Movie getMovie(int movieId) throws Exception;
    List<Movie> getMovies() throws Exception;
    List<Movie> getMoviesSearch(String search) throws Exception;
}
