package com.github.szugyi.rxperiments.experiment;

import com.github.szugyi.rxperiments.service.DefaultService;
import com.github.szugyi.rxperiments.service.Service;

public interface Experiment {
    Service service = new DefaultService();

    void run();
}
