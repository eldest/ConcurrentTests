/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * UserAdapter
 * 
 * Created: 12.10.2009
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.webservice.spring;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author EremenkoAA
 * 
 */
public class UserAdapter extends XmlAdapter<UserImpl, User> {
	public UserImpl marshal(User v) throws Exception {
		if (v instanceof UserImpl) {
			return (UserImpl) v;
		}
		return new UserImpl(v.getName());
	}

	public User unmarshal(UserImpl v) throws Exception {
		return v;
	}
}
