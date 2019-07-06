package com.luisotinianodavila.movieclean.domain.interactor;

import androidx.appcompat.widget.SearchView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase <T, P> {

    public UseCase() {}

    abstract Observable<T> buildUseCaseObservable(P params);
    abstract Observable<T> buildUseCaseObservableSearch(SearchView searchView);

    public Disposable execute(DisposableObserver<T> observer, P params){
        final Observable<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable.subscribeWith(observer);

    }

    public Disposable execute_search(DisposableObserver<T> observer, P params){
        final Observable<T> observable = this.buildUseCaseObservableSearch((SearchView) params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return observable.subscribeWith(observer);
    }
}
