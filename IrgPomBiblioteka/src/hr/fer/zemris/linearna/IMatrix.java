package hr.fer.zemris.linearna;

/**
 * The basic interface for a matrix.
 * 
 * @author Ivan Hrastinski
 * @version 1
 */
public interface IMatrix {
	/**
	 * Gets the number of rows of this IMatrix.
	 * 
	 * @return the number of rows.
	 */
	public int getRowsCount();

	/**
	 * Gets the number of columns of this IMatrix.
	 * 
	 * @return the number of columns.
	 */
	public int getColsCount();

	/**
	 * Gets the a specific value.
	 * 
	 * @param row
	 *            the row number of the wanted value.
	 * @param col
	 *            the column number of the wanted value.
	 * @return
	 */
	public double get(int row, int col);

	/**
	 * Sets a specific value in the matrix.
	 * 
	 * @param row
	 *            the row of the value.
	 * @param col
	 *            the column of the value.
	 * @param value
	 *            the new value.
	 * @return the matrix with the changed value.
	 */
	public IMatrix set(int row, int col, double value);

	/**
	 * Copies this matrix.
	 * 
	 * @return a new copy of this matrix.
	 */
	public IMatrix copy();

	/**
	 * Creates a new instance of an IMatrix.
	 * 
	 * @param row
	 *            the number of rows.
	 * @param col
	 *            the number of columns.
	 * @return the new empty matrix.
	 */
	public IMatrix newInstance(int row, int col);

	/**
	 * Transposes this matrix into a new one.
	 * 
	 * @param trans
	 * @return the new matrix.
	 */
	public IMatrix nTranspose(boolean trans);

	/**
	 * Adds two matrix and the result saves in this.
	 * 
	 * @param other
	 *            the other matrix we add to this.
	 * @return this matrix with the new value.
	 * @throws IncompatibleOperandException
	 */
	public IMatrix add(IMatrix other) throws IncompatibleOperandException;

	/**
	 * Adds two matrix and the result saves in a new IMatrix.
	 * 
	 * @param other
	 *            the other matrix we add to this.
	 * @return a new matrix with the new values.
	 * @throws IncompatibleOperandException
	 */
	public IMatrix nAdd(IMatrix other) throws IncompatibleOperandException;

	/**
	 * Subtracts two matrix and the result saves in this.
	 * 
	 * @param other
	 *            the other matrix we subtract to this.
	 * @return this matrix with the new value.
	 * @throws IncompatibleOperandException
	 */
	public IMatrix sub(IMatrix other) throws IncompatibleOperandException;

	/**
	 * Subtracts two matrix and the result saves in a new IMatrix.
	 * 
	 * @param other
	 *            the other matrix we subtract from this.
	 * @return a new IMatrix with the new values.
	 * @throws IncompatibleOperandException
	 */
	public IMatrix nSub(IMatrix other) throws IncompatibleOperandException;

	/**
	 * This matrix multiplied with a scalar.
	 * 
	 * @param scalar
	 *            the scalar with which we multiply.
	 * @return this matrix multiplied with a scalar.
	 */
	public IMatrix scalarMultiply(double scalar);

	/**
	 * This matrix multiplied with a scalar and a new one returned.
	 * 
	 * @param scalar
	 *            the scalar with which we multiply.
	 * @return a new IMatrix which is the result of the this multiplied by the
	 *         scalar.
	 */
	public IMatrix nScalarMultiply(double scalar);

	/**
	 * Multiplies two IMatrix and saves the result in a new IMatrix.
	 * 
	 * @param other
	 *            the other IMatrix we multiply with.
	 * @return the new IMatrix.
	 * @throws IncompatibleOperandException
	 */
	public IMatrix nMultiply(IMatrix other) throws IncompatibleOperandException;

	/**
	 * Calculates the determinant of this matrix.
	 * 
	 * @return the determinant.
	 * @throws IncompatibleOperandException
	 */
	public double determinant() throws IncompatibleOperandException;

	/**
	 * 
	 * @param row
	 * @param col
	 * @param control
	 * @return
	 */
	public IMatrix subMatrix(int row, int col, boolean control);

	/**
	 * Inverts this matrix and creates a new.
	 * 
	 * @return the new inverted matrix.
	 * @throws IncompatibleOperandException
	 */

	public IMatrix nInvert() throws IncompatibleOperandException;

	/**
	 * Transforms this matrix in an array.
	 * 
	 * @return the values of this matrix as an array.
	 */
	public double[][] toArray();

	/**
	 * Transforms this matrix to an IVector.
	 * 
	 * @param control
	 *            checks if it is a live view.
	 * @return the new IVector with the values of this IMatrix.
	 */
	public IVector toVector(boolean control);
}
