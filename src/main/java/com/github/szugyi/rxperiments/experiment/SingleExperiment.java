package com.github.szugyi.rxperiments.experiment;

import com.github.szugyi.rxperiments.utils.TimeUtils;

import static com.github.szugyi.rxperiments.utils.LogUtils.log;
import static com.github.szugyi.rxperiments.utils.SchedulerUtils.applySingleSchedulers;

public class SingleExperiment implements Experiment {

    @Override
    public void run() {
        service.getSingleNumber()
                .compose(applySingleSchedulers())
                .subscribe(number -> {
                    TimeUtils.logEnd();
                    log("Result is: " + number);
                });
    }
}
