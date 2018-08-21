package com.github.szugyi.rxperiments;

import com.github.szugyi.rxperiments.experiment.*;
import io.reactivex.plugins.RxJavaPlugins;

import java.io.IOException;

public class Main {
    private static Experiment experiment = new RetryExperiment();

    public static void main(String[] args) throws IOException {
        System.out.println();
        RxJavaPlugins.setErrorHandler(e -> { });

        experiment.run();

        System.in.read();
    }
}
