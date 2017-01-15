package com.github.szugyi.rxperiments.service;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import javafx.util.Pair;

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

    @Override
    public Observable<Pair<Integer, String>> getStringWithProgress() {
        return Observable.create((ObservableOnSubscribe<Pair<Integer, String>>) subscriber -> {
            try {
                for (int i = 1; i <= 10; i++ ){
                    sleep(200);
                    subscriber.onNext(new Pair<>(i * 10, null));
                }
                subscriber.onNext(new Pair<>(-1, "A String, created with tons of computation"));
                subscriber.onComplete();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }


}
