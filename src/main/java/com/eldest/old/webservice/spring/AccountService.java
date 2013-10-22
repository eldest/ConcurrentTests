/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * HelloWorld
 * 
 * Created: 12.10.2009
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.old.webservice.spring;

import java.util.Map;

import javax.jws.WebService;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @author EremenkoAA
 * 
 */
@WebService
public interface AccountService {

	String sayHi(String text);

	/*
	 * Advanced usecase of passing an Interface in. JAX-WS/JAXB does not support
	 * interfaces directly. Special XmlAdapter classes need to be written to
	 * handle them
	 */
	String sayHiToUser(User user);

	/*
	 * Map passing JAXB also does not support Maps. It handles Lists great, but
	 * Maps are not supported directly. They also require use of a XmlAdapter to
	 * map the maps into beans that JAXB can use.
	 */
	@XmlJavaTypeAdapter(IntegerUserMapAdapter.class)
	Map<Integer, User> getUsers();
}
