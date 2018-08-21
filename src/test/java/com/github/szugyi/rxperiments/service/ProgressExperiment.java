package com.github.szugyi.rxperiments.service;

import com.github.szugyi.rxperiments.utils.SchedulerUtils;
import org.junit.Test;

import static com.github.szugyi.rxperiments.utils.LogUtils.log;

public class ProgressExperiment extends BaseExperiment {

    @Test
    public void progressExperiment() throws Exception {
        service.getStringWithProgress()
                .compose(SchedulerUtils.applySchedulers())
                .doOnNext(pair -> {
                    int progress = pair.getKey();
                    if (progress != -1) {
                        log("Progress update: " + progress);
                    } else {
                        log("Result: " + pair.getValue());
                    }
                })
                .subscribe();

        System.in.read();
    }
}
