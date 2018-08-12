package com.github.szugyi.rxperiments.experiment;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;

import static com.github.szugyi.rxperiments.utils.LogUtils.log;

/**
 * Created by szugyi on 17/01/17.
 */
public class ComposeExperiment implements Experiment {


    @Override
    public void run() {
        service.getThreadName()
                .compose(new LoggingTransformer("Transformer 1"))
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .compose(new LoggingTransformer("Transformer 2"))
                .observeOn(Schedulers.computation())
                .compose(new LoggingTransformer("Transformer 3"))
                .subscribeOn(Schedulers.io())
                .subscribe(thread -> {
                    log("onNext run on: " + Thread.currentThread().getName());
                });
    }

    private static class LoggingTransformer implements FlowableTransformer {
        private String name;

        public LoggingTransformer(String name) {
            this.name = name;
        }

        @Override
        public Publisher apply(Flowable upstream) {
            log(name + " apply run on: " + Thread.currentThread().getName());
            return upstream.doOnNext(o -> {
                log(name + " onNext run on: " + Thread.currentThread().getName());
            });
        }
    }
}
