package com.github.szugyi.rxperiments.service;

import com.github.szugyi.rxperiments.utils.LogUtils;
import com.github.szugyi.rxperiments.utils.TimeUtils;
import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MapExperiment extends BaseExperiment {

    @Test
    public void flatMapExperiment() throws Exception {
        final List<String> items = Arrays.asList("a", "b", "c", "d", "e", "f");

        Flowable.fromIterable(items)
                .flatMap(s -> {
                    final int delay = new Random().nextInt(10);
                    return Flowable.just(s + "x")
                            .delay(delay, TimeUnit.SECONDS, Schedulers.computation())
                            .doOnNext(value -> TimeUtils.log(value + ", delay: " + delay));
                })
                .toList()
                .doOnSubscribe(disposable -> TimeUtils.log("Start"))
                .doOnSuccess(list -> TimeUtils.log(list.toString()))
                .subscribe();

        System.in.read();
    }

    @Test
    public void concatMapExperiment() throws Exception {
        final List<String> items = Arrays.asList("a", "b", "c", "d", "e", "f");

        Flowable.fromIterable(items)
                .concatMap(s -> {
                    final int delay = new Random().nextInt(10);
                    return Flowable.just(s + "x")
                            .delay(delay, TimeUnit.SECONDS, Schedulers.computation())
                            .doOnNext(value -> TimeUtils.log(value + ", delay: " + delay));
                })
                .toList()
                .doOnSubscribe(disposable -> TimeUtils.log("Start"))
                .doOnSuccess(list -> TimeUtils.log(list.toString()))
                .subscribe();

        System.in.read();
    }

    @Test
    public void concatMapEagerExperiment() throws Exception {
        LogUtils.log("\n\n");

        final List<String> items = Arrays.asList("a", "b", "c", "d", "e", "f");

        Flowable.fromIterable(items)
                .concatMapEager(s -> {
                    final int delay = new Random().nextInt(10);
                    return Flowable.just(s + "x")
                            .delay(delay, TimeUnit.SECONDS, Schedulers.computation())
                            .doOnNext(value -> TimeUtils.log(value + ", delay: " + delay));
                })
                .toList()
                .doOnSubscribe(disposable -> TimeUtils.log("Start"))
                .doOnSuccess(list -> TimeUtils.log(list.toString()))
                .subscribe();

        System.in.read();
    }

    @Test
    public void switchMapExperiment() throws Exception {
        Flowable.intervalRange(1, 5, 0, 5, TimeUnit.SECONDS)
                .switchMap(s -> {
                    final int delay = new Random().nextInt(10);
                    TimeUtils.log(s + ", delayed: " + delay);

                    return Flowable.just(s + "x")
                            .delay(delay, TimeUnit.SECONDS, Schedulers.computation())
                            .doOnNext(value -> TimeUtils.log(value + ", after: " + delay));
                })
                .toList()
                .subscribe(System.out::println);

        System.in.read();
    }

    private Flowable<String> getStringDelayed(String s) {
        int delay = new Random().nextInt(5);
        TimeUtils.log(s + ", delay: " + delay);

        return Flowable.just(s)
                .delay(delay, TimeUnit.SECONDS, Schedulers.computation());
    }
}
