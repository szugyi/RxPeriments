package com.github.szugyi.rxperiments;

import io.reactivex.Observable;

/**
 * Created by szugyi on 14/01/17.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Observable.just("Hello RxWorld!").subscribe(System.out::println);
    }
}
