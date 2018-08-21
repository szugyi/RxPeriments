package com.github.szugyi.rxperiments.service;

import com.github.szugyi.rxperiments.utils.LogUtils;
import com.github.szugyi.rxperiments.utils.TimeUtils;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

import java.net.UnknownHostException;
import java.util.Random;
import java.util.zip.DataFormatException;

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

    public Completable uploadAvatarError() {
        return Completable.create(source -> {
            TimeUtils.log("try to upload avatar");

            if (RANDOM.nextInt(100) <= 20) {
                source.onError(new UnknownHostException());
            }

            source.onError(new RuntimeException());
        });
    }
}
