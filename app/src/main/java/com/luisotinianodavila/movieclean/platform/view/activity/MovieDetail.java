package com.luisotinianodavila.movieclean.platform.view.activity;

import android.app.Activity;
import android.content.Intent;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.luisotinianodavila.movieclean.R;
import com.luisotinianodavila.movieclean.domain.model.Movie;
import com.luisotinianodavila.movieclean.platform.di.App;
import com.luisotinianodavila.movieclean.platform.view.base.BaseActivity;
import com.luisotinianodavila.movieclean.presentation.presenters.DetailMoviePresenter;
import com.luisotinianodavila.movieclean.presentation.presenters.MovieMVP;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;


public class MovieDetail extends BaseActivity implements MovieMVP.ViewMovieDetail {

    public static final String EXTRA_MOVIE_ID = "MOVIE_ID";

    @Inject
    DetailMoviePresenter presenter;

    ImageView imagen;
    TextView titulodetail;
    TextView popularity;
    TextView voteAverage;
    TextView vote_count;
    TextView releaseDate;
    TextView overview;

    @Override
    public void initElementos(){
        imagen = findViewById(R.id.image_movie);
        titulodetail = findViewById(R.id.tv_title);
        popularity = findViewById(R.id.tv_popularity);
        voteAverage = findViewById(R.id.tv_vote_average);
        vote_count = findViewById(R.id.tv_vote_count);
        releaseDate = findViewById(R.id.text_releaseDate);
        overview = findViewById(R.id.tv_overview);
    }

    @Override
    public void initView() {
        //Toast.makeText(this, "movieId -> " + getMovieFlagKey(), Toast.LENGTH_SHORT).show();

        this.initializeDagger();
        this.initializePresenter();
    }

    public static Intent launch(Activity context, int movieId) {
        Intent callingIntent = new Intent(context, MovieDetail.class);
        callingIntent.putExtra(EXTRA_MOVIE_ID, movieId);
        return callingIntent;
    }

    private void initializeDagger() {
        ((App) getApplication()).getComponent().inject(this);
    }

    private void initializePresenter() {
        presenter.setView(this);
        presenter.viewReady(getMovieFlagKey());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    private int getMovieFlagKey() {
        return getIntent().getIntExtra(EXTRA_MOVIE_ID, -1);
    }

    @Override
    public void mostrarMovie(Movie movie) {
        titulodetail.setText(movie.getTitle());
        popularity.setText(String.valueOf(movie.getPopularity()));
        voteAverage.setText(String.valueOf(movie.getVoteAverage()));
        vote_count.setText(String.valueOf(movie.getVote_count()));
        releaseDate.setText(movie.getReleaseDate());
        overview.setText(movie.getOverview());
    }

    @Override
    public void mensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mostrarProgressBar() {

    }

    @Override
    public void ocultarProgressBar() {

    }

    @Override
    public void descargarPosterPath(String posterPath) {
        Picasso.with(this)
                .load(posterPath)
                .placeholder(R.drawable.ic_launcher_background)
                .into(imagen);
    }

    @Override
    public void refresh() {
        this.initializePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_movie_detail;
    }
}
