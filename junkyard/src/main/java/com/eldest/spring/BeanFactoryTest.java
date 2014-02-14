package com.eldest.spring;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class BeanFactoryTest {

	static Logger log = Logger.getLogger(BeanFactoryTest.class);

	public static void main(String[] args) throws BeansException, IOException {
		BasicConfigurator.configure(); // configure log4j

		Resource res = new FileSystemResource("./Data/HelloWorld.xml");
		BeanFactory beanFactory = new XmlBeanFactory(res);
		HelloWorld helloWorld = (HelloWorld) beanFactory.getBean("helloWorld");
		helloWorld.sayMessage();
	}
}