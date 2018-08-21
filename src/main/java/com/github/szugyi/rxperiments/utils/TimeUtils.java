package com.github.szugyi.rxperiments.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss.S");

    public static void logStart() {
        log("Started");
    }

    public static void logEnd() {
        log("Ended");
    }

    public static void log(String prefix){
        LocalDateTime date = LocalDateTime.now();
        LogUtils.log(prefix + " at: " + date.format(format));
    }

    public static void sleep() {
        sleep(1000);
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
