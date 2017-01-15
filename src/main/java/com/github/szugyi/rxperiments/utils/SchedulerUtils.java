package com.github.szugyi.rxperiments.utils;

import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by szugyiczkicsaba on 15/01/17.
 */
public class SchedulerUtils {
    public static <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single());
    }

    public static <T> SingleTransformer<T, T> applySingleSchedulers() {
        return single -> single.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single());
    }
}
