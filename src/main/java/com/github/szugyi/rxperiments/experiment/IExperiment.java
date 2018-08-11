package com.github.szugyi.rxperiments.experiment;

import com.github.szugyi.rxperiments.service.DefaultService;
import com.github.szugyi.rxperiments.service.IService;

public interface IExperiment {
    IService service = new DefaultService();

    void run();
}
