package com.github.szugyi.rxperiments.service;

import com.github.szugyi.rxperiments.utils.LogUtils;
import io.reactivex.flowables.ConnectableFlowable;
import org.junit.Test;

public class ShareExperiment extends BaseExperiment {

    @Test
    public void shareExperiment() throws Exception {
        ConnectableFlowable<String> stringFlowable = service.getThreadName().publish();

        stringFlowable
                .map(str -> "First subscriber: " + str)
                .subscribe(LogUtils::log);

        stringFlowable
                .map(str -> "Second subscriber: " + str)
                .subscribe(LogUtils::log);

        stringFlowable.connect();

        System.in.read();
    }
}
