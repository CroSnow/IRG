package hr.fer.zemris.linearna;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Matrix extends AbstractMatrix {
	// The elements of this matrix.
	private double[][] elements;
	// The number of rows of this matrix.
	private int rows;
	// The number of columns of this matrix.
	private int cols;

	public Matrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.elements=new double[rows][cols];
	}

	public Matrix(int rows, int cols, double[][] values, boolean liveView) {
		this.rows = rows;
		this.cols = cols;
		if (liveView) {
			this.elements = values;
		} else {
			this.elements = new double[rows][cols];
			for (int i = rows - 1; i >= 0; i--) {
				for (int j = cols - 1; j >= 0; j--) {
					this.elements[i][j] = values[i][j];
				}
			}
		}
	}

	/**
	 * The parser which takes a string in which the columns are divided by
	 * spaces and the rows are by|. An example of the input 1 2 3 | 4 5 6 .
	 * 
	 * @param input
	 *            the string we want to parse.
	 * @return an IMatrix parsed from our string.
	 */
	public static IMatrix parseSimple(String input) {
		String[] byRows = input.split("\\|");
	//	System.out.println(Arrays.toString(byRows));
		double[][] matrix;
		matrix = new double[byRows.length][byRows[0].split(" +").length];
		int i = 0;
		int j = 0;
		for (String stringRow : byRows) {
			String[] stringArrayRow = stringRow.trim().split(" +");
			j = 0;
			for (String numberString : stringArrayRow) {
				matrix[i][j] = Double.parseDouble(numberString.trim());
				j++;
			}
			i++;

		}

		return new Matrix(byRows.length, byRows[0].split(" +").length, matrix,
				true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#getRowsCount()
	 */
	@Override
	public int getRowsCount() {

		return this.rows;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#getColsCount()
	 */
	@Override
	public int getColsCount() {
		return this.cols;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#get(int, int)
	 */
	@Override
	public double get(int row, int col) {

		return elements[row][col];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#set(int, int, double)
	 */
	@Override
	public IMatrix set(int row, int col, double value) {
		elements[row][col] = value;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#copy()
	 */
	@Override
	public IMatrix copy() {
		return new Matrix(this.rows, this.cols, this.elements, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#newInstance(int, int)
	 */
	@Override
	public IMatrix newInstance(int row, int col) {

		return new Matrix(row, col);
	}

	/**
	 * The matrix shown as a string in which the precision determines how many
	 * digits the double will show.
	 * 
	 * @param precision
	 *            the number of digits (behind the dot) in the values.
	 * @return the matrix shown as a string.
	 */
	public String toString(int precision) {
		String mask = "#0.";
		for (int i = 0; i < precision; i++) {
			mask = mask + "0";
		}

		StringBuilder out = new StringBuilder();

		NumberFormat formatter = new DecimalFormat(mask);

		for (int i = 0; i < this.rows; i++) {
			out.append("[");
			for (int j = 0; j < this.cols; j++) {
				out.append(formatter.format(this.get(i, j)));
				out.append(" ");
			}
			out.append("]\n");
		}
		return out.toString();
	}

	/**
	 *
	 * The basic to string function in which the precision is 3.
	 *
	 * @return this matrix shown as a string.
	 *
	 */
	@Override
	public String toString() {
		return this.toString(3);
	}

}
