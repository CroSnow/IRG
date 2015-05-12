package hr.fer.zemris.irg.lab2.zad2.labos;

import java.util.ArrayList;

/**
 * A class which saves a shape in form of triangles.
 * 
 * @author Ivan Hrastinski
 * @version 1
 */
public class Shape {
	// The list of triangles which form this shape.
	private ArrayList<Triangle> partList = new ArrayList<Triangle>();

	/**
	 * Forms a shape from triangles.
	 * 
	 * @param triangles
	 */
	public Shape(Triangle... triangles) {
		for (Triangle t : triangles) {
			partList.add(t);
		}
	}

	/**
	 * Empty constructor
	 */
	public Shape() {

	}

	/**
	 * Add a triangle shape to this shape.
	 * 
	 * @param t
	 */
	public void addTriangle(Triangle t) {
		partList.add(t);
	}

	/**
	 * Checks if the given point is inside or outside the shape.
	 * 
	 * @param p
	 *            the point we check for.
	 * @return true if its inside , false else.
	 */
	public boolean checkPoint(Point p) {
		for (Triangle t : partList) {
			if (t.position(p) > 0) {
				return false;
			}
		}
		return true;
	}

}
