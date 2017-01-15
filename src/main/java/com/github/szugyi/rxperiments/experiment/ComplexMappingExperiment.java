package com.github.szugyi.rxperiments.experiment;

import com.github.szugyi.rxperiments.utils.SchedulerUtils;
import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

import static com.github.szugyi.rxperiments.utils.LogUtils.log;
import static com.github.szugyi.rxperiments.utils.SchedulerUtils.applySchedulers;

/**
 * Created by szugyiczkicsaba on 15/01/17.
 */
public class ComplexMappingExperiment implements IExperiment {

    @Override
    public void run() {
        Observable.fromIterable(createData())
                .compose(applySchedulers())
                .flatMap(a ->
                        Observable.fromIterable(a.getB())
                            .filter(b -> b.isC())
                            .map(b -> a.getD())
                            .distinct()
                )
                .subscribe(d -> {
                    log("Result came from: " + d);
                });
    }

    private List<A> createData() {
        List<B> listOfFalseBs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listOfFalseBs.add(new B(false));
        }

        List<B> listOfTrueB = new ArrayList<>();
        listOfTrueB.add(new B(true));

        List<A> listOfAs = new ArrayList<>();
        listOfAs.add(new A(listOfFalseBs, "FirstA"));
        listOfAs.add(new A(listOfFalseBs, "SecondA"));
        listOfAs.add(new A(listOfFalseBs, "ThirdA"));
        listOfAs.add(new A(listOfTrueB, "FourthA"));
        listOfAs.add(new A(listOfFalseBs, "FifthA"));
        listOfAs.add(new A(listOfFalseBs, "SixthA"));
        listOfAs.add(new A(listOfTrueB, "SeventhA"));
        listOfAs.add(new A(listOfFalseBs, "EighthA"));

        return listOfAs;
    }

    private static class A {
        private List<B> b;
        private String d;

        public A(List<B> b, String d) {
            this.b = b;
            this.d = d;
        }

        public List<B> getB() {
            return b;
        }

        public void setB(List<B> bList) {
            this.b = bList;
        }

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }
    }

    private static class B {
        private boolean c;

        public B(boolean c) {
            this.c = c;
        }

        public boolean isC() {
            return c;
        }

        public void setC(boolean c) {
            this.c = c;
        }
    }
}
