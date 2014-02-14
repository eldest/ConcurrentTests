package com.eldest.math;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

public class SimpleMath {

	final static String SEPARATOR = "\\p{Punct}";

	public static String plus(String s1, String s2) {

		String[] num_1 = s1.split(SEPARATOR);
		String[] num_2 = s2.split(SEPARATOR);

		int tailCapacity = 0;
		int tail_1 = 0, tail_2 = 0;

		if (!num_1[1].isEmpty()) {
			tail_1 = Integer.valueOf(num_1[1]);
		}
		
		if (!num_2[1].isEmpty()) {
			tail_2 = Integer.valueOf(num_2[1]);
		}
		
		if (tail_1 > tail_2) {
			tailCapacity = num_1[1].length();
		} else {
			tailCapacity = num_2[1].length();
		}
		
		

		BigInteger main = new BigInteger(num_1[0]).add(new BigInteger(num_2[0]));
		BigInteger tail = new BigInteger(num_1[1]).add(new BigInteger(num_2[1]));

		System.out.println(String.format("main = %s, tail = %s", main, tail));

		return main.toString();
	}

	@Test
	public void testIt() {
		System.out.println(plus("9223372036854775807.56", "1000000000000000000.789"));
	}
	
	@Test
	public void testIt2() {
		System.out.println(new BigDecimal("9223372036854775807.56").add(new BigDecimal("1000000000000000000.789")).toString());
	}
}
