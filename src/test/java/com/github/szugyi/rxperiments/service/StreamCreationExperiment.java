package com.github.szugyi.rxperiments.service;

import com.github.szugyi.rxperiments.LoggingSubscriber;
import com.github.szugyi.rxperiments.utils.LogUtils;
import com.github.szugyi.rxperiments.utils.TimeUtils;
import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.junit.Test;
import org.reactivestreams.Publisher;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class StreamCreationExperiment extends BaseExperiment {

    @Test
    public void emptyTest() {
        Flowable.empty()
                .subscribe(new LoggingSubscriber());
    }

    @Test
    public void justTest() {
        Flowable.just(1)
                .subscribe(new LoggingSubscriber());
    }

    @Test
    public void fromIterableTest() {
        List<Integer> integerList = Arrays.asList(1, 3, 5, 7, 9);
        Flowable.fromIterable(integerList)
                .subscribe(new LoggingSubscriber());
    }

    @Test
    public void fromArrayTest() {
        Integer[] intArray = new Integer[]{1, 3, 5, 7, 9};
        Flowable.fromArray(intArray)
                .subscribe(new LoggingSubscriber());
    }

    @Test
    public void rangeTest() {
        Flowable.range(1, 10)
                .subscribe(new LoggingSubscriber());
    }

    @Test
    public void wrongJustTest() {
        Flowable<Integer> randomFlowableJust = Flowable.just(getRandomValue());
        TimeUtils.sleep();
        randomFlowableJust.subscribe(new LoggingSubscriber());
    }

    @Test
    public void fromCallableTest() {
        Flowable<Integer> randomFlowableCallable = Flowable.fromCallable(this::getRandomValue);
        TimeUtils.sleep();
        randomFlowableCallable.subscribe(new LoggingSubscriber());
    }

    @Test
    public void intervalTest() throws Exception {
        Flowable.interval(1, TimeUnit.SECONDS)
                .subscribe(new LoggingSubscriber());

        System.in.read();
    }

    @Test
    public void repeatWhenTest() throws Exception {
        Flowable.fromCallable(this::getRandomValue)
                .repeatWhen(new Function<Flowable<Object>, Publisher<?>>() {
                    @Override
                    public Publisher<?> apply(Flowable<Object> completedFlowable) throws Exception {
                        Flowable updateInterval = Flowable.interval(1, TimeUnit.SECONDS)
                                .doOnNext(interval -> LogUtils.log("interval: " + interval));

                        return completedFlowable
                                .doOnNext(object -> LogUtils.log("onNext: " + object.toString()))
                                .zipWith(updateInterval, (item, event) -> {
                                    LogUtils.log("item: " + item + ", event: " + event);
                                    return item;
                                });
                    }
                })
                .subscribe(new LoggingSubscriber());

        System.in.read();
    }

    @Test
    public void zipWithTest() {
        List<String> words = Arrays.asList("the", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog");

        Flowable.fromIterable(words)
                .zipWith(Flowable.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string))
                .subscribe(LogUtils::log);
    }

    private Integer getRandomValue() {
        LogUtils.log("new random value requested");
        return new Random().nextInt();
    }
}
