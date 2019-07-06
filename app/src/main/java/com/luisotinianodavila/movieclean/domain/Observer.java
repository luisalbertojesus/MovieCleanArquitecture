package com.luisotinianodavila.movieclean.domain;

import io.reactivex.observers.DisposableObserver;

public class Observer<T> extends DisposableObserver<T> {
    @Override
    public void onNext(T t) {}

    @Override
    public void onError(Throwable e) {}

    @Override
    public void onComplete() {}
}
