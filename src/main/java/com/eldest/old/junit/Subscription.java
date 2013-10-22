package com.eldest.old.junit;

import com.eldest.old.annotations.ClassPreamble;

@ClassPreamble(author = "Eldest", date = "21.01.09", reviewers = { "me" })
public class Subscription {

	private int price; // subscription total price in euro-cent
	private int length; // length of subscription in months

	// constructor :
	public Subscription(int p, int n) {
		price = p;
		length = n;
	}

	/**
	 * Calculate the monthly subscription price in euro, rounded up to the
	 * nearest cent.
	 */
	public double pricePerMonth() {
        return (double) price / (double) length;
	}

	/**
	 * Call this to cancel/nullify this subscription.
	 */
	public void cancel() {
		length = 0;
	}

}
