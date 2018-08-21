package com.github.szugyi.rxperiments.service;

import com.github.szugyi.rxperiments.utils.LogUtils;
import com.github.szugyi.rxperiments.utils.SchedulerUtils;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class DisposableExperiment extends BaseExperiment {
    private Disposable disposable;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Test
    public void disposableExperiment() throws Exception {
        disposable = Flowable.range(1, 10)
                .compose(SchedulerUtils.applySchedulers())
//                .concatMap(i-> Flowable.just(i).delay(500, TimeUnit.MILLISECONDS))
                .map(value -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        // This is normal
                    }

                    return String.valueOf(value);
                })
                .subscribe(LogUtils::log);

        disposables.add(Completable.complete()
                .delay(3, TimeUnit.SECONDS)
                .subscribe(this::stopExperiment));

        System.in.read();
    }

    private void stopExperiment() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
