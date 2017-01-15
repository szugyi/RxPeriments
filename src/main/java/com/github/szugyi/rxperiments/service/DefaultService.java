package com.github.szugyi.rxperiments.service;

import io.reactivex.Observable;
import io.reactivex.Single;

import static com.github.szugyi.rxperiments.utils.TimeUtils.logStart;
import static com.github.szugyi.rxperiments.utils.TimeUtils.sleep;

/**
 * Created by szugyiczkicsaba on 14/01/17.
 */
public class DefaultService implements IService {

    @Override
    public Observable<Integer> getNumber() {
        return Observable.defer(() -> {
            logStart();
            sleep();
            return Observable.just(42);
        });
    }

    @Override
    public Observable<Integer> getNumbers() {
        return Observable.defer(() -> {
            logStart();
            sleep();
            return Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        });
    }

    @Override
    public Single<Integer> getSingleNumber() {
        return Single.defer(() -> {
            logStart();
            sleep();
            return Single.just(1);
        });
    }


}
