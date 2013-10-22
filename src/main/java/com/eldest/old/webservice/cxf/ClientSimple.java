package com.eldest.old.webservice.cxf;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.log4j.BasicConfigurator;

public class ClientSimple {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.getInInterceptors().add(new LoggingInInterceptor());
		factory.getOutInterceptors().add(new LoggingOutInterceptor());
		factory.setServiceClass(HelloWorld.class);
		factory.setAddress("http://localhost:9000/helloWorld");
		HelloWorld client = (HelloWorld) factory.create();

		String reply = client.sayHi("HI");
		System.out.println("Server said: " + reply);
		System.exit(0); 
	}

}
