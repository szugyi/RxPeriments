package com.github.szugyi.rxperiments.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by szugyiczkicsaba on 15/01/17.
 */
public class TimeUtils {
    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss.S");

    public static void logStart() {
        log("Started");
    }

    public static void logEnd() {
        log("Ended");
    }

    private static void log(String prefix){
        LocalDateTime date = LocalDateTime.now();
        LogUtils.log(prefix + " at: " + date.format(format));
    }

    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
