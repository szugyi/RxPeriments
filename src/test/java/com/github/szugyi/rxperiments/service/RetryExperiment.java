package com.github.szugyi.rxperiments.service;

import com.github.szugyi.rxperiments.utils.LogUtils;
import io.reactivex.Flowable;
import org.junit.Test;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class RetryExperiment extends BaseExperiment {

    @Test
    public void retryExperiment() throws Exception {
        apiClient.uploadAvatarError()
            .retryWhen(errors ->
                errors
                    .flatMap(error -> {
                        LogUtils.log("error type: " + error.getClass());

                        if (error instanceof UnknownHostException) {
                            return Flowable.error(error);
                        }

                        return Flowable.just(0);
                    })
                    .zipWith(Flowable.range(1, 3), (error, i) -> i)
                    .flatMap(retryCount -> Flowable.timer((long) Math.pow(2, retryCount), TimeUnit.SECONDS)))
            .subscribe(() -> LogUtils.log("Upload completed"),
                throwable -> LogUtils.log("Upload error: " + throwable.getClass()));

        System.in.read();
    }
}
