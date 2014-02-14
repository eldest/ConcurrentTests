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
package com.eldest.webservice.spring;

import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author EremenkoAA
 * 
 */
@WebService(serviceName = "AccountService")
public class AccountServiceEndpoint implements AccountService {

	@Autowired
	private AccountServiceImpl accountService;


	@WebMethod
	public String sayHi(String text) {
		return accountService.sayHi(text);
	}
	
	@WebMethod
	public String sayHiToUser(User user) {
		return accountService.sayHiToUser(user);
	}

	@WebMethod
	public Map<Integer, User> getUsers() {
		return accountService.getUsers();
	}

}
