package com.eldest.old.webservice.spring;

import org.apache.log4j.BasicConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		BasicConfigurator.configure(); // configure log4j

		ApplicationContext context = new FileSystemXmlApplicationContext("./etc/WebService.xml");
	}
}
