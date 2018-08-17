package com.github.szugyi.rxperiments.experiment;

import com.github.szugyi.rxperiments.service.SampleApi;
import com.github.szugyi.rxperiments.utils.LogUtils;

public class MaybeExperiment implements Experiment {

    private final SampleApi apiClient = new SampleApi();

    @Override
    public void run() {
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
