/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * PrimeNumbersSearchClient
 * 
 * Created: 05.02.2010
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.rmi;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author EremenkoAA
 * @see http://habrahabr.ru/blogs/java/74639/
 */
public class PrimeNumbersSearchClient implements PrimeChecker {

	public boolean check(BigDecimal number) throws RemoteException {
		boolean isPrime = true;
		BigDecimal i = new BigDecimal(2);

		/* mistaken? */
		BigDecimal sqrt = new BigDecimal(Math.sqrt(number.doubleValue()));
		BigDecimal div = number.divide(sqrt, BigDecimal.ROUND_UP);

		/* sqrt must be equal or greater than root of number */
		if (div.compareTo(sqrt) == 1) {
			sqrt = div;
		}

		/* while i less than sqrt */
		while (i.compareTo(sqrt) == -1) {
			if (number.divideAndRemainder(i)[1].compareTo(BigDecimal.ZERO) == 0) {
				isPrime = false;
				break;
			}
			i = i.add(BigDecimal.ONE);
		}

		System.out.println(number + ((isPrime) ? " is prime" : " is not prime"));
		return isPrime;
	}

	public static void main(String[] args) {
		PrimeNumbersSearchClient client = new PrimeNumbersSearchClient();

		try {
			Registry registry = LocateRegistry.getRegistry(null, 12345);
			ClientRegister server = (ClientRegister) registry.lookup("ClientRegister");
			PrimeChecker stub = (PrimeChecker) UnicastRemoteObject.exportObject(client, 0);
			server.register(stub);
		} catch (Exception e) {
			System.out.println("Error occured: " + e.getMessage());
			System.exit(1);
		}
	}
}