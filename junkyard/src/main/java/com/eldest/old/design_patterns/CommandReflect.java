/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * Command
 * 
 * Created: 24.02.2010
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.old.design_patterns;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author EremenkoAA
 * @see http://sourcemaking.com/design_patterns/command/java/2
 */
public class CommandReflect {
	private int state;

	public CommandReflect(int in) {
		state = in;
	}

	public static void main(String[] args) {
		CommandReflect[] objs = { new CommandReflect(1), new CommandReflect(2) };

		System.out.print("Normal call results: ");
		System.out.print(objs[0].addOne(new Integer(3)) + " ");
		System.out.print(objs[1].addTwo(new Integer(4), new Integer(5)) + " ");
		
		Command[] cmds = { new Command(objs[0], "addOne", new Integer[] { new Integer(3) }),
				new Command(objs[1], "addTwo", new Integer[] { new Integer(4), new Integer(5) }) };
		System.out.print("\nReflection results:  ");
		for (int i = 0; i < cmds.length; i++)
			System.out.print(cmds[i].execute() + " ");
		System.out.println();
	}

	public int addOne(Integer one) {
		return state + one.intValue();
	}

	public int addTwo(Integer one, Integer two) {
		return state + one.intValue() + two.intValue();
	}

	// ---------------------- Command ----------------------

	static public class Command {
		private Object receiver; // the "encapsulated" object
		private Method action; // the "pre-registered" request
		private Object[] args; // the "pre-registered" arg list

		@SuppressWarnings("unchecked")
		public Command(Object obj, String methodName, Object[] arguments) {
			receiver = obj;
			args = arguments;
			Class cls = obj.getClass(); // get the object's "Class"
			Class[] argTypes = new Class[args.length];

			for (int i = 0; i < args.length; i++)
				// get the "Class" for each
				argTypes[i] = args[i].getClass(); //    supplied argument
			// get the "Method" data structure with the correct name and signature
			try {
				action = cls.getMethod(methodName, argTypes);
			} catch (NoSuchMethodException e) {
				System.out.println(e);
			}
		}

		public Object execute() {
			// in C++, you do something like --- return receiver->action( args ); 
			try {
				return action.invoke(receiver, args);
			} catch (IllegalAccessException e) {
				System.out.println(e);
			} catch (InvocationTargetException e) {
				System.out.println(e);
			}
			return null;
		}
	}

}
