package com.eldest.test;

import com.eldest.apt.time.Time;

/**
 * Created with IntelliJ IDEA for SomeTests.
 * User: EremenkoAA
 * Date: 22.10.13
 */
public class TimeUsing {

    public static void main(String[] args) {
        new TimeUsing().doSome();
    }

    @Time(format = "method time: %s ms")
    public void doSome () {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
