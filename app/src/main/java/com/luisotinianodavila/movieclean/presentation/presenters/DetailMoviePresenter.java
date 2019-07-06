package com.luisotinianodavila.movieclean.presentation.presenters;

import com.luisotinianodavila.movieclean.domain.Observer;
import com.luisotinianodavila.movieclean.domain.interactor.GetMovie;
import com.luisotinianodavila.movieclean.domain.interactor.UseCase;
import com.luisotinianodavila.movieclean.domain.interactor.UseCaseFactory;
import com.luisotinianodavila.movieclean.domain.model.Movie;
import com.luisotinianodavila.movieclean.presentation.BasePresenter;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

public class DetailMoviePresenter extends BasePresenter implements MovieMVP.PresenterMovieDetail{

    private WeakReference<MovieMVP.ViewMovieDetail> view;
    UseCaseFactory useCaseFactory;

    @Inject
    public DetailMoviePresenter(UseCaseFactory useCaseFactory){
        this.useCaseFactory = useCaseFactory;
    }

    @Override
    public void setView(MovieMVP.ViewMovieDetail view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void viewReady(int movieId) {
        view.get().mostrarProgressBar();
        UseCase uc = useCaseFactory.getMovie();

        addDisposable(uc.execute(new MoviesObserver(), new GetMovie.Params(movieId)));
    }

    private final class MoviesObserver extends Observer<Movie> {
        @Override
        public void onNext(Movie movie) {
            MovieMVP.ViewMovieDetail v = view.get();
            if(v!=null) {
                view.get().ocultarProgressBar();
                v.mostrarMovie(movie);
                v.descargarPosterPath(movie.getPosterPath());
            }
        }

        @Override
        public void onError(Throwable e) {
            view.get().ocultarProgressBar();
            view.get().mensaje(e.getMessage());
        }

        @Override
        public void onComplete() {
            view.get().ocultarProgressBar();
            view.get().mensaje("Movie");
            super.onComplete();
        }
    }
}
