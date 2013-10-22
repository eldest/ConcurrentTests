/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * StupidTest
 * 
 * Created: 29.10.2009
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.old.stupid;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;

import com.eldest.utils.log.SimpleLogger;

/**
 * @author EremenkoAA
 *
 */
public class StupidTest {

	private static SimpleLogger log = new SimpleLogger(StupidTest.class);

	@Before
	public void prepare() {
		BasicConfigurator.configure();
	}

	@Test
	public void stringTest1() {
		String[] sa = new String[]{null, "317", null, "01.01.2009", "01.11.2009"};
		String str = ""; 
		
		for (String string : sa) {
			if (string != null) {
				str = str.concat(string);
			} else {
				str = str.concat("null");
			}
			str = str.concat("_");
		}
		
		log.debug(str);
		
		sa = str.split("_");
		for (String string : sa) {
			if (string == "null") {
				string = null;
			}
		}
		
		for (String string : sa) {
			log.debug(string);
		}
	}
	
	@Test
	public void test1() {
		doSomething("one two three", 1l, 2l, 3l);
	}

	@Test
	public void test3() {
		System.out.println(9 + 2l);
	}

	private void doSomething(String name, Object... objects) {
		int count = 0;
		for (Object object : objects) {
			log.debug("name: %s, object: %s - %s", name.split(" ")[count], object, object.getClass().toString());
			doSomethingElse((Long) object);
			count++;
		}
	}

	private void doSomethingElse(Long var) {
		log.debug("var: %s", var);
	}

	private void doSomethingElse(long var) {
		log.debug("var: %s", var);
	}
}
