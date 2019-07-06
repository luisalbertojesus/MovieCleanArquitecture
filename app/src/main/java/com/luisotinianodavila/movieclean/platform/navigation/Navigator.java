package com.luisotinianodavila.movieclean.platform.navigation;

import android.app.Activity;
import android.content.Intent;

import com.luisotinianodavila.movieclean.platform.view.activity.MovieDetail;

public class Navigator {
    public void navigateToMovieDetails(Activity activity, int movieId) {
        if (activity != null) {
            Intent intentToLaunch = MovieDetail.launch(activity, movieId);
            activity.startActivity(intentToLaunch);
        }
    }
}
