/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * SimpleTests
 * 
 * Created: 09.10.2009
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.old.net;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author EremenkoAA
 *
 */
public class SimpleTests {

	static Logger log = Logger.getLogger(SimpleTests.class);
	
	
	@BeforeClass
	public static void prepare() {
		BasicConfigurator.configure();
	}
	
	@Test
	public void justTest1() {
		int count = 0;
		while (true) {
			log.debug("This is a row number " + count);
			count++;
			
//			if (count > 100000) {
//				break;
//			}
		}
	}
	
	@Test
	public void justTest2() {
		int count = 0;
		while (true) {
			log.debug("This is a row number " + count);
			count++;
			
			try {
				Thread.currentThread().join(100);
			} catch (InterruptedException e) {
				log.error("InterruptedException", e);
			}
		}
	}
}
