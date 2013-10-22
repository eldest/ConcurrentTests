/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * SomeClass
 * 
 * Created: 05.02.2010
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.old.rmi;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author EremenkoAA
 * @see http://habrahabr.ru/blogs/java/74639/
 */
public class PrimeNumbersSearchServer implements ClientRegister {

	/** checkers queue */
	private BlockingQueue<PrimeChecker> availableCheckers = new LinkedBlockingQueue<PrimeChecker>();

	/** current number */
	private BigDecimal number = BigDecimal.ONE;

	public void register(PrimeChecker checker) throws RemoteException {
		availableCheckers.add(checker);
	}

	public void startSearch() {
		while (true) {
			try {
				final PrimeChecker checker = availableCheckers.take();
				final BigDecimal numberToCkeck = increment();

				new Thread(new Runnable() {
					public void run() {
						try {
							if (checker.check(numberToCkeck)) {
								System.out.println(numberToCkeck);
							}
							availableCheckers.add(checker);
						} catch (RemoteException e) {
							System.out.println("Client disconnected or unknown error occured");
						}
					}
				}).start();
			} catch (InterruptedException e) {

			}
		}
	}

	private synchronized BigDecimal increment() {
		number = number.add(BigDecimal.ONE);
		return number;
	}

	public static void main(String[] args) {
		PrimeNumbersSearchServer server = new PrimeNumbersSearchServer();

		try {
			ClientRegister stub = (ClientRegister) UnicastRemoteObject.exportObject(server, 0);
			Registry registry = LocateRegistry.createRegistry(12345);
			registry.bind("ClientRegister", stub);
			server.startSearch();
		} catch (Exception e) {
			System.out.println("Error occured: " + e.getMessage());
			System.exit(1);
		}
	}
}