/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * Type
 * 
 * Created: 28.12.2009
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.old.enums;

/**
 * @author EremenkoAA
 * 
 */
enum Type {
	INT(true) {
		@Override
		public Object parse(String string) {
			return Integer.valueOf(string);
		}
	},
	
	INTEGER(false) {
		@Override
		public Object parse(String string) {
			return Integer.valueOf(string);
		}
	},
	
	STRING(false) {
		@Override
		public Object parse(String string) {
			return string;
		}
	},
	
	LONG(false) {
		@Override
		public Object parse(String string) {
			return Long.valueOf(string);
		}
		
	}
	;

	// ---------------------- body ----------------------
	
	boolean primitive;

	Type(boolean primitive) {
		this.primitive = primitive;
	}

	public boolean isPrimitive() {
		return primitive;
	}

	public abstract Object parse(String string);
}
