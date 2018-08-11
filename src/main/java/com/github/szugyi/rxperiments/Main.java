package com.github.szugyi.rxperiments;

import com.github.szugyi.rxperiments.experiment.ComposeExperiment;
import com.github.szugyi.rxperiments.experiment.IExperiment;
import io.reactivex.Observable;

import java.io.IOException;

public class Main {
    private static IExperiment experiment = new ComposeExperiment();

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        Observable.just("Hello RxWorld!").subscribe(System.out::println);

        experiment.run();

        System.in.read();
    }
}
