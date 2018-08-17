package com.github.szugyi.rxperiments.experiment;

import com.github.szugyi.rxperiments.service.SampleApi;

public class SingleExperiment implements Experiment {

    private final SampleApi apiClient = new SampleApi();

    @Override
    public void run() {
        apiClient.getProfile()
                .subscribe(profileData -> {
                    // Profile data loaded successfully
                }, throwable -> {
                    // Error happened during loading profile data
                });
    }
}
