/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * ApplicationContextTest
 * 
 * Created: 08.06.2009
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.old.spring;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author EremenkoAA
 *
 */
public class ApplicationContextTest {

	static Logger log = Logger.getLogger(ApplicationContextTest.class);

	public static void main(String[] args) throws BeansException, IOException {
		BasicConfigurator.configure(); // configure log4j

		ApplicationContext context = new ClassPathXmlApplicationContext("HelloWorld.xml");
		//ApplicationContext context = new FileSystemXmlApplicationContext("./data/HelloWorld.xml");

		HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
		helloWorld.sayMessage();
	}
}
