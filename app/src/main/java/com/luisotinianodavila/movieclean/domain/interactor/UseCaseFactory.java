package com.luisotinianodavila.movieclean.domain.interactor;

import com.luisotinianodavila.movieclean.data.repository.MoviesRepository;

import javax.inject.Inject;

public class UseCaseFactory {
    private MoviesRepository repository;

    @Inject
    public UseCaseFactory(MoviesRepository repository){
        this.repository = repository;
    }

    public UseCase getMovies(){
        return new GetMovies(repository);
    }
    public UseCase getMovie() {
        return new GetMovie(repository);
    }
}
