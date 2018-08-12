package com.github.szugyi.rxperiments.service;

import io.reactivex.Flowable;
import io.reactivex.Single;
import javafx.util.Pair;

public interface Service {
    Flowable<Integer> getNumber();

    Flowable<Integer> getNumbers();

    Single<Integer> getSingleNumber();

    Flowable<Pair<Integer, String>> getStringWithProgress();

    Flowable<String> getStringFromUi();

    Flowable<String> getThreadName();
}
