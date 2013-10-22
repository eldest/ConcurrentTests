/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * BlockingQueueTest
 * 
 * Created: 30.10.2009
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.old.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.BasicConfigurator;

import com.eldest.utils.log.SimpleLogger;

/**
 * @author EremenkoAA
 * 
 */
public class BlockingQueueTest {
//	private static SimpleLogger log = new SimpleLogger(BlockingQueueTest.class);
	
	public static void main(String[] args) {
		BasicConfigurator.configure();
		new Setup().main();
	}
		
}

class Setup {
	void main() {
		BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);

		Producer producer = new Producer(queue);
		Consumer consumer1 = new Consumer(queue);
		Consumer consumer2 = new Consumer(queue);

		new Thread(producer, "Producer").start();
		new Thread(consumer1, "Consumer-1").start();
		new Thread(consumer2, "Consumer-2").start();
	}
}

class Producer implements Runnable {
	private static SimpleLogger log = new SimpleLogger(Producer.class);
	
	private final BlockingQueue<String> queue;

	int number = 0;

	Producer(BlockingQueue<String> q) {
		queue = q;
	}

	public void run() {
		try {
			while (true) {
				queue.put(produce());
			}
		} catch (InterruptedException ex) {
			log.error(ex, "Got error in Producer");
		}
	}

	String produce() throws InterruptedException {
		Thread.sleep(1000);
		log.info("Produce product numder %s", number);
		return Producer.class.getSimpleName() + "_" + number++;
	}
}

class Consumer implements Runnable {
	private static SimpleLogger log = new SimpleLogger(Consumer.class);
	
	private final BlockingQueue<String> queue;

	Consumer(BlockingQueue<String> q) {
		queue = q;
	}

	public void run() {
		try {
			while (true) {
				consume(queue.take());
			}
		} catch (InterruptedException ex) {
			log.error(ex, "Got error in Consumer");
		}
	}

	void consume(String x) {
		log.info("Consuming object number %s", x.split("_")[1]);
	}
}