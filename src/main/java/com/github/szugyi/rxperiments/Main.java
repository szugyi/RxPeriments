package com.github.szugyi.rxperiments;

import com.github.szugyi.rxperiments.experiment.BackpressureExperiment;
import com.github.szugyi.rxperiments.experiment.Experiment;
import com.github.szugyi.rxperiments.experiment.MaybeExperiment;

import java.io.IOException;

public class Main {
    private static Experiment experiment = new MaybeExperiment();

    public static void main(String[] args) throws IOException {
        System.out.println();

        experiment.run();

        System.in.read();
    }
}
