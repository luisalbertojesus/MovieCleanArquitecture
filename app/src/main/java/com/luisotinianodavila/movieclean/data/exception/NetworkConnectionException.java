package com.luisotinianodavila.movieclean.data.exception;


public class NetworkConnectionException extends Exception {

    public NetworkConnectionException(){
        super("La conexión ha fallado");
    }

}
