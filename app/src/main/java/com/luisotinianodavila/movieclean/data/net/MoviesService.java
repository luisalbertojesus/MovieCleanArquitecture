package com.luisotinianodavila.movieclean.data.net;

import com.luisotinianodavila.movieclean.data.entity.Results;
import com.luisotinianodavila.movieclean.data.entity.detail.DetailEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesService {

    @GET("movie/{movie_id}")
    Call<DetailEntity> getMovieDetail(@Path("movie_id") int movie_id);

    @GET("movie/popular")
    Call<Results> getMoviesList();

    @GET("search/movie")
    Call<Results> getMoviesListSearch(@Query("query") String search);
}
