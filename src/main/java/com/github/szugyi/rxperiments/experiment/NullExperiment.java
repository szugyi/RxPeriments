package com.github.szugyi.rxperiments.experiment;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.internal.functions.ObjectHelper;

public class NullExperiment implements IExperiment {

    @Override
    public void run() {
        Object nonNullValue = ObjectHelper.requireNonNull(null, "Object was null");

        Observable.just(null);

        Single.just(null);

        Observable.fromCallable(() -> null)
                .subscribe(System.out::println, Throwable::printStackTrace);

        Observable.just(1).map(v -> null)
                .subscribe(System.out::println, Throwable::printStackTrace);
    }
}
