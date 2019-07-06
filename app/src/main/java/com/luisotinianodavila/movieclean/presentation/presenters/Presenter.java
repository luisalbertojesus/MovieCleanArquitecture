package com.luisotinianodavila.movieclean.presentation.presenters;

import androidx.appcompat.widget.SearchView;

import com.luisotinianodavila.movieclean.domain.Observer;
import com.luisotinianodavila.movieclean.domain.interactor.UseCase;
import com.luisotinianodavila.movieclean.domain.interactor.UseCaseFactory;
import com.luisotinianodavila.movieclean.domain.model.Movie;
import com.luisotinianodavila.movieclean.presentation.BasePresenter;
import com.luisotinianodavila.movieclean.presentation.MovieCellView;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

public class Presenter extends BasePresenter implements MovieMVP.Presenter{

    private WeakReference<MovieMVP.View> view;
    UseCaseFactory useCaseFactory;

    private List<Movie> movieList;

    @Inject
    public Presenter(UseCaseFactory useCaseFactory){
        this.useCaseFactory = useCaseFactory;
    }

    void saveMovies(List<Movie> movieList){
        this.movieList = movieList;
    }

    public Movie getMovie(int position){
        return movieList.get(position);
    }

    public boolean listIsEmpty(){
        return movieList==null || movieList.isEmpty();
    }

    @Override
    public void setView(MovieMVP.View view) {
        this.view = new WeakReference<>(view);
    }

    @Override
    public void viewReady(SearchView searchView) {

        view.get().mostrarProgressBar();

        UseCase uc = useCaseFactory.getMovies();

        addDisposable(uc.execute(new MoviesObserver(), null));
        addDisposable(uc.execute_search(new MoviesObserver(), searchView));
    }

    private final class MoviesObserver extends Observer<List<Movie>> {
        @Override
        public void onNext(List<Movie> movies) {
            saveMovies(movies);
            MovieMVP.View lista = view.get();
            lista.cleanAdapter();
            if(lista!=null) {
                view.get().ocultarProgressBar();
                lista.mostrarMovie(movies);
            }
        }

        @Override
        public void onError(Throwable e) {
            view.get().ocultarProgressBar();
            view.get().mensaje(e.getMessage());
            view.get().refreshList();
        }

        @Override
        public void onComplete() {
            view.get().ocultarProgressBar();
            view.get().mensaje("List Movies");
            super.onComplete();
        }
    }

    @Override
    public void configureImage(MovieCellView cellView, int posicion) {
        Movie movie = getMovie(posicion);
        cellView.displayImage(movie.getBackdropPath());
    }

    @Override
    public void configureTitulo(MovieCellView cellView, int posicion) {
        Movie movie = getMovie(posicion);
        cellView.displayTitulo(movie.getTitle());
    }

    @Override
    public void configureVoteAverage(MovieCellView cellView, int posicion) {
        Movie movie = getMovie(posicion);
        cellView.displayVoteAverage(movie.getVoteAverage());
    }

    @Override
    public void configureFecha(MovieCellView cellView, int posicion) {
        Movie movie = getMovie(posicion);
        cellView.displayFecha(movie.getReleaseDate());
    }

    @Override
    public void configurePopularidad(MovieCellView cellView, int posicion) {
        Movie movie = getMovie(posicion);
        cellView.displayPopularidad(movie.getPopularity());
    }

    @Override
    public void configureVoto_contador(MovieCellView cellView, int posicion) {
        Movie movie = getMovie(posicion);
        cellView.displayVoto_contador(movie.getVote_count());
    }

    @Override
    public void configurePromedio(MovieCellView cellView, int posicion) {
        Movie movie = getMovie(posicion);
        cellView.displayPromedio(movie.getVoteAverage());
    }

    @Override
    public void configureOverview(MovieCellView cellView, int posicion) {
        Movie movie = getMovie(posicion);
        cellView.displayOverview(movie.getOverview());
    }

    @Override
    public void configureBotonLeerMas(MovieCellView cellView, int posicion) {
        cellView.displayBotonLeerMas(posicion);
    }

    @Override
    public int getItemsCount() {
        if(listIsEmpty()){
            return 0;
        } else{
            return movieList.size();
        }
    }

    @Override
    public void onItemClick(MovieCellView cellView, int position) {
        cellView.onItemClick(position);
    }

    @Override
    public void navigateToDetailMovie(int posicion) {
        Movie movie = getMovie(posicion);
        view.get().navigateToDetailMovie(movie.getMovieId());
    }
}
