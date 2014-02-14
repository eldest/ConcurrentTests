/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * EnumTest
 * 
 * Created: 28.12.2009
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.enums;

import org.junit.Test;

/**
 * @author EremenkoAA
 *
 */
public class EnumTest {

	@Test
	public void test1() {
		Type type = Type.INT;
		System.out.println(type.parse("0123"));
	}
}
