package hr.fer.zemris.irg.lab2.zad2.labos;

/**
 * Class for saving a 3d point.
 * 
 * @author Ivan Hrastinski
 * @version 1
 */
public class Point {
	// The coordinates of this point.
	private float[] coordinate;

	/**
	 * The constructor for a 3d point.
	 * 
	 * @param x
	 *            the x coordinate.
	 * @param y
	 *            the y coordinate.
	 * @param z
	 *            the z coordinate.
	 */
	public Point(float x, float y, float z) {
		coordinate = new float[3];
		coordinate[0] = x;
		coordinate[1] = y;
		coordinate[2] = z;
	}

	/**
	 * @return the coordinate
	 */
	public float[] getCoordinate() {
		return coordinate;
	}

	/**
	 * @param coordinate
	 *            the coordinate to set
	 */
	public void setCoordinate(float[] coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * 
	 * @return the x coordinate
	 */
	public float getX() {
		return this.coordinate[0];
	}

	/**
	 * 
	 * @param x
	 *            the x coordinate to set
	 */
	public void setX(float x) {
		this.coordinate[0] = x;
	}

	/**
	 * 
	 * @return the y coordinate
	 */
	public float getY() {
		return this.coordinate[1];
	}

	/**
	 * 
	 * @param y
	 *            the y coordinate to set
	 */
	public void setY(float y) {
		this.coordinate[1] = y;
	}

	/**
	 * 
	 * @return the z coordinate
	 */
	public float getZ() {
		return this.coordinate[2];
	}

	/**
	 * 
	 * @param z
	 *            the z coordinate to set
	 */
	public void setZ(float z) {
		this.coordinate[2] = z;
	}

	/**
	 * Parses a 'x y z' string to a point.
	 * 
	 * @param toParse
	 *            the string we parse.
	 * @return a new Point.
	 */
	public static Point parseInput(String toParse) {
		toParse = toParse.trim();
		String numbers[] = toParse.split(" ");
		float x = Float.parseFloat(numbers[0].trim());
		float y = Float.parseFloat(numbers[1].trim());
		float z = Float.parseFloat(numbers[2].trim());
		return new Point(x, y, z);
	}

}
