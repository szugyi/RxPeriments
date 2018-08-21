package com.github.szugyi.rxperiments.service;

import com.github.szugyi.rxperiments.utils.LogUtils;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;


public class SchedulerExperiment extends BaseExperiment {

    @Test
    public void schedulerExperiment() throws Exception {
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

        System.in.read();
    }
}
