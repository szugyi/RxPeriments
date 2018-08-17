package com.github.szugyi.rxperiments.experiment;

import com.github.szugyi.rxperiments.utils.LogUtils;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class BackpressureExperiment implements Experiment {

    @Override
    public void run() {
        Observable.interval(1, TimeUnit.MILLISECONDS)
                .toFlowable(BackpressureStrategy.DROP)
                .observeOn(Schedulers.single())
                .subscribe(value -> {
                    Thread.sleep(50);
                    LogUtils.log("value: " + value);
                });
    }
}
