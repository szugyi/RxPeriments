package com.github.szugyi.rxperiments.service;

import com.github.szugyi.rxperiments.utils.LogUtils;
import org.junit.Test;

public class SingleExperiment extends BaseExperiment {

    @Test
    public void singleExperiment() throws Exception {
        apiClient.getProfile()
                .subscribe(profileData -> {
                    // Profile data loaded successfully
                    LogUtils.log(profileData);
                }, throwable -> {
                    // Error happened during loading profile data
                    LogUtils.log("Error happened: " + throwable.getClass());
                });
    }

}