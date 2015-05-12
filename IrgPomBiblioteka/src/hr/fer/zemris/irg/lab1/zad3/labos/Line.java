package hr.fer.zemris.irg.lab1.zad3.labos;

import hr.fer.zemris.irg.lab1.zad2.src.labos.Colors;

import java.awt.Point;

import javax.media.opengl.GL;

/**
 * 
 * @author Ivan Hrastinski
 * @version 1
 */
public class Line {
	// The first point of the line.
	private Point start;
	// The second point of the line.
	private Point end;
	// The collor of the line, by defual black.
	private Colors color = Colors.BLACK;

	/**
	 * Basic constructor with 2 points.
	 * 
	 * @param start
	 *            the first point.
	 * @param end
	 *            the second point.
	 */
	public Line(Point start, Point end) {
		this.start = start;
		this.end = end;
	}

	/**
	 * Constructor which takes the coordinates separetly.
	 * 
	 * @param startX
	 *            x coordinate of the first point.
	 * @param startY
	 *            y coordinate of the first point.
	 * @param endX
	 *            x coordinate of the second point.
	 * @param endY
	 *            y coordinate of the second point.
	 */
	public Line(int startX, int startY, int endX, int endY) {
		this.start = new Point(startX, startY);
		this.end = new Point(endX, endY);
	}

	/**
	 * The basic method which draws the line point by point.
	 * 
	 * @param gl
	 *            the canvas we draw on.
	 */
	public void draw(GL gl) {

		this.drawBresenham(gl, start.x, start.y, end.x, end.y);
	}

	/**
	 * The implementation of the Bresenham algorithm for drawing a line point by
	 * point.
	 * 
	 * @param gl
	 *            the canvas we draw on.
	 * @param xs
	 *            first point x coordinate.
	 * @param ys
	 *            first point y coordinate.
	 * @param xe
	 *            second point x coordinate.
	 * @param ye
	 *            second point y coordinate.
	 */
	private void drawBresenham(GL gl, int xs, int ys, int xe, int ye) {
		gl.glColor3d(this.color.getR(), this.color.getG(), this.color.getB());

		if (xs <= xe) {
			if (ys <= ye) {

				drawBresenhamWholeNumber2(gl, xs, ys, xe, ye);
			} else {

				drawBresenhamWholeNumber3(gl, xs, ys, xe, ye);
			}
		} else {
			if (ys >= ye) {

				drawBresenhamWholeNumber2(gl, xe, ye, xs, ys);
			} else {

				drawBresenhamWholeNumber3(gl, xe, ye, xs, ys);
			}
		}

	}

	/**
	 * The method which cuts the line drawn only to a specific rectangle.
	 * 
	 * @param gl
	 *            The canvas we draw on.
	 * @param xMin
	 *            The minimum x coordinate of the rectangle.
	 * @param yMin
	 *            the minimum y coordinate of the rectangle.
	 * @param xMax
	 *            the maximum x coordinate of the rectangle.
	 * @param yMax
	 *            the maximum y coordinate of the rectangle.
	 */
	public void drawCut(GL gl, int xMin, int yMin, int xMax, int yMax) {
		cutAndDraw(gl, xMin, yMin, xMax, yMax, start.x, start.y, end.x, end.y);

	}

