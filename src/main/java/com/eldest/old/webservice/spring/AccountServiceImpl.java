/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * HelloWorldImpl
 * 
 * Created: 12.10.2009
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.old.webservice.spring;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author EremenkoAA
 * 
 */
public class AccountServiceImpl implements AccountService {
	Map<Integer, User> users = new LinkedHashMap<Integer, User>();

	public String sayHi(String text) {
		System.out.println("sayHi called");
		return "Hello " + text;
	}

	public String sayHiToUser(User user) {
		System.out.println("sayHiToUser called");
		users.put(users.size() + 1, user);
		return "Hello " + user.getName();
	}

	public Map<Integer, User> getUsers() {
		System.out.println("getUsers called");
		return users;
	}

}
