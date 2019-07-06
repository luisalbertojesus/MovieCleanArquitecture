package com.luisotinianodavila.movieclean.domain;

import androidx.appcompat.widget.SearchView;
import android.text.TextUtils;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxSearchObservable {

    private RxSearchObservable() {
    }

    public static Observable<String> fromView(SearchView searchView) {

        final PublishSubject<String> subject = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (TextUtils.isEmpty(s)) subject.onNext("");
                else subject.onNext(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) subject.onNext("");
                else subject.onNext(newText);
                return true;
            }
        });

        return subject;
    }
}
