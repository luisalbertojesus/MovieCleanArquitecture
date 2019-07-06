package com.luisotinianodavila.movieclean.data.entity.mapper;

import com.luisotinianodavila.movieclean.data.entity.MovieEntity;
import com.luisotinianodavila.movieclean.data.entity.detail.DetailEntity;
import com.luisotinianodavila.movieclean.domain.model.Movie;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EntityDataMapper {
    public static final String BASE_URL_IMAGE = "https://image.tmdb.org/t/p/w500";

    @Inject
    public EntityDataMapper(){}

    public List<Movie> transform_mapper(List<MovieEntity> movieEntityList){
        List<Movie> movieList = new ArrayList<>();
        for(MovieEntity movieEntity : movieEntityList){
            Movie movie = transform_mapper(movieEntity);
            if(movie != null){
                movieList.add(movie);
            }
        }
        return movieList;
    }

    public Movie transform_mapper(MovieEntity movieEntity){
        Movie movie = null;
        if(movieEntity != null){
            movie = new Movie(movieEntity.getId(),
                    movieEntity.getTitle(),
                    movieEntity.getPopularity(),
                    movieEntity.getVoteAverage(),
                    movieEntity.getVoteCount(),
                    movieEntity.getReleaseDate(),
                    movieEntity.getOverview(),
                    completarUrlImagen(movieEntity.getBackdropPath()));
        }
        return movie;
    }

    public Movie transform_mapper(DetailEntity detailEntity){
        Movie movie = null;
        if(detailEntity != null){
            movie = new Movie(detailEntity.getId(),
                    detailEntity.getTitle(),
                    detailEntity.getPopularity(),
                    detailEntity.getVoteAverage(),
                    detailEntity.getVoteCount(),
                    detailEntity.getReleaseDate(),
                    detailEntity.getOverview(),
                    completarUrlImagen(detailEntity.getBackdropPath()));
        }
        return movie;
    }

    private String completarUrlImagen(String path){
        return BASE_URL_IMAGE + path;
    }
}
