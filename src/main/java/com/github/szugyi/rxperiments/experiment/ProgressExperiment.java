package com.github.szugyi.rxperiments.experiment;

import com.github.szugyi.rxperiments.utils.SchedulerUtils;

import static com.github.szugyi.rxperiments.utils.LogUtils.log;

/**
 * Created by szugyiczkicsaba on 15/01/17.
 */
public class ProgressExperiment implements IExperiment {

    @Override
    public void run() {
        service.getStringWithProgress()
                .compose(SchedulerUtils.applySchedulers())
                .doOnNext(pair -> {
                    int progress = pair.getKey();
                    if(progress != -1){
                        log("Progress update: " + progress);
                    }else{
                        log("Result: " + pair.getValue());
                    }
                })
                .subscribe();
    }
}
