package com.github.szugyi.rxperiments.service;

import com.github.szugyi.rxperiments.utils.LogUtils;
import io.reactivex.Flowable;
import org.junit.Test;

public class ShareExperiment extends BaseExperiment {

    @Test
    public void shareExperiment() throws Exception {
        Flowable<String> stringFlowable = service.getThreadName().share();

        stringFlowable
                .map(str -> "First subscriber: " + str)
                .subscribe(LogUtils::log);

        stringFlowable
                .map(str -> "Second subscriber: " + str)
                .subscribe(LogUtils::log);

        System.in.read();
    }
}
