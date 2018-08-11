package com.github.szugyi.rxperiments.service;

import io.reactivex.Observable;
import io.reactivex.Single;
import javafx.util.Pair;

public interface IService {
    Observable<Integer> getNumber();

    Observable<Integer> getNumbers();

    Single<Integer> getSingleNumber();

    Observable<Pair<Integer, String>> getStringWithProgress();

    Observable<String> getStringFromUi();

    Observable<String> getThreadName();
}
