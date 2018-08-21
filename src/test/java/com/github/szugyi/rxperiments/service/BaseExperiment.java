package com.github.szugyi.rxperiments.service;

import io.reactivex.plugins.RxJavaPlugins;
import org.junit.Before;

abstract class BaseExperiment {
    Service service = new DefaultService();
    SampleApi apiClient = new SampleApi();

    @Before
    public void setup() {
        RxJavaPlugins.setErrorHandler(e -> {
        });
    }
}
