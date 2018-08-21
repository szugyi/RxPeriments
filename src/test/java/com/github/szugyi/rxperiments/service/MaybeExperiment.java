package com.github.szugyi.rxperiments.service;

import com.github.szugyi.rxperiments.experiment.Experiment;
import com.github.szugyi.rxperiments.service.SampleApi;
import com.github.szugyi.rxperiments.utils.LogUtils;
import org.junit.Test;

public class MaybeExperiment extends BaseExperiment {

    @Test
    public void maybeExperiment() {
        apiClient.getAvatar()
                .subscribe(avatar -> {
                    // Avatar is set and has been retrieved successfully
                    LogUtils.log(avatar);
                }, throwable -> {
                    // Error happened during loading avatar
                }, () -> {
                    // No avatar to show
                    LogUtils.log("getAvatar completed");
                });
    }
}
