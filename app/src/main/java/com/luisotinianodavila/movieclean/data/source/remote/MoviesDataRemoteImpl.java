package com.luisotinianodavila.movieclean.data.source.remote;

import com.luisotinianodavila.movieclean.data.entity.MovieEntity;
import com.luisotinianodavila.movieclean.data.entity.Results;
import com.luisotinianodavila.movieclean.data.entity.detail.DetailEntity;
import com.luisotinianodavila.movieclean.data.exception.NetworkConnectionException;
import com.luisotinianodavila.movieclean.data.exception.ServiceException;
import com.luisotinianodavila.movieclean.data.net.MoviesService;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

public class MoviesDataRemoteImpl implements MoviesDataRemote{

    MoviesService service;

    @Inject
    public MoviesDataRemoteImpl(MoviesService service){
        this.service = service;
    }

    @Override
    public DetailEntity getMovie(int movieId) throws Exception {
        try {
            Response<DetailEntity> response = service.getMovieDetail(movieId).execute();
            if(response.isSuccessful()) return response.body();
            else throw new ServiceException();
        } catch (IOException exception) {
            throw new NetworkConnectionException();
        }
    }

    @Override
    public List<MovieEntity> getAllMovies() throws Exception {
        try {
            Response<Results> response = service.getMoviesList().execute();
            if(response.isSuccessful()) return response.body().getResults();
            else throw new ServiceException();
        } catch (IOException exception) {
            throw new NetworkConnectionException();
        }
    }

    @Override
    public List<MovieEntity> getSearchAllMovies(String search) throws Exception {
        try {
            Response<Results> response = service.getMoviesListSearch(search).execute();
            if(response.isSuccessful()) return response.body().getResults();
            else throw new ServiceException();
        } catch (IOException exception) {
            throw new NetworkConnectionException();
        }
    }
}
