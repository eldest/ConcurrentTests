/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * KeySample
 * 
 * Created: 28.12.2009
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.cglib;

import net.sf.cglib.core.KeyFactory;

/**
 * @author EremenkoAA
 * 
 */
public class KeySample {
	private interface MyFactory {
		Object newInstance(int a, char[] b, String d);
	}

	public static void main(String[] args) {
		MyFactory f = (MyFactory) KeyFactory.create(MyFactory.class);
		Object key1 = f.newInstance(20, new char[] { 'a', 'b' }, "hello");
		Object key2 = f.newInstance(20, new char[] { 'a', 'b' }, "hello");
		Object key3 = f.newInstance(20, new char[] { 'a', '_' }, "hello");
		
		System.out.println(key1.equals(key2));
		System.out.println(key2.equals(key3));
		
		System.out.println();
		System.out.println(key1);
		System.out.println(key2);
		System.out.println(key3);
	}
}
