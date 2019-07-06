package com.luisotinianodavila.movieclean.platform.di;

import android.app.Application;

public class App extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate(){
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .moviesModule(new MoviesModule())
                .build();
    }

    public ApplicationComponent getComponent(){
        return applicationComponent;
    }
}
