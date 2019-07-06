package com.luisotinianodavila.movieclean.presentation;

import androidx.annotation.VisibleForTesting;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter {
    private final CompositeDisposable disposables = new CompositeDisposable();

    public void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    public void destroy(){
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    @VisibleForTesting
    public CompositeDisposable getCompositeDisposable(){
        return disposables;
    }
}
