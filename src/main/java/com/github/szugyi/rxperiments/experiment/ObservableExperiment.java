package com.github.szugyi.rxperiments.experiment;

import com.github.szugyi.rxperiments.utils.TimeUtils;

import static com.github.szugyi.rxperiments.utils.LogUtils.log;
import static com.github.szugyi.rxperiments.utils.SchedulerUtils.applySchedulers;

/**
 * Created by szugyiczkicsaba on 15/01/17.
 */
public class ObservableExperiment implements IExperiment {

    @Override
    public void run() {
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
    }
}
