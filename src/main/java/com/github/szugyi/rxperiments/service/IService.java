package com.github.szugyi.rxperiments.service;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import javafx.util.Pair;

/**
 * Created by szugyiczkicsaba on 14/01/17.
 */
public interface IService {
    Observable<Integer> getNumber();

    Observable<Integer> getNumbers();

    Single<Integer> getSingleNumber();

    Observable<Pair<Integer, String>> getStringWithProgress();

    Observable<String> getStringFromUi();

    Observable<String> getThreadName();
}
