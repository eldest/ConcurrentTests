/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * PrimeChecker
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
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author EremenkoAA
 * 
 */
public interface PrimeChecker extends Remote {
	public boolean check(BigDecimal number) throws RemoteException;
}