/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * ClientRegister
 * 
 * Created: 05.02.2010
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author EremenkoAA
 * 
 */
public interface ClientRegister extends Remote {
	public void register(PrimeChecker checker) throws RemoteException;
}
