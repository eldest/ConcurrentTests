/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * Bean
 * 
 * Created: 28.12.2009
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.cglib;

import java.beans.PropertyChangeListener;
import java.io.Serializable;

/**
 * @author EremenkoAA
 * 
 */
public abstract class Bean implements Serializable {
	private static final long serialVersionUID = 1L;

	String sampleProperty;

	abstract public void addPropertyChangeListener(PropertyChangeListener listener);

	abstract public void removePropertyChangeListener(PropertyChangeListener listener);

	public String getSampleProperty() {
		return sampleProperty;
	}

	public void setSampleProperty(String value) {
		this.sampleProperty = value;
	}

	public String toString() {
		return "sampleProperty is " + sampleProperty;
	}

}
