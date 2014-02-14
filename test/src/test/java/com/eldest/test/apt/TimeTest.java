package com.eldest.test.apt;

import com.eldest.annotations.Time;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA for SomeTests.
 * User: EremenkoAA
 * Date: 22.10.13
 */
public class TimeTest {

    @Test
    public void testTimeAnnotation() throws Throwable {
        doSome();
    }

    @Time(format = "method time: %s ms")
    public void doSome () {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