	/**
	 * The method which uses the Cohen Sutherland algorithm to cut the space for
	 * drawing lines.
	 * 
	 * @param gl
	 *            The canvas we draw on.
	 * @param xMin
	 *            The minimum x coordinate of the rectangle.
	 * @param yMin
	 *            the minimum y coordinate of the rectangle.
	 * @param xMax
	 *            the maximum x coordinate of the rectangle.
	 * @param yMax
	 *            the maximum y coordinate of the rectangle.
	 * @param xs
	 *            the x coordinate of the first point.
	 * @param ys
	 *            the y coordinate of the first point.
	 * @param xe
	 *            the x coordinate of the second point.
	 * @param ye
	 *            the y coordinate of the second point.
	 */
	private void cutAndDraw(GL gl, int xMin, int yMin, int xMax, int yMax,
			int xs, int ys, int xe, int ye) {
		String startFlags = getFlags(xs, ys, xMin, yMin, xMax, yMax);
		String endFlags = getFlags(xe, ye, xMin, yMin, xMax, yMax);
		if (compareFlags(startFlags, endFlags)) {
			if (startFlags.equals("0000")) {
				if (endFlags.equals("0000")) {
					drawBresenham(gl, xs, ys, xe, ye);
				} else {
					int newX;
					int newY;
					switch (endFlags.indexOf("1")) {
					case 0:
						newX = getCordinate(ys, xs, ye, xe, yMax);
						cutAndDraw(gl, xMin, yMin, xMax, yMax, xs, ys, newX,
								yMax);
						break;
					case 1:
						newX = getCordinate(ys, xs, ye, xe, yMin);
						cutAndDraw(gl, xMin, yMin, xMax, yMax, xs, ys, newX,
								yMin);
						break;
					case 2:
						newY = getCordinate(xs, ys, xe, ye, xMax);
						cutAndDraw(gl, xMin, yMin, xMax, yMax, xs, ys, xMax,
								newY);
						break;
					case 3:
						newY = getCordinate(xs, ys, xe, ye, xMin);
						cutAndDraw(gl, xMin, yMin, xMax, yMax, xs, ys, xMin,
								newY);
						break;
					}
				}
			} else {
				int newX;
				int newY;
				switch (startFlags.indexOf("1")) {
				case 0:
					newX = getCordinate(ys, xs, ye, xe, yMax);
					cutAndDraw(gl, xMin, yMin, xMax, yMax, newX, yMax, xe, ye);
					break;
				case 1:
					newX = getCordinate(ys, xs, ye, xe, yMin);
					cutAndDraw(gl, xMin, yMin, xMax, yMax, newX, yMin, xe, ye);
					break;
				case 2:
					newY = getCordinate(xs, ys, xe, ye, xMax);
					cutAndDraw(gl, xMin, yMin, xMax, yMax, xMax, newY, xe, ye);
					break;
				case 3:
					newY = getCordinate(xs, ys, xe, ye, xMin);
					cutAndDraw(gl, xMin, yMin, xMax, yMax, xMin, newY, xe, ye);
					break;

				}
			}

		}
	}

