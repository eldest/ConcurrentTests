/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * NullObject
 * 
 * Created: 24.02.2010
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.old.design_patterns;

import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.Test;

/**
 * @author EremenkoAA
 *
 */
public class NullObject {

	@Test
	public void first() throws Exception {
		new Application(new NullPrintStream()).go();
	}

	class NullOutputStream extends OutputStream {
		public void write(int b) {
			// Do nothing
		}
	}

	class NullPrintStream extends PrintStream {
		public NullPrintStream() {
			super(new NullOutputStream());
		}
	}

	class Application {
		private PrintStream debugout;

		public Application(PrintStream debugout) {
			this.debugout = debugout;
		}

		public void go() {
			int sum = 0;
			for (int i = 0; i < 10; i++) {
				sum += i;
				debugout.println("i = " + i);
			}
			System.out.println("sum = " + sum);
		}
	}

}
