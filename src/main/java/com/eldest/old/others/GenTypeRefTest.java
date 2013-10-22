/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * GenTypeRefTest
 * 
 * Created: 27.01.2010
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.old.others;

import com.googlecode.gentyref.GenericTypeReflector;
import com.googlecode.gentyref.TypeToken;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author EremenkoAA
 * @see <a href="http://code.google.com/p/gentyref/wiki/ExampleUsage">source</a>
 */
public class GenTypeRefTest {

	// ---------------------- first test ----------------------

	@Test
	public void firstTest() {

		// returns true, because StringProcessor extends Processor<String>.
		Assert.assertTrue(isStringProcessor(StringProcessor.class));

		// return false, because IntegerProcessor doesn't extend Processor<String> but Processor<Integer>
		Assert.assertFalse(isStringProcessor(IntegerProcessor.class));
	}

	// ---------------------- actors ----------------------

	interface Processor<T> {
		void process(T t);
	}

	class StringProcessor implements Processor<String> {
		public void process(String s) {
			System.out.println("processing " + s);
		}
	}

	class IntegerProcessor implements Processor<Integer> {
		public void process(Integer i) {
			System.out.println("processing " + i);
		}
	}

	/*
	 * Returns true if processorClass extends Processor<String>
	 */
	public boolean isStringProcessor(Class<? extends Processor<?>> processorClass) {
		
		// Use TypeToken to get an instanceof a specific Type
		Type type = new TypeToken<Processor<String>>(){}.getType();
		
		// Use GenericTypeReflector.isSuperType to check if a type is a supertype of another
		return GenericTypeReflector.isSuperType(type, processorClass);
	}

	// ---------------------- ----------- ----------------------
	// ---------------------- second test ----------------------

	@Test
	public void secondTest() throws SecurityException, NoSuchMethodException {
	
		// The return type of the Lister.list() method is List<T>. For StringLister, this would be List<String>. 
		// But, if we simply get the return type from java, we get List<T>: 
		Method listMethod = StringLister.class.getMethod("list");
	
		Type returnType = listMethod.getGenericReturnType();
		// Then returnType is List<T>, not what we want. To get the exact return type, use GenericTypeReflector.getExactReturnType:
		Assert.assertTrue(returnType.toString().equals("java.util.List<T>"));
		System.out.println(returnType.toString());
		
		Type exactReturnType = GenericTypeReflector.getExactReturnType(listMethod, StringLister.class);
		// Now exactReturnType is List<String>. 
		Assert.assertTrue(exactReturnType.toString().equals("java.util.List<java.lang.String>"));
		System.out.println(exactReturnType.toString());
	}

	// ---------------------- actors ----------------------

	abstract class Lister<T> {
		public List<T> list() {
			return null;
		}
	}

	class StringLister extends Lister<String> {
	}

	// ---------------------- ------------- ----------------------
	// ---------------------- another tests ----------------------
	
	@Test
	public void thirdTest() {
		SomeClass.doSomething();
	}
	
	static class SomeClass {
		
		static public <T extends SecondClass> void doSomething() {
			Type type = new TypeToken<T>(){}.getType();
//			type = GenericTypeReflector.erase(type)
			System.out.println(GenericTypeReflector.erase(type).getName());
		}
	}
	
	static class FirstClass extends Object {
		
	}
	
	static class SecondClass extends FirstClass {
		
	} 
	
}
