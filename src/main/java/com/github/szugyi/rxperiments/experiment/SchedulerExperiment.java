package com.github.szugyi.rxperiments.experiment;

import com.github.szugyi.rxperiments.utils.LogUtils;
import io.reactivex.schedulers.Schedulers;


public class SchedulerExperiment implements Experiment {

    @Override
    public void run() {


        service.getThreadName()
                .subscribeOn(Schedulers.computation())
                .doOnNext(ignored -> LogUtils.log("First onNext run on: " + Thread.currentThread().getName()))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .doOnNext(ignored -> LogUtils.log("Second onNext run on: " + Thread.currentThread().getName()))
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(ignored -> LogUtils.log("Third onNext run on:" + Thread.currentThread().getName()))
                .subscribe(ignored -> LogUtils.log("Subscribe consumer run on:" + Thread.currentThread().getName()));



    }
}
