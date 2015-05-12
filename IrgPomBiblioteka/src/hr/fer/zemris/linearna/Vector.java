
package hr.fer.zemris.linearna;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ivan Hrastinski
 * @version 1
 */
public class Vector extends AbstractVector {

	// The elements of this Vector.
	private double[] elements;

	// The dimension of this Vector.
	private int dimension;

	// The read only flag.
	private boolean readOnly = false;

	/**
	 * The basic constructor which gets an array of values.
	 * 
	 * @param values
	 *            the array from which we generate an vector.
	 */
	public Vector(double... values) {
		this.dimension = values.length;
		this.elements = new double[this.dimension];
		for (int i = this.dimension - 1; i >= 0; i--) {
			this.elements[i] = values[i];
		}
	}

	/**
	 * The advanced constructor which takes 2 flags.
	 * 
	 * @param readOnly
	 *            this flag determines if the Vector is an read only vector.
	 * @param directCopy
	 *            this flag determines if the input param values can be directly
	 *            used as elements.
	 * @param values
	 *            the elements of this vector.
	 */
	public Vector(boolean readOnly, boolean directCopy, double[] values) {
		this.readOnly = readOnly;
		this.dimension = values.length;
		if (directCopy) {
			this.elements = values;
		} else {
			for (int i = this.dimension - 1; i >= 0; i--) {
				this.elements[i] = values[i];
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractVector#get(int)
	 */
	@Override
	public double get(int position) {

		return elements[position];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractVector#set(int, double)
	 */
	@Override
	public IVector set(int position, double value)
			throws IllegalAccessException {
		if (this.readOnly) {
			throw new IllegalAccessException(
					"This vector is an read only vector.");
		}
		else{
			this.elements[position]=value;
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractVector#getDimensions()
	 */
	@Override
	public int getDimensions() {

		return this.dimension;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractVector#copy()
	 */
	@Override
	public IVector copy() {

		return new Vector(this.elements);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractVector#newInstance(int)
	 */
	@Override
	public IVector newInstance(int dimension) {

		return new Vector(new double[dimension]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return toString(3);
	}
	
	public String toString(int precision){
		String mask = "#0.";
		for (int i = 0; i < precision; i++) {
			mask = mask + "0";
		}

		StringBuilder out = new StringBuilder();

		NumberFormat formatter = new DecimalFormat(mask);
		out.append("[");
		for(int i=0;i<this.dimension;i++){
			out.append(formatter.format(this.get(i))+" ");
		}
		out.append("]");
		return out.toString();
	}

	/**
	 * Parses a string into a Vector.
	 * 
	 * @param vector
	 *            the string we parse into a vector.
	 * @return a new Vector with the parsed values.
	 */
	public static Vector parseSimple(String vector) {
		ArrayList<Double> stringElements = new ArrayList<Double>();
		vector = vector.trim();
		String firstNumber = "";
		double firstDouble = 0;
		while (vector.length() != 0) {
			if (vector.indexOf(" ") > 0) {
				firstNumber = vector.substring(0, vector.indexOf(" "));
			} else {
				firstNumber = vector;
			}
			firstDouble = Double.parseDouble(firstNumber);
			stringElements.add(firstDouble);
			vector = vector.substring(firstNumber.length()).trim();
		}
		double[] elementArray = new double[stringElements.size()];
		for (int i = 0; i < stringElements.size(); i++) {
			elementArray[i] = stringElements.get(i);
		}

		return new Vector(elementArray);

	}

}
