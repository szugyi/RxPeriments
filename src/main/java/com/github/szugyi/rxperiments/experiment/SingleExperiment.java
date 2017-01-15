package com.github.szugyi.rxperiments.experiment;

import com.github.szugyi.rxperiments.utils.TimeUtils;

import static com.github.szugyi.rxperiments.utils.LogUtils.log;
import static com.github.szugyi.rxperiments.utils.SchedulerUtils.applySingleSchedulers;

/**
 * Created by szugyiczkicsaba on 15/01/17.
 */
public class SingleExperiment implements IExperiment {

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
