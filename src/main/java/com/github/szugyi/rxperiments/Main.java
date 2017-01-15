package com.github.szugyi.rxperiments;

import com.github.szugyi.rxperiments.experiment.*;
import com.github.szugyi.rxperiments.service.DefaultService;
import com.github.szugyi.rxperiments.service.IService;
import com.github.szugyi.rxperiments.utils.TimeUtils;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;

import static com.github.szugyi.rxperiments.utils.LogUtils.log;
import static com.github.szugyi.rxperiments.utils.SchedulerUtils.applySingleSchedulers;

/**
 * Created by szugyi on 14/01/17.
 */
public class Main {
    private static IExperiment experiment = new SchedulerExperiment();

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");
        Observable.just("Hello RxWorld!").subscribe(System.out::println);

        experiment.run();

        System.in.read();
    }
}
