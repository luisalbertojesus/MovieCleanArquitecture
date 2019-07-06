package com.luisotinianodavila.movieclean.presentation.presenters;

import androidx.appcompat.widget.SearchView;

import com.luisotinianodavila.movieclean.domain.model.Movie;
import com.luisotinianodavila.movieclean.presentation.MovieCellView;

import java.util.List;

public interface MovieMVP {

    interface PresenterMovieDetail {
        void setView(ViewMovieDetail view);
        void viewReady(int movieId);
    }

    interface ViewMovieDetail{
        void mostrarMovie(Movie movie);
        void mensaje(String mensaje);
        void mostrarProgressBar();
        void ocultarProgressBar();
        void descargarPosterPath(String posterPath);
        void refresh();
    }

    interface Presenter{
        void setView(View view);
        void viewReady(SearchView searchView);
        void configureImage(MovieCellView cellView, int posicion);
        void configureTitulo(MovieCellView cellView, int posicion);

        void configureVoteAverage(MovieCellView cellView, int posicion);
        void configureFecha(MovieCellView cellView, int posicion);
        void configurePopularidad(MovieCellView cellView, int posicion);
        void configureVoto_contador(MovieCellView cellView, int posicion);
        void configurePromedio(MovieCellView cellView, int posicion);
        void configureOverview(MovieCellView cellView, int posicion);
        void configureBotonLeerMas(MovieCellView cellView, int posicion);

        int getItemsCount();
        void onItemClick(MovieCellView cellView, int position);
        void navigateToDetailMovie(int movieId);
    }

    interface View{
        void cleanAdapter();
        void refreshList();
        void mostrarMovie(List<Movie> list);
        void mensaje(String mensaje);
        void mostrarProgressBar();
        void ocultarProgressBar();
        void navigateToDetailMovie(int movieId);
    }
}
