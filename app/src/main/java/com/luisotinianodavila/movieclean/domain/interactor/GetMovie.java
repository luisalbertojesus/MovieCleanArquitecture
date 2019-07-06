package com.luisotinianodavila.movieclean.domain.interactor;

import androidx.appcompat.widget.SearchView;

import com.luisotinianodavila.movieclean.data.repository.MoviesRepository;
import com.luisotinianodavila.movieclean.domain.model.Movie;

import io.reactivex.Observable;

public class GetMovie extends UseCase<Movie, GetMovie.Params>{

    MoviesRepository repository;

    public GetMovie(MoviesRepository searchRepository){
        this.repository = searchRepository;
    }

    @Override
    Observable<Movie> buildUseCaseObservable(Params params) {
        return Observable.create(
                emitter -> {
                    try {
                        Movie movie = repository.getMovie(params.getMovieId());
                        emitter.onNext(movie);
                    } catch (Exception exception){
                        if (!emitter.isDisposed()) {
                            emitter.onError(exception);
                        }
                    }
                }
        );
    }

    @Override
    Observable<Movie> buildUseCaseObservableSearch(SearchView searchView) {
        return null;
    }

    public static final class Params{

        private final int movieId;

        public Params(int movieId){
            this.movieId = movieId;
        }

        public int getMovieId() {
            return movieId;
        }

    }
}
