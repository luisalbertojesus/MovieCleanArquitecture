package com.luisotinianodavila.movieclean.data.source.remote;

import com.luisotinianodavila.movieclean.data.entity.MovieEntity;
import com.luisotinianodavila.movieclean.data.entity.detail.DetailEntity;

import java.util.List;

public interface MoviesDataRemote {
    DetailEntity getMovie(int movieId) throws Exception;
    List<MovieEntity> getAllMovies() throws Exception;
    List<MovieEntity> getSearchAllMovies(String search) throws Exception;
}
