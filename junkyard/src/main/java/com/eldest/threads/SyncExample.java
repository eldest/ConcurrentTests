package com.eldest.threads;

public class SyncExample {
    private static final Object lockObject = new Object();
    private static int x, y;

    private static class Thread1 extends Thread {
        public void run() {
            synchronized (lockObject) {
                x = y = 0;
                System.out.println(x);
            }
        }
    }

    private static class Thread2 extends Thread {
        public void run() {
            synchronized (lockObject) {
                x = y = 1;
                System.out.println(y);
            }
        }
    }

    public static void main(String[] args) {
        new Thread1().start();
        new Thread2().start();
    }
}