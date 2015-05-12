package hr.fer.zemris.irg.lab2.zad2.labos;

public class Triangle {
	// The x coordinates.
	private float[] x;
	// The y coordinates.
	private float[] y;
	// The z coordinates.
	private float[] z;

	// The a parametar.
	private float a;
	// The b parametar.
	private float b;
	// The c parametar.
	private float c;
	// The d parametar.
	private float d;

	/**
	 * Creates a trinagle from 3 points.
	 * 
	 * @param first
	 *            the first point
	 * @param second
	 *            the second point
	 * @param third
	 *            the third point
	 */
	public Triangle(float[] first, float[] second, float[] third) {
		x = new float[3];
		y = new float[3];
		z = new float[3];

		x[0] = first[0];
		x[1] = second[0];
		x[2] = third[0];

		y[0] = first[1];
		y[1] = second[1];
		y[2] = third[1];

		z[0] = first[2];
		z[1] = second[2];
		z[2] = third[2];

		a = (y[1] - y[0]) * (z[2] - z[0]) - (z[1] - z[0]) * (y[2] - y[0]);
		b = -(x[1] - x[0]) * (y[2] - y[0]) + (z[1] - z[0]) * (x[2] - x[0]);
		c = (x[1] - x[0]) * (y[2] - y[0]) - (y[1] - y[0]) * (x[2] - x[0]);
		d = -x[0] * a - y[0] * b - z[0] * c;
	}

	/**
	 * Checks in which position is the point compared to this triangle (over on
	 * under )(+,0,-).
	 * 
	 * @param p
	 *            the point we compare
	 * @return a positive number if its over, 0 if its in , a negative number if
	 *         its under.
	 */
	public float position(Point p) {
		return a * p.getX() + b * p.getY() + c * p.getZ() + d;
	}

	/**
	 * @return the x
	 */
	public float[] getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(float[] x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public float[] getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(float[] y) {
		this.y = y;
	}

	/**
	 * @return the z
	 */
	public float[] getZ() {
		return z;
	}

	/**
	 * @param z
	 *            the z to set
	 */
	public void setZ(float[] z) {
		this.z = z;
	}

	/**
	 * @return the a
	 */
	public float getA() {
		return a;
	}

	/**
	 * @param a
	 *            the a to set
	 */
	public void setA(float a) {
		this.a = a;
	}

	/**
	 * @return the b
	 */
	public float getB() {
		return b;
	}

	/**
	 * @param b
	 *            the b to set
	 */
	public void setB(float b) {
		this.b = b;
	}

	/**
	 * @return the c
	 */
	public float getC() {
		return c;
	}

	/**
	 * @param c
	 *            the c to set
	 */
	public void setC(float c) {
		this.c = c;
	}

	/**
	 * @return the d
	 */
	public float getD() {
		return d;
	}

	/**
	 * @param d
	 *            the d to set
	 */
	public void setD(float d) {
		this.d = d;
	}

}
