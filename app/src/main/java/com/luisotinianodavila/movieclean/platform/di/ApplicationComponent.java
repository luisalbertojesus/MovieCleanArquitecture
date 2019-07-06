package com.luisotinianodavila.movieclean.platform.di;

import com.luisotinianodavila.movieclean.platform.view.activity.MovieDetail;
import com.luisotinianodavila.movieclean.platform.view.activity.MovieList;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, MoviesModule.class})
public interface ApplicationComponent {
    void inject(MovieList movieList);
    void inject(MovieDetail movieDetail);
}
