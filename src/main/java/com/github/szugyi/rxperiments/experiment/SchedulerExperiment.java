package com.github.szugyi.rxperiments.experiment;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.github.szugyi.rxperiments.utils.LogUtils.log;

/**
 * Created by szugyiczkicsaba on 15/01/17.
 */
public class SchedulerExperiment implements IExperiment {

    @Override
    public void run() {
        Observable.zip(service.getThreadName()
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.single())
                .doOnNext(thread -> {
                    log("First onNext was on: " + Thread.currentThread().getName());
                }), service.getThreadName()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .doOnNext(thread -> {
                    log("Second onNext was on: " + Thread.currentThread().getName());
                }), (thread1, thread2) -> {
                    log("Zipping was on:" + Thread.currentThread().getName());
                    return thread1 + thread2;
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.newThread())
                .doOnNext(threads -> {
                    log("Zipped onNext was on:" + Thread.currentThread().getName());
                })
                .subscribe();
    }
}
