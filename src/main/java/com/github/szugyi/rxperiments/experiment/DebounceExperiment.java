package com.github.szugyi.rxperiments.experiment;

import io.reactivex.Flowable;

import java.util.concurrent.TimeUnit;

import static com.github.szugyi.rxperiments.utils.LogUtils.log;
import static com.github.szugyi.rxperiments.utils.SchedulerUtils.applySchedulers;

public class DebounceExperiment implements Experiment {

    @Override
    public void run() {
        Flowable.concat(getStringFromUiWithDebounce(5),
                getStringFromUiWithDebounce(50),
                getStringFromUiWithDebounce(500))
                .subscribe();
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
