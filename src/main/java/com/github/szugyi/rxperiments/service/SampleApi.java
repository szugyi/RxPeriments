package com.github.szugyi.rxperiments.service;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.util.Random;

public class SampleApi {
    private static final Random RANDOM = new Random();

    public Single<String> getProfile() {
        return Single.just("Profile result");
    }

    public Maybe<String> getAvatar() {
        return Maybe.create(emitter -> {
            if (RANDOM.nextBoolean()) {
                emitter.onSuccess("Avatar url");
            } else {
                emitter.onComplete();
            }
        });
    }

    public Completable uploadAvatar() {
        return Completable.complete();
    }
}
