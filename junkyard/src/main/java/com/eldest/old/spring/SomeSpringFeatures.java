package com.eldest.old.spring;

import java.beans.PropertyEditor;
import java.text.DateFormat;
import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import com.eldest.utils.log.SimpleLogger;


public class SomeSpringFeatures {

	private static SimpleLogger log = new SimpleLogger(SomeSpringFeatures.class);
	
	@BeforeClass
	static public void prepare() {
		BasicConfigurator.configure();
	}
	
	@Test
	public void converterTest() {
//		StringToInteger stringToInteger = new StringToInteger();
//		int intValue = stringToInteger.convert("7");
//		
//		log.debug("intValue = " + intValue);
	}
	
	@Test
	public void propertyEditorTest() {
		PropertyEditor customDateEditor = new CustomDateEditor(DateFormat.getDateInstance(DateFormat.FULL), true);
		customDateEditor.setValue(new Date(System.currentTimeMillis()));
		
		log.debug("Output date from spring converter: " + customDateEditor.getAsText());
	}
}
