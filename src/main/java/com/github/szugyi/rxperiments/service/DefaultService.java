package com.github.szugyi.rxperiments.service;

import io.reactivex.Observable;
import io.reactivex.Single;
import javafx.util.Pair;

import static com.github.szugyi.rxperiments.utils.LogUtils.log;
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
        return Observable.create(subscriber -> {
            try {
                for (int i = 1; i <= 10; i++) {
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

    private String uiString = "Oh God this string is long!";
    //                              O   h  ' '  G   o   d  ' ' t   h   i   s  ' '  s   t  r   i   n   g  ' '  i   s  ' ' l   o   n   g   !
    private int[] typingPattern = {10, 13, 20, 45, 21, 6, 18, 68, 23, 31, 12, 25, 76, 9, 19, 22, 36, 14, 37, 12, 19, 8, 59, 17, 21, 20, 91};

    @Override
    public Observable<String> getStringFromUi() {
        return Observable.create(subscriber -> {
            StringBuilder sb = new StringBuilder(typingPattern.length);
            int i = 0;
            for (int pause : typingPattern) {
                sleep(pause);
                sb.append(uiString.charAt(i));
                subscriber.onNext(sb.toString());
                i++;
            }
            subscriber.onComplete();
        });
    }

    @Override
    public Observable<String> getThreadName() {
        return Observable.create(subscriber -> {
            String threadName = Thread.currentThread().getName();
            log("Observable run on: " + threadName);
            subscriber.onNext(threadName);
            subscriber.onComplete();
        });
    }
}
