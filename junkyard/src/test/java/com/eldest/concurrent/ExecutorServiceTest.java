package com.eldest.concurrent;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA for ConcurrentTests.
 * User: EremenkoAA
 * Date: 10.10.13
 *
 * @see <a href="http://tutorials.jenkov.com/java-util-concurrent/executorservice.html">tutorials.jenkov.com</a>
 */
public class ExecutorServiceTest {

    static final int ONE_SEC = 1000;
    static final int TEN_SEC = 10 * 1000;

    @Test
    public void testFixedThreadPool() throws Throwable {
        System.out.println();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i++) {
            executorService.execute(new LocalThread("Thread-" + i));
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            System.out.print(".");
            Thread.sleep(ONE_SEC);
        }
    }

    @Test
    public void testCachedThreadPool() throws Throwable {
        System.out.println();

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            executorService.execute(new LocalThread("Thread-" + i));
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            System.out.print(".");
            Thread.sleep(ONE_SEC);
        }
    }

    @Test
    public void testScheduledThreadPool() throws Throwable {
        System.out.println();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

        for (int i = 0; i < 10; i++) {
            executorService.schedule(new LocalThread("Thread-" + i), 5, TimeUnit.SECONDS);
        }

        executorService.shutdown();

        while (!executorService.isTerminated()) {
            System.out.print(".");
            Thread.sleep(ONE_SEC);
        }
    }

    @Test
    public void testSingleThread() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> control = executorService.submit(new LocalThread("SingleThread"));
        try {
            control.get(9, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            System.out.println("It takes too much time, let's interrupt it");
            control.cancel(true);
        }

    }

    private static class LocalThread extends Thread {

        private LocalThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.print(String.format(" %s ", getName()));

            try {
                Thread.sleep(TEN_SEC);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
