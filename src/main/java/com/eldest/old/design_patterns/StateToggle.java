/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * StateToggle
 * 
 * Created: 24.02.2010
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.old.design_patterns;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author EremenkoAA
 *
 */
public class StateToggle {

	public static void main(String[] args) throws IOException {
		new StateToggle().run();
	}
	
	public void run() throws IOException {
		InputStreamReader is = new InputStreamReader(System.in);
		Button btn = new Button();
		while (true) {
			System.out.print("Press 'Enter'");
			is.read();
			is.read();
			btn.push();
		}
	}

	class Button {
		private State current;

		public Button() {
			current = OFF.instance();
		}

		public void setCurrent(State s) {
			current = s;
		}

		public void push() {
			current.push(this);
		}
	}

	interface State {
		public void push(Button b);
	}

	static class ON implements State {
		private static ON inst = new ON();

		public static State instance() {
			return inst;
		}

		public void push(Button b) {
			b.setCurrent(OFF.instance());
			System.out.println("   turning OFF");
		}
	}

	static class OFF implements State {
		private static OFF inst = new OFF();

		public static State instance() {
			return inst;
		}

		public void push(Button b) {
			b.setCurrent(ON.instance());
			System.out.println("   turning ON");
		}
	}
}