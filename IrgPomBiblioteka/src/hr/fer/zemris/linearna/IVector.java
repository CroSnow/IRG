package hr.fer.zemris.linearna;

/**
 * Basic Vector interface.
 * 
 * @author Ivan Hrastinski
 * @version 1
 */
public interface IVector {

	/**
	 * Gets the value from a specific position.
	 * 
	 * @param position
	 *            of the value we get.
	 * @return the value at the given position.
	 */
	public double get(int position);

	/**
	 * Sets the value on a specific position.
	 * 
	 * @param position
	 *            the position on which we change the value.
	 * @param value
	 *            the new value.
	 * @return the changed IVector.
	 * @throws IllegalAccessException 
	 */
	public IVector set(int position, double value) throws IllegalAccessException;

	/**
	 * Gets the dimensions of the IVector.
	 * 
	 * @return the dimensions.
	 */
	public int getDimensions();

	/**
	 * Copies the IVector into a new instance.
	 * 
	 * @return the new instance of the same IVector.
	 */
	public IVector copy();

	/**
	 * Copies only a part of the IVector starting at the beginning.
	 * 
	 * @param numberOfComponents
	 *            specifies the number of components that we copy into the new
	 *            IVector
	 * @return a part of the old IVector.
	 */
	public IVector copyPart(int numberOfComponents);

	/**
	 * Creates the a new empty IVector with a specific dimension.
	 * 
	 * @param instance
	 *            the dimension of this IVector.
	 * @return the new empty IVector.
	 */
	public IVector newInstance(int dimension);

	/**
	 * Adds two IVectors, saves the result in this and returns him.
	 * 
	 * @param other
	 *            the other IVector which we add to this.
	 * @return this IVector with the added values.
	 * @throws IncompatibleOperandException
	 */
	public IVector add(IVector other) throws IncompatibleOperandException;

	/**
	 * Adds two IVector and saves the value of the operation in a NEW IVector.
	 * 
	 * @param other
	 *            the other IVector we add to this.
	 * @return a new IVector with the added values.
	 * @throws IncompatibleOperandException
	 */
	public IVector nAdd(IVector other) throws IncompatibleOperandException;

	/**
	 * Subs two IVectors, saves the result in this and returns him.
	 * 
	 * @param other
	 *            the other IVector which we subtract to this.
	 * @return this IVector with the subtracted values.
	 * @throws IncompatibleOperandException
	 */
	public IVector sub(IVector other) throws IncompatibleOperandException;

	/**
	 * Subs two IVectors, saves the result in a NEW IVector and returns him.
	 * 
	 * @param other
	 *            the other IVector which we subtract to this.
	 * @return the NEW IVector with the subtracted values.
	 * @throws IncompatibleOperandException
	 */
	public IVector nSub(IVector other) throws IncompatibleOperandException;

	/**
	 * Multiples this IVector with a scalar and returns it.
	 * 
	 * @param scalar
	 *            the scalar we multiplie with.
	 * @return this IVector multiplied with scalar.
	 */
	public IVector scalarMultiply(double scalar);

	/**
	 * Multiples this IVector with a scalar and returns a new IVector.
	 * 
	 * @param scalar
	 *            the scalar we multiplie with.
	 * @return the new IVector which is the product of this IVector multiplied
	 *         with the scalar.
	 */
	public IVector nScalarMultiply(double scalar);

	/**
	 * The norm of the vector.
	 * 
	 * @return the norm of the IVector.
	 */
	public double norm();

	/**
	 * Normalizes this vector.
	 * 
	 * @return this vector normalized.
	 */
	public IVector normalize();

	/**
	 * Normalizes this vector.
	 * 
	 * @return a new normalized vector.
	 */
	public IVector nNormalize();

	/**
	 * The cosine between this vector and another one.
	 * 
	 * @param other
	 *            the other vector between we get the cosine.
	 * @return the cosine.
	 * @throws IncompatibleOperandException
	 */
	public double cosine(IVector other) throws IncompatibleOperandException;

	/**
	 * Returns the Scalar product of two IVectors.
	 * 
	 * @param other
	 *            the other IVector we get the scalar product from.
	 * @return the scalar product of the two IVectors.
	 * @throws IncompatibleOperandException
	 */
	public double scalarProduct(IVector other)
			throws IncompatibleOperandException;

	/**
	 * The Vector product of two IVectors returned in a new IVector.
	 * 
	 * @param other
	 *            the other IVector.
	 * @return a new instance of IVector which is a vector product of this and
	 *         the other IVector.
	 * @throws IncompatibleOperandException
	 */
	public IVector nVectorProduct(IVector other)
			throws IncompatibleOperandException;

	/**
	 * A new IVector which is 1 dimension shorter and all the elements are
	 * divided by the deleted element.
	 * 
	 * @return the new IVector.
	 * @throws IncompatibleOperandException
	 */
	public IVector nFromHomogeneus() throws IncompatibleOperandException;

	/**
	 * Returns this IVector transormed into a IMatrix by rows.
	 * 
	 * @param liveView
	 *            //TODO
	 * @return an instance of IMatrix who's rows are filled with the values of
	 *         this IVector.
	 */
	public IMatrix toRowMatrix(boolean liveView);

	/**
	 * Returns this IVector transformed into a IMatrix by columns.
	 * 
	 * @param liveView
	 *            //TODO
	 * @return an instance of IMatrix who's columns are filled with the values
	 *         of this IVector.
	 */
	public IMatrix toColumnMatrix(boolean liveView);

	/**
	 * Returns this IVector in a basic Array.
	 * 
	 * @return this IVector in Array format.
	 */
	public double[] toArray();
}
