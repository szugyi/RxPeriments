package com.github.szugyi.rxperiments.service;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by szugyiczkicsaba on 14/01/17.
 */
public interface IService {
    Observable<Integer> getNumber();

    Observable<Integer> getNumbers();

    Single<Integer> getSingleNumber();
}
