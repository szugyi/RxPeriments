package com.github.szugyi.rxperiments.experiment;

import com.github.szugyi.rxperiments.utils.LogUtils;
import com.github.szugyi.rxperiments.utils.TimeUtils;
import io.reactivex.Flowable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class StreamCreationExperiment implements Experiment {


    @Override
    public void run() {
        Flowable.empty()
                .subscribe(new LoggingSubscriber());

        LogUtils.log("\n\n");

        Flowable.just(1)
                .subscribe(new LoggingSubscriber());

        LogUtils.log("\n\n");

        List<Integer> integerList = Arrays.asList(1, 3, 5, 7, 9);
        Flowable.fromIterable(integerList)
                .subscribe(new LoggingSubscriber());

        LogUtils.log("\n\n");

        Integer[] intArray = new Integer[]{1, 3, 5, 7, 9};
        Flowable.fromArray(intArray)
                .subscribe(new LoggingSubscriber());

        LogUtils.log("\n\n");

        Flowable.range(1, 10)
                .subscribe(new LoggingSubscriber());

        LogUtils.log("\n\n");

        Flowable<Integer> randomFlowableJust = Flowable.just(getRandomValue());
        TimeUtils.sleep();
        randomFlowableJust.subscribe(new LoggingSubscriber());

        LogUtils.log("\n\n");

        Flowable<Integer> randomFlowableCallable = Flowable.fromCallable(this::getRandomValue);
        TimeUtils.sleep();
        randomFlowableCallable.subscribe(new LoggingSubscriber());

        LogUtils.log("\n\n");

        Flowable.interval(1, TimeUnit.SECONDS)
                .subscribe(new LoggingSubscriber());

        LogUtils.log("\n\n");

        Flowable updateInterval = Flowable.interval(1, TimeUnit.SECONDS);

        Flowable.fromCallable(this::getRandomValue)
                .repeatWhen(completed -> completed.zipWith(updateInterval, (item, event) -> item))
                .subscribe(new LoggingSubscriber());
    }

    private Integer getRandomValue() {
        LogUtils.log("new random value requested");
        return new Random().nextInt();
    }

    private static final class LoggingSubscriber implements Subscriber<Object> {

        @Override
        public void onSubscribe(Subscription subscription) {
            LogUtils.log("onSubscribe");
            subscription.request(Long.MAX_VALUE);
        }

        @Override
        public void onNext(Object value) {
            LogUtils.log("value: " + value);
        }

        @Override
        public void onError(Throwable throwable) {
            LogUtils.log("error: " + throwable.getMessage());
        }

        @Override
        public void onComplete() {
            LogUtils.log("onComplete");
        }
    }
}
