package com.github.szugyi.rxperiments;

import com.github.szugyi.rxperiments.experiment.DisposableExperiment;
import com.github.szugyi.rxperiments.experiment.IExperiment;

import java.io.IOException;

public class Main {
    private static IExperiment experiment = new DisposableExperiment();

    public static void main(String[] args) throws IOException {
        System.out.println();

        experiment.run();

        System.in.read();
    }
}
