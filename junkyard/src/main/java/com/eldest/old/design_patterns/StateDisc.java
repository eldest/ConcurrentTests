/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * State
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
public class StateDisc {
	
	public static void main(String[] args) throws IOException {
		new StateDisc().run();
	}
	
	public void run() throws IOException {
			InputStreamReader is = new InputStreamReader(System.in);
			Chain chain = new Chain();
			while (true) {
				System.out.print("Press 'Enter'");
				is.read();
				is.read();
				chain.pull();
			}
		}

	//	Chain
	public class Chain {
		private State current;

		public Chain() {
			current = new Off();
		}

		public void setState(State s) {
			current = s;
		}

		public void pull() {
			current.pull(this);
		}
	}

	//	State
	abstract class State {
		public void pull(Chain wrapper) {
			wrapper.setState(new Off());
			System.out.println("   turning off");
		}
	}

	class Off extends State {
		public void pull(Chain wrapper) {
			wrapper.setState(new Low());
			System.out.println("   low speed");
		}
	}

	class Low extends State {
		public void pull(Chain wrapper) {
			wrapper.setState(new Medium());
			System.out.println("   medium speed");
		}
	}

	class Medium extends State {
		public void pull(Chain wrapper) {
			wrapper.setState(new High());
			System.out.println("   high speed");
		}
	}

	class High extends State {
	}
	
}