package com.eldest.concurrent;

import com.eldest.utils.log.SimpleLogger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    private static SimpleLogger log = new SimpleLogger(LockTest.class);

    public static void main(String[] args) {

    }

    private static class Service {
        private final Lock lock = new ReentrantLock();

        public void init() {
            lock.lock();

            try {
                log.info("Initializing...");
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                log.error(e);
            } finally {
                lock.unlock();
            }

        }

        public void doSome() {
            log.info("doSome...");
            check();
        }

        public void doSomeElse() {
            log.info("doSomeElse...");
            check();
        }

        public void check() {
            try {
                if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                    try {
                        // manipulate protected state
                    } finally {
                        lock.unlock();
                    }
                } else {
                    // perform alternative actions
                }
            } catch (InterruptedException e) {
                log.error(e);
            }
        }
    }
}
