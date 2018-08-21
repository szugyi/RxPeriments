package com.github.szugyi.rxperiments;

import com.github.szugyi.rxperiments.utils.LogUtils;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class LoggingSubscriber implements Subscriber<Object> {

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
