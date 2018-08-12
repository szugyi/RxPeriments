package com.github.szugyi.rxperiments.utils;

import io.reactivex.FlowableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.schedulers.Schedulers;

public class SchedulerUtils {
    public static <T> FlowableTransformer<T, T> applySchedulers() {
        return flowable -> flowable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single());
    }

    public static <T> SingleTransformer<T, T> applySingleSchedulers() {
        return single -> single.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single());
    }
}
