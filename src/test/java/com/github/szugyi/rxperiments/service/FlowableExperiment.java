package com.github.szugyi.rxperiments.service;

import com.github.szugyi.rxperiments.utils.TimeUtils;
import org.junit.Test;

import static com.github.szugyi.rxperiments.utils.LogUtils.log;
import static com.github.szugyi.rxperiments.utils.SchedulerUtils.applySchedulers;

public class FlowableExperiment extends BaseExperiment {

    @Test
    public void flowableExperiment() throws Exception {
        service.getNumber()
                .compose(applySchedulers())
                .subscribe(number -> {
                    TimeUtils.logEnd();
                    log("Result is: " + number);
                });

        service.getNumbers()
                .compose(applySchedulers())
                .subscribe(number -> {
                    TimeUtils.logEnd();
                    log("Result is: " + number);
                });

        System.in.read();
    }
}
