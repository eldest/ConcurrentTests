package com.eldest.old.junit;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SubscriptionTest {

	@Test
	public void test_returnEuro() {
		System.out.println("Test if pricePerMonth returns Euro...");
		Subscription S = new Subscription(200, 200);
		assertTrue(S.pricePerMonth() == 1.0);
	}

	@Test
	public void test_roundUp() {
		System.out.println("Test if pricePerMonth rounds up correctly...");
		Subscription S = new Subscription(200, 3);
		assertTrue(S.pricePerMonth() == 0.67);
	}

	@Test
	public void test_3() {
		String[] args = { "1", "2", "3" };
		if (args instanceof Object) { // 1
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
	}

}
