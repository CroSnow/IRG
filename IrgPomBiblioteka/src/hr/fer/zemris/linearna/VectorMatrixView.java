/**
 * 
 */
package hr.fer.zemris.linearna;

/**
 * @author Ivan Hrastinski
 * @version 1
 */
public class VectorMatrixView extends AbstractVector {
	// The dimension of vector.
	private int dimension;
	// Is this a row matrix.
	private boolean rowMatrix;
	// The Matrix.
	private IMatrix matrix;

	/**
	 * The basic constructor for the vector matrix view.
	 * 
	 * @param matrix
	 *            the matrix ve want to view as an vector.
	 */
	public VectorMatrixView(IMatrix matrix) {
		this.matrix = matrix;
		if (matrix.getRowsCount() == 1) {
			this.rowMatrix = true;
			this.dimension = matrix.getColsCount();
		} else {
			this.rowMatrix = false;
			this.dimension = matrix.getRowsCount();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractVector#get(int)
	 */
	@Override
	public double get(int position) {
		double value;
		if (rowMatrix) {
			value = matrix.get(0, position);
		} else {
			value = matrix.get(position, 0);
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractVector#set(int, double)
	 */
	@Override
	public IVector set(int position, double value)
			throws IllegalAccessException {
		if (rowMatrix) {
			matrix.set(1, position, value);
		} else {
			matrix.set(position, 1, value);
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
		return new VectorMatrixView(matrix.copy());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractVector#newInstance(int)
	 */
	@Override
	public IVector newInstance(int dimension) {
		return new VectorMatrixView(matrix.newInstance(dimension, 1));
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VectorMatrixView [matrix=" + matrix + "]";
	}
	

}
