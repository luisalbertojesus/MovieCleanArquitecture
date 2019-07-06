package com.luisotinianodavila.movieclean.presentation;

public interface MovieCellView {
    void displayImage(String url);
    void displayTitulo(String name);
    void displayVoteAverage(Double param);
    void displayFecha(String param);
    void displayPopularidad(Double param);
    void displayVoto_contador(Integer param);
    void displayPromedio(Double param);
    void displayOverview(String param);
    void displayBotonLeerMas(Integer posicion);
    void onItemClick(int i);
}