	/**
	 * Compares the flags for the Cohen Sutherland algorithm, if any part of the
	 * line is inside the rectangle it returns true, else it returns false.
	 * 
	 * @param startFlags
	 *            the flags for the first point
	 * @param endFlags
	 *            the flags for the second point.
	 * @return true if the line is inside (any part of it) false else.
	 */
	private boolean compareFlags(String startFlags, String endFlags) {
		for (int i = 0; i < 4; i++) {
			if ((startFlags.charAt(i) == '1') && (endFlags.charAt(i) == '1')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Calculates the remaining coordinate of a point on a line specified by two
	 * points and one coordinate of the point.(returns y be default if you need
	 * x just swap the x and y coordinates).
	 * 
	 * @param x1
	 *            the x coordinate of the first point.
	 * @param y1
	 *            the y coordinate of the first point.
	 * @param x2
	 *            the x coordinate of the second point.
	 * @param y2
	 *            the y coordinate of the second second.
	 * @param x
	 *            the x coordinate of the wanted point
	 * @return the wanted y coordinate.
	 */
	private int getCordinate(int x1, int y1, int x2, int y2, int x) {
		if (x2 - x1 == 0)
			return 0;
		return ((y2 - y1) * (x - x1) / (x2 - x1) + y1);
	}

	private String getFlags(int x, int y, int xMin, int yMin, int xMax, int yMax) {
		String flags = "";
		if (y > yMax) {
			flags += "1";
		} else {
			flags += "0";
		}
		if (y < yMin) {
			flags += "1";
		} else {
			flags += "0";
		}
		if (x > xMax) {
			flags += "1";
		} else {
			flags += "0";
		}
		if (x < xMin) {
			flags += "1";
		} else {
			flags += "0";
		}
		return flags;
	}

	/**
	 * Draws a red line using the implemented line method.
	 * 
	 * @param gl
	 *            the canvas we draw on.
	 */
	public void drawRedLine(GL gl) {
		float stepX;
		float stepY;
		if (start.x > end.x) {
			stepY = -4;
		} else {
			stepY = 4;
		}
		if (start.y > end.y) {
			stepX = 4;
		} else {
			stepX = -4;
		}
		gl.glColor3d(Colors.RED.getR(), Colors.RED.getG(), Colors.RED.getB());
		gl.glBegin(GL.GL_LINE_STRIP);

		gl.glVertex3f(start.x + stepX, start.y + stepY, 0.0f);
		gl.glVertex3f(end.x + stepX, end.y + stepY, 0.0f);

		gl.glEnd();
	}

	/**
	 * The Bresenham algorithm for drawing a line for the angle between 0 and 90
	 * degrees.
	 * 
	 * @param gl
	 *            the canvas we draw on.
	 * 
	 * @param xs
	 *            the x coordinate of the first point.
	 * @param ys
	 *            the y coordinate of the first point.
	 * @param xe
	 *            the x coordinate of the second point.
	 * @param ye
	 *            the y coordinate of the second point.
	 */
	private void drawBresenhamWholeNumber2(GL gl, int xs, int ys, int xe, int ye) {
		gl.glBegin(GL.GL_POINTS);
		if ((ye - ys) <= (xe - xs)) {
			int a = 2 * (ye - ys);
			int yc = ys;

			int yf = -(xe - xs);
			int korekcija = -2 * (xe - xs);

			for (int x = xs; x <= xe; x++) {

				gl.glVertex2i(x, yc);

				yf = yf + a;
				if (yf >= 0) {
					yf = yf + korekcija;
					yc = yc + 1;
				}
			}

		} else {
			int x = xe;
			xe = ye;
			ye = x;
			x = xs;
			xs = ys;
			ys = x;
			int a = 2 * (ye - ys);

			int yc = ys;
			int yf = -(xe - xs);
			int korekcija = -2 * (xe - xs);

			for (x = xs; x <= xe; x++) {
				gl.glVertex2i(yc, x);
				yf = yf + a;
				if (yf >= 0) {
					yf = yf + korekcija;
					yc = yc + 1;
				}
			}

		}
		gl.glEnd();
	}

	/**
	 * * The Bresenham algorithm for drawing a line for the angle between 0 and
	 * -90 degrees.
	 * 
	 * @param gl
	 *            The canvas we draw on.
	 * 
	 * @param xs
	 *            the x coordinate of the first point.
	 * @param ys
	 *            the y coordinate of the first point.
	 * @param xe
	 *            the x coordinate of the second point.
	 * @param ye
	 *            the y coordinate of the second point.
	 */
	private void drawBresenhamWholeNumber3(GL gl, int xs, int ys, int xe, int ye) {
		gl.glBegin(GL.GL_POINTS);
		if (-(ye - ys) <= xe - xs) {
			int a = 2 * (ye - ys);
			int yc = ys;
			int yf = (xe - xs);
			int korekcija = 2 * (xe - xs);
			for (int x = xs; x <= xe; x++) {
				gl.glVertex2i(x, yc);
				yf = yf + a;
				if (yf <= 0) {
					yf = yf + korekcija;
					yc = yc - 1;
				}
			}
		} else {
			int x = xe;
			xe = ys;
			ys = x;
			x = xs;
			xs = ye;
			ye = x;
			int a = 2 * (ye - ys);
			int yc = ys;
			int yf = (xe - xs);
			int korekcija = 2 * (xe - xs);
			for (x = xs; x <= xe; x++) {
				gl.glVertex2i(yc, x);

				yf = yf + a;
				if (yf <= 0) {
					yf = yf + korekcija;
					yc = yc - 1;
				}
			}
		}
		gl.glEnd();

	}

}
