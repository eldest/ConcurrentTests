/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * User
 * 
 * Created: 12.10.2009
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.webservice.spring;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * @author EremenkoAA
 * 
 */
@XmlJavaTypeAdapter(UserAdapter.class)
public interface User {

	String getName();
}
