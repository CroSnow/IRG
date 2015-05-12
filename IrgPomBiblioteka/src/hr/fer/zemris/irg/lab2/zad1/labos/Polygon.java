package hr.fer.zemris.irg.lab2.zad1.labos;

import hr.fer.zemris.irg.lab1.zad2.src.labos.Colors;

import java.awt.Point;
import java.util.ArrayList;

import javax.media.opengl.GL;

/**
 * The class to store a polygon and draw it.
 * 
 * @author Ivan Hrastinski
 * @version 1
 */
public class Polygon {
	// The points of the polygon.
	private ArrayList<Point> points;
	// The defualt color for drawing the outskirt lines.
	private Colors color = Colors.BLACK;
	// The helping parameter field a.
	private ArrayList<Integer> a = new ArrayList<Integer>();
	// The helping parameter field b.
	private ArrayList<Integer> b = new ArrayList<Integer>();
	// The helping parameter field c.
	private ArrayList<Integer> c = new ArrayList<Integer>();
	// The minimum x coordinate.
	private int minX;
	// The maximum x coordinate.
	private int maxX;
	// The minimum y coordinate.
	private int minY;
	// The maximum y coordinate.
	private int maxY;

	/**
	 * The constructor which uses the points given to create all the variables
	 * needet for this polygon.
	 * 
	 * @param points
	 *            the points of the polygon.
	 */
	Polygon(ArrayList<Point> points) {
		this.points = new ArrayList<Point>();
		this.points.addAll(points);
		minX = this.points.get(this.points.size() - 1).x;
		maxX = minX;
		minY = this.points.get(this.points.size() - 1).y;
		maxY = minY;
		for (int i = 0; i < this.points.size() - 1; i++) {
			a.add(this.points.get(i).y - this.points.get(i + 1).y);
			b.add(-this.points.get(i).x + this.points.get(i + 1).x);
			c.add(this.points.get(i).x * this.points.get(i + 1).y
					- this.points.get(i + 1).x * this.points.get(i).y);

			if (this.points.get(i).y > maxY) {
				maxY = this.points.get(i).y;
			} else if (this.points.get(i).y < minY) {
				minY = this.points.get(i).y;
			}

			if (this.points.get(i).x > maxX) {
				maxX = this.points.get(i).x;
			} else if (this.points.get(i).x < minX) {
				minX = this.points.get(i).x;
			}
		}

	}

	/**
	 * Checks if this Polygon contains the point p.
	 * 
	 * @param p
	 *            the point we check if it is inside.
	 * @return true if its inside , false if it is out.
	 */
	public boolean contains(Point p) {
		for (int i = 0; i < this.points.size() - 1; i++) {
			if ((p.x * this.a.get(i) + p.y * b.get(i) + c.get(i)) > 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Draws the outskirt of this polygon.
	 * 
	 * @param gl
	 *            the canvas we draw on
	 */
	public void draw(GL gl) {
		gl.glColor3d(this.color.getR(), this.color.getG(), this.color.getB());
		gl.glBegin(GL.GL_LINE_STRIP);

		for (Point p : points) {
			gl.glVertex2i((int) p.getX(), (int) p.getY());
		}
		gl.glEnd();

	}

	/**
	 * The method which paints the polygon in the color.
	 * 
	 * @param gl
	 *            the canvas we draw on.
	 * @param col
	 *            the color in which we paint this polygon.
	 */
	public void color(GL gl, Colors col) {
		this.draw(gl);
		gl.glColor3d(col.getR(), col.getG(), col.getB());
		gl.glBegin(GL.GL_LINES);
		double L;
		double D;
		double x;
		for (int y = minY; y <= maxY; y++) {
			L = minX;
			D = maxX;
			for (int i = 0; i < this.points.size() - 1; i++) {
				if (a.get(i) != 0) {
					x = (double) (-b.get(i) * y - c.get(i))
							/ ((double) a.get(i));
					if (this.points.get(i).y < this.points.get(i + 1).y) {
						if (x > L) {
							L = x;
						}
					} else {
						if (x < D) {
							D = x;
						}
					}

				}

			}
			if (L < D) {
				gl.glVertex2d(L, y);
				gl.glVertex2d(D, y);
			}
		}
		gl.glEnd();

	}

}
