package com.github.szugyi.rxperiments.experiment;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

import static com.github.szugyi.rxperiments.utils.LogUtils.log;

public class SchedulerExperiment implements Experiment {

    @Override
    public void run() {
        Flowable.zip(service.getThreadName()
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
