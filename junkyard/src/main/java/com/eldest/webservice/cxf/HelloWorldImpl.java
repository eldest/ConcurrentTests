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
package com.eldest.webservice.cxf;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.jws.WebService;

/**
 * @author EremenkoAA
 * 
 */
@WebService(endpointInterface = "com.eldest.webservice.cxf.HelloWorld", serviceName = "HelloWorld")
public class HelloWorldImpl implements HelloWorld {
	Map<Integer, User> users = new LinkedHashMap<Integer, User>();

	@Override
	public String sayHi(String text) {
		System.out.println("sayHi called");
		return "Hello " + text;
	}

	@Override
	public String sayHiToUser(User user) {
		System.out.println("sayHiToUser called");
		users.put(users.size() + 1, user);
		return "Hello " + user.getName();
	}

	@Override
	public Map<Integer, User> getUsers() {
		System.out.println("getUsers called");
		return users;
	}

}
