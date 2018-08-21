package com.github.szugyi.rxperiments.experiment;

import com.github.szugyi.rxperiments.service.SampleApi;
import com.github.szugyi.rxperiments.utils.LogUtils;
import io.reactivex.Flowable;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class RetryExperiment implements Experiment {

    private final SampleApi apiClient = new SampleApi();

    @Override
    public void run() {
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
    }
}
