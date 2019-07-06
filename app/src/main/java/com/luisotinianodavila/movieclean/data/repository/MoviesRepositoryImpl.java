package com.luisotinianodavila.movieclean.data.repository;

import com.luisotinianodavila.movieclean.data.entity.mapper.EntityDataMapper;
import com.luisotinianodavila.movieclean.data.source.remote.MoviesDataRemote;
import com.luisotinianodavila.movieclean.domain.model.Movie;

import java.util.List;

import javax.inject.Inject;

public class MoviesRepositoryImpl implements MoviesRepository {

    private MoviesDataRemote dataRemote;
    private EntityDataMapper entityDataMapper;

    @Inject
    public MoviesRepositoryImpl(MoviesDataRemote dataRemote, EntityDataMapper entityDataMapper){
        this.dataRemote = dataRemote;
        this.entityDataMapper = entityDataMapper;
    }

    @Override
    public Movie getMovie(int movieId) throws Exception {
        return entityDataMapper.transform_mapper(dataRemote.getMovie(movieId));
    }

    @Override
    public List<Movie> getMovies() throws Exception {
        return entityDataMapper.transform_mapper(dataRemote.getAllMovies());
    }

    @Override
    public List<Movie> getMoviesSearch(String search) throws Exception {
        return entityDataMapper.transform_mapper(dataRemote.getSearchAllMovies(search));
    }
}
