package com.github.szugyi.rxperiments.experiment;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.schedulers.Schedulers;

import static com.github.szugyi.rxperiments.utils.LogUtils.log;

/**
 * Created by szugyi on 17/01/17.
 */
public class ComposeExperiment implements IExperiment {


    @Override
    public void run() {
        service.getThreadName()
                .compose(new LoggingTransformer())
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .compose(new LoggingTransformer())
                .subscribeOn(Schedulers.io())
                .compose(new LoggingTransformer())
                .observeOn(Schedulers.computation())
                .subscribe(thread -> {
                    log("onNext was on: " + Thread.currentThread().getName());
                });
    }

    private static class LoggingTransformer implements ObservableTransformer {
        @Override
        public ObservableSource apply(Observable upstream) {
            String threadName = Thread.currentThread().getName();
            log("Compose run on: " + threadName);
            return upstream;
        }
    }
}
