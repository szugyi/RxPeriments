package com.github.szugyi.rxperiments.service;

import com.github.szugyi.rxperiments.utils.LogUtils;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class BackpressureExperiment extends BaseExperiment {

    @Test
    public void backpressureExperiment() throws Exception {
        Observable.interval(10, TimeUnit.MILLISECONDS)
                .doOnNext(interval -> LogUtils.log("interval: " + interval))
                .toFlowable(BackpressureStrategy.DROP)
                .observeOn(Schedulers.single(), false, 1)
                .subscribe(value -> {
                    LogUtils.log("value: " + value);
                    Thread.sleep(50);
                });

        System.in.read();
    }
}
