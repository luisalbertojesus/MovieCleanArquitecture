package com.luisotinianodavila.movieclean.domain.interactor;

import androidx.appcompat.widget.SearchView;
import android.text.TextUtils;

import com.luisotinianodavila.movieclean.data.repository.MoviesRepository;
import com.luisotinianodavila.movieclean.domain.RxSearchObservable;
import com.luisotinianodavila.movieclean.domain.model.Movie;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class GetMovies extends UseCase<List<Movie>, GetMovies.Params>{

    MoviesRepository repository;

    public GetMovies(MoviesRepository searchRepository){
        this.repository = searchRepository;
    }

    @Override
    Observable<List<Movie>> buildUseCaseObservable(Params params) {
        return Observable.create(
                emitter -> {
                    try {
                        List<Movie> movieList = repository.getMovies();
                        emitter.onNext(movieList);
                    } catch (Exception exception){
                        if (!emitter.isDisposed()) {
                            emitter.onError(exception);
                        }
                    }
                }
        );
    }

    @Override
    Observable<List<Movie>> buildUseCaseObservableSearch(SearchView searchView) {
        return RxSearchObservable.fromView(searchView)
                .debounce(2, TimeUnit.SECONDS)
                .distinctUntilChanged()
                .switchMap(query -> {
                    if (TextUtils.isEmpty(query)) return buildUseCaseObservable(null);
                    else return buildUseCaseObservableSearch(query);
                });
    }

    Observable<List<Movie>> buildUseCaseObservableSearch(String query) {
        return Observable.create(
                emitter -> {
                    try {
                        List<Movie> movieList = repository.getMoviesSearch(query);
                        emitter.onNext(movieList);
                    } catch (Exception exception){
                        if (!emitter.isDisposed()) {
                            emitter.onError(exception);
                        }
                    }
                }
        );
    }

    public class Params {
        private SearchView searchView;

        public Params(SearchView searchView){
            this.searchView = searchView;
        }

        public SearchView getSearchView() {
            return searchView;
        }
    }
}
