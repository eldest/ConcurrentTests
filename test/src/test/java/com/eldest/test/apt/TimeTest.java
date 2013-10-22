package com.eldest.test.apt;

import com.eldest.apt.time.Time;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA for SomeTests.
 * User: EremenkoAA
 * Date: 22.10.13
 */
public class TimeTest {

    @Test
    @Time(format = "method time: %s ms")
    public void testTimeAnnotation() throws Throwable {
        Thread.sleep(1000);
    }
}
