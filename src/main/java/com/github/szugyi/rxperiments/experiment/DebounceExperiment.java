package com.github.szugyi.rxperiments.experiment;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

import static com.github.szugyi.rxperiments.utils.LogUtils.log;
import static com.github.szugyi.rxperiments.utils.SchedulerUtils.applySchedulers;

public class DebounceExperiment implements IExperiment {

    @Override
    public void run() {
        Observable.concat(getStringFromUiWithDebounce(5),
                getStringFromUiWithDebounce(50),
                getStringFromUiWithDebounce(500))
                .subscribe();
    }

    private Observable getStringFromUiWithDebounce(int millis) {
        return service.getStringFromUi()
                .compose(applySchedulers())
                .debounce(millis, TimeUnit.MILLISECONDS)
                .doOnNext(str -> {
                    log("Start lookup for: " + str);
                })
                .doOnComplete(() -> log("----------------------------------------------"));
    }
}
