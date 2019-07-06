package com.luisotinianodavila.movieclean.data.exception;


public class ServiceException extends Exception {

    public ServiceException(){
        super("Un error ha ocurrido con el servidor");
    }

}
