package com.github.szugyi.rxperiments.experiment;

import com.github.szugyi.rxperiments.utils.LogUtils;
import com.github.szugyi.rxperiments.utils.SchedulerUtils;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class DisposableExperiment implements Experiment {
    private Disposable disposable;
    private CompositeDisposable disposables = new CompositeDisposable();

    @Override
    public void run() {
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
    }

    private void stopExperiment() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
