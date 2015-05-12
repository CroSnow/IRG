package hr.fer.zemris.linearna;

/**
 * @author Ivan Hrastinski
 * @version 1
 */

public abstract class AbstractVector implements IVector {
	/**
	 * Basic constructor.
	 */
	public AbstractVector() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#get(int)
	 */
	@Override
	public abstract double get(int position);

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#set(int, double)
	 */
	@Override
	public abstract IVector set(int position, double value)
			throws IllegalAccessException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#getDimensions()
	 */
	@Override
	public abstract int getDimensions();

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#copy()
	 */
	@Override
	public abstract IVector copy();

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#newInstance(int)
	 */
	@Override
	public abstract IVector newInstance(int dimension);

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#copyPart(int)
	 */
	@Override
	public IVector copyPart(int numberOfComponents) {

		IVector shorter = this.newInstance(numberOfComponents);
		for (int i = numberOfComponents - 1; i >= 0; i--) {
			if (i < this.getDimensions()) {
				try {
					shorter.set(i, this.get(i));
					
				} catch (IllegalAccessException e) {
					// 
					e.printStackTrace();
				}
			} else {
				try {
					shorter.set(i, 0);
			
				} catch (IllegalAccessException e) {
					// 
					e.printStackTrace();
				}
			}
		}

		return shorter;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#add(hr.fer.zemris.linearna.IVector)
	 */
	@Override
	public IVector add(IVector other) throws IncompatibleOperandException {
		if (this.getDimensions() != other.getDimensions())
			throw new IncompatibleOperandException(
					"Wrong dimensions of the 2 vectors we want to add.");
		for (int i = this.getDimensions() - 1; i >= 0; i--) {
			try {
				this.set(i, this.get(i) + other.get(i));
			} catch (IllegalAccessException e) {
				// 
				e.printStackTrace();
			}
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#nAdd(hr.fer.zemris.linearna.IVector)
	 */
	@Override
	public IVector nAdd(IVector other) throws IncompatibleOperandException {

		return this.copy().add(other);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#sub(hr.fer.zemris.linearna.IVector)
	 */
	@Override
	public IVector sub(IVector other) throws IncompatibleOperandException {
		if (this.getDimensions() != other.getDimensions())
			throw new IncompatibleOperandException(
					"Wrong dimensions of the 2 vectors we want to sub.");
		for (int i = this.getDimensions() - 1; i >= 0; i--) {
			try {
				this.set(i, this.get(i) - other.get(i));
			} catch (IllegalAccessException e) {
				// 
				e.printStackTrace();
			}
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#nSub(hr.fer.zemris.linearna.IVector)
	 */
	@Override
	public IVector nSub(IVector other) throws IncompatibleOperandException {

		return this.copy().sub(other);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#scalarMultiply(double)
	 */
	@Override
	public IVector scalarMultiply(double scalar) {
		for (int i = this.getDimensions() - 1; i >= 0; i--) {
			try {
				this.set(i, this.get(i) * scalar);
			} catch (IllegalAccessException e) {
				// 
				e.printStackTrace();
			}
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#nScalarMultiply(double)
	 */
	@Override
	public IVector nScalarMultiply(double scalar) {
		return this.copy().scalarMultiply(scalar);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#norm()
	 */
	@Override
	public double norm() {
		double norm = 0;
		for (int i = this.getDimensions() - 1; i >= 0; i--) {
			norm += Math.pow(this.get(i), 2);
		}
		return Math.pow(norm, 0.5);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#normalize()
	 */
	@Override
	public IVector normalize() {
		double norm = this.norm();
		for (int i = this.getDimensions() - 1; i >= 0; i--) {
			try {
				this.set(i, this.get(i) / norm);
			} catch (IllegalAccessException e) {
				// 
				e.printStackTrace();
			}
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#nNormalize()
	 */
	@Override
	public IVector nNormalize() {

		return this.copy().normalize();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hr.fer.zemris.linearna.IVector#cosine(hr.fer.zemris.linearna.IVector)
	 */
	@Override
	public double cosine(IVector other) throws IncompatibleOperandException {
		double cos = this.scalarProduct(other);

		return cos / (this.norm() * other.norm());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hr.fer.zemris.linearna.IVector#scalarProduct(hr.fer.zemris.linearna.IVector
	 * )
	 */
	@Override
	public double scalarProduct(IVector other)
			throws IncompatibleOperandException {
		if (this.getDimensions() != other.getDimensions()) {
			throw new IncompatibleOperandException(
					"Wrong dimensions of the vectors during the scalar product.");
		}
		double sum = 0;
		for (int i = this.getDimensions() - 1; i >= 0; i--) {
			sum += this.get(i) * other.get(i);
		}
		return sum;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hr.fer.zemris.linearna.IVector#nVectorProduct(hr.fer.zemris.linearna.
	 * IVector)
	 */
	@Override
	public IVector nVectorProduct(IVector other)
			throws IncompatibleOperandException {
		if (this.getDimensions() != 3 || other.getDimensions() != 3) {
			throw new IncompatibleOperandException(
					" Both vectors have to have the dimension equal to 3");
		}
		IVector newVect = this.newInstance(3);
		double x1 = this.get(0);
		double x2 = other.get(0);
		double y1 = this.get(1);
		double y2 = other.get(1);
		double h1 = this.get(2);
		double h2 = other.get(2);
		try {
			newVect.set(0, y1 * h2 - y2 * h1);
			newVect.set(1, -x1 * h2 + x2 * h1);
			newVect.set(2, x1 * y2 - x2 * y1);
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		}

		return newVect;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#nFromHomogeneus()
	 */
	@Override
	public IVector nFromHomogeneus() throws IncompatibleOperandException {
		if (this.getDimensions() <= 1) {
			throw new IncompatibleOperandException(
					"This vector has not enough elements. ");
		}
		return this.copyPart(this.getDimensions() - 1).scalarMultiply(
				1 / this.get(this.getDimensions() - 1));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#toRowMatrix(boolean)
	 */
	@Override
	public IMatrix toRowMatrix(boolean liveView) {
		IMatrix rowMatrix;
		if (liveView){
			rowMatrix=new MatrixVectorView(this, true);
		}else{
			rowMatrix=new MatrixVectorView(this.copy(), true);
		}
		return rowMatrix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#toColumnMatrix(boolean)
	 */
	@Override
	public IMatrix toColumnMatrix(boolean liveView) {
		IMatrix colMatrix;
		if (liveView){
			colMatrix=new MatrixVectorView(this, false);
		}else{
			colMatrix=new MatrixVectorView(this.copy(), false);
		}
		return colMatrix;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IVector#toArray()
	 */
	@Override
	public double[] toArray() {
		double[] array = new double[this.getDimensions()];
		for (int i = this.getDimensions() - 1; i >= 0; i--) {
			array[i] = this.get(i);
		}
		return array;
	}

}
