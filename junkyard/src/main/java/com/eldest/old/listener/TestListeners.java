package com.eldest.old.listener;

import java.util.ArrayList;

public class TestListeners {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// ...
		A a = new A();
		B b = new B();
		B c = new B();
		B d = new B();

		a.addListener(b);
		a.addListener(c);
		a.addListener(d);

		a.doSomething(); // Objects b and c do doEvent

	}

}

interface AListener {
	public void doEvent();
}

// -------------------------------------------------------------
class A {
	ArrayList<AListener> listeners = new ArrayList<AListener>();

	public void addListener(AListener listener) {
		listeners.add(listener);
	}

	public void doSomething() {
		System.out.println("First work is done.");
		// Do something for notify listeners
		for (int i = 0; i < listeners.size(); i++) {
			try {
				AListener tl = (AListener) listeners.get(i);
				tl.doEvent(); // class A don't know who listened him
			} catch (Exception e) {
				System.out.println("No item in array.");
				// TODO: handle exception
			}
		}
	}
}

// --------------------------------------------------------------
class B implements AListener {
	private static int count = 1;

	public void doEvent() {
		System.out.println("Listner " + count + " is worked.");
		count++;
		// Something happen in class A
	}
}
