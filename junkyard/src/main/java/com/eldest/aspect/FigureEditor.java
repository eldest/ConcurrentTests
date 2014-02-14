/*
 * Copyright (C) 2008-2009 Eremenko EremenkoAA@ics-ware.com
 * 
 * Information Corporative Systems, ICS LLC
 * TelaDictum LLC
 *
 * FigureEditor
 * 
 * Created: 12.05.2010
 * History: 
 *    
 *
 * $Id$
 * 
 */
package com.eldest.aspect;

/**
 * @author EremenkoAA
 *
 */
public class FigureEditor {

	public static class Screen  {
		
	}
	
	public static class FigureEllement {

		public void setXY(int x, int y) {
			
		}

		public void draw(Screen screen) {
		}
	}

	public static class FigureFactory {

		public void makePoint() {
		}

		public void makeLine() {
		}
	}

	public static class Figure {

	}

	public static class Line {

		Point p1, p2;

		/** Get p1 */
		public Point getP1() {
			return p1;
		}

		/** Set p1 */
		public void setP1(Point p1) {
			this.p1 = p1;
		}

		/** Get p2 */
		public Point getP2() {
			return p2;
		}

		/** Set p2 */
		public void setP2(Point p2) {
			this.p2 = p2;
		}

	}

	public static class Point {

		int x, y;

		/** Get x */
		public int getX() {
			return x;
		}

		/** Set x */
		public void setX(int x) {
			this.x = x;
		}

		/** Get y */
		public int getY() {
			return y;
		}

		/** Set y */
		public void setY(int y) {
			this.y = y;
		}

	}

}
