/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * ChainBefore
 * 
 * Created: 24.02.2010
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.old.design_patterns;

import java.util.Random;

/**
 * @author EremenkoAA
 * @see http://sourcemaking.com/design_patterns/chain_of_responsibility/java/2
 */
public class ChainBefore {

	public static void main(String[] args) {
		Image[] input = { new IR(), new IR(), new LS(), new IR(), new LS(), new LS() };
		Processor[] procs = { new Processor(), new Processor(), new Processor() };

		for (int i = 0, j; i < input.length; i++) {
			j = 0;
			while (!procs[j].handle(input[i]))
				j = (j + 1) % procs.length;
		}
	}

	interface Image {
		String process();
	}

	static class IR implements Image {
		public String process() {
			return "IR";
		}
	}

	static class LS implements Image {
		public String process() {
			return "LS";
		}
	}

	static class Processor {
		private static Random rn = new Random();
		private static int nextId = 1;
		private int id = nextId++;

		public boolean handle(Image img) {
			if (rn.nextInt(2) != 0) {
				System.out.println("   Processor " + id + " is busy");
				return false;
			} else {
				System.out.println("Processor " + id + " - " + img.process());
				return true;
			}
		}
	}
}
