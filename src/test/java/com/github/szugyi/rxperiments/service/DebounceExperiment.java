package com.github.szugyi.rxperiments.service;

import io.reactivex.Flowable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static com.github.szugyi.rxperiments.utils.LogUtils.log;
import static com.github.szugyi.rxperiments.utils.SchedulerUtils.applySchedulers;

public class DebounceExperiment extends BaseExperiment {

    @Test
    public void debounceExperiment() throws Exception {
        Flowable.concat(getStringFromUiWithDebounce(5),
                getStringFromUiWithDebounce(50),
                getStringFromUiWithDebounce(500))
                .subscribe();

        System.in.read();
    }

    private Flowable<String> getStringFromUiWithDebounce(int millis) {
        return service.getStringFromUi()
                .compose(applySchedulers())
                .debounce(millis, TimeUnit.MILLISECONDS)
                .doOnNext(str -> {
                    log("Start lookup for: " + str);
                })
                .doOnComplete(() -> log("----------------------------------------------"));
    }
}
