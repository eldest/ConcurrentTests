/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * UserImpl
 * 
 * Created: 12.10.2009
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.webservice.cxf;

import javax.xml.bind.annotation.XmlType;

/**
 * @author EremenkoAA
 * 
 */
@XmlType(name = "User")
public class UserImpl implements User {
	String name;

	public UserImpl() {
	}

	public UserImpl(String s) {
		name = s;
	}

	public String getName() {
		return name;
	}

	public void setName(String s) {
		name = s;
	}
}
