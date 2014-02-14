/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * MediatorPattern
 * 
 * Created: 24.02.2010
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.design_patterns;

/**
 * @author EremenkoAA
 * @see http://sourcemaking.com/design_patterns/mediator/java/2
 */
public class MediatorPattern {

	static int sleepTime = 2000;
	static int count = 0;
	
	public static void main(String[] args) {
		Mediator mb = new Mediator();
		new Producer(mb).start();
		new Producer(mb).start();
		new Consumer(mb).start();
		new Consumer(mb).start();
		new Consumer(mb).start();
		new Consumer(mb).start();
	}

	static void count() {
		count++;
		if (count == 2) {
			System.out.println();
			count = 0;
		}
	}
	
	// 1. The "intermediary"
	static class Mediator {
		// 4. The Mediator arbitrates
		private boolean slotFull = false;
		private int number;

		public synchronized void storeMessage(int num) {
			// no room for another message
			while (slotFull == true) {
				try {
					wait();
				} catch (InterruptedException e) {
				}
			}
			slotFull = true;
			number = num;
			notifyAll();
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public synchronized int retrieveMessage() {
			// no message to retrieve
			while (slotFull == false)
				try {
					wait();
				} catch (InterruptedException e) {
				}
			slotFull = false;
			notifyAll();
			return number;
		}
	}

	static class Producer extends Thread {
		// 2. Producers are coupled only to the Mediator
		private Mediator med;
		private int id;
		private static int num = 1;

		public Producer(Mediator m) {
			med = m;
			id = num++;
		}

		public void run() {
			int num;
			while (true) {
				med.storeMessage(num = (int) (Math.random() * 100));
				System.out.print("p" + id + "-" + num + "  ");
				count();
			}
		}
	}

	static class Consumer extends Thread {
		// 3. Consumers are coupled only to the Mediator
		private Mediator med;
		private int id;
		private static int num = 1;

		public Consumer(Mediator m) {
			med = m;
			id = num++;
		}

		public void run() {
			while (true) {
				System.out.print("c" + id + "-" + med.retrieveMessage() + "  ");
				count();
			}
		}
	}
}
