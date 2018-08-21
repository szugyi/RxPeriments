package com.github.szugyi.rxperiments.service;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.internal.functions.ObjectHelper;
import org.junit.Test;

public class NullExperiment extends BaseExperiment {

    @Test
    public void nullExperiment() throws Exception {
        Object nonNullValue = ObjectHelper.requireNonNull(null, "Object was null");

        Observable.just(null);

        Single.just(null);

        Observable.fromCallable(() -> null)
                .subscribe(System.out::println, Throwable::printStackTrace);

        Observable.just(1).map(v -> null)
                .subscribe(System.out::println, Throwable::printStackTrace);

        System.in.read();
    }
}
