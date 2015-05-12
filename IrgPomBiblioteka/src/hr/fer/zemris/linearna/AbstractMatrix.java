/**
 * 
 */
package hr.fer.zemris.linearna;

/**
 * @author Ivan Hrastinski
 * @version 1
 */
public abstract class AbstractMatrix implements IMatrix {

	public AbstractMatrix() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IMatrix#getRowsCount()
	 */
	@Override
	public abstract int getRowsCount();

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IMatrix#getColsCount()
	 */
	@Override
	public abstract int getColsCount();

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IMatrix#get(int, int)
	 */
	@Override
	public abstract double get(int row, int col);

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IMatrix#set(int, int, double)
	 */
	@Override
	public abstract IMatrix set(int row, int col, double value);

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IMatrix#copy()
	 */
	@Override
	public abstract IMatrix copy();

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IMatrix#newInstance(int, int)
	 */
	@Override
	public abstract IMatrix newInstance(int row, int col);

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IMatrix#nTranspose(boolean)
	 */
	@Override
	public IMatrix nTranspose(boolean liveView) {
		IMatrix transposed;
		if (liveView) {
			transposed = new MatrixTransposeView(this);
		} else {
			transposed = new MatrixTransposeView(this.copy());
		}
		return transposed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IMatrix#add(hr.fer.zemris.linearna.IMatrix)
	 */
	@Override
	public IMatrix add(IMatrix other) throws IncompatibleOperandException {
		if (this.getColsCount() != other.getColsCount()
				|| this.getRowsCount() != other.getRowsCount()) {
			throw new IncompatibleOperandException(
					"Wrong number of colums or rows.");
		}
		for (int i = this.getRowsCount() - 1; i >= 0; i--) {
			for (int j = this.getColsCount() - 1; j >= 0; j--) {
				this.set(i, j, this.get(i, j) + other.get(i, j));
			}
		}

		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IMatrix#nAdd(hr.fer.zemris.linearna.IMatrix)
	 */
	@Override
	public IMatrix nAdd(IMatrix other) throws IncompatibleOperandException {

		return this.copy().add(other);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IMatrix#sub(hr.fer.zemris.linearna.IMatrix)
	 */
	@Override
	public IMatrix sub(IMatrix other) throws IncompatibleOperandException {
		if (this.getColsCount() != other.getColsCount()
				|| this.getRowsCount() != other.getRowsCount()) {
			throw new IncompatibleOperandException(
					"Wrong number of colums or rows.");
		}
		for (int i = this.getRowsCount() - 1; i >= 0; i--) {
			for (int j = this.getColsCount() - 1; j >= 0; j--) {
				this.set(i, j, this.get(i, j) + other.get(i, j));
			}
		}

		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IMatrix#nSub(hr.fer.zemris.linearna.IMatrix)
	 */
	@Override
	public IMatrix nSub(IMatrix other) throws IncompatibleOperandException {
		return this.copy().add(other);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IMatrix#scalarMultiply(double)
	 */
	public IMatrix scalarMultiply(double scalar) {
		for (int i = this.getRowsCount() - 1; i >= 0; i--) {
			for (int j = this.getColsCount() - 1; j >= 0; j--) {
				this.set(i, j, this.get(i, j) * scalar);
			}
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IMatrix#nScalarMultiply(double)
	 */
	public IMatrix nScalarMultiply(double scalar) {
		return this.copy().scalarMultiply(scalar);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hr.fer.zemris.linearna.IMatrix#nMultiply(hr.fer.zemris.linearna.IMatrix)
	 */
	@Override
	public IMatrix nMultiply(IMatrix other) throws IncompatibleOperandException {
		if (this.getColsCount() != other.getRowsCount()) {
			throw new IncompatibleOperandException(
					"Wrong number of the other Matrix rows");
		}
		IMatrix multiplied = this.newInstance(this.getRowsCount(),
				other.getColsCount());
		for (int i = this.getRowsCount() - 1; i >= 0; i--) {
			for (int j = other.getColsCount() - 1; j >= 0; j--) {
				for (int k = this.getColsCount() - 1; k >= 0; k--) {
					multiplied.set(i, j, multiplied.get(i, j) + this.get(i, k)
							* other.get(k, j));
				}
			}
		}
		return multiplied;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IMatrix#determinant()
	 */
	@Override
	public double determinant() throws IncompatibleOperandException {
		if (this.getColsCount() != this.getRowsCount()) {
			throw new IncompatibleOperandException(
					"The number of rows and columns of this matrix are not the same.");
		}

		double det = 0;
		if (this.getColsCount() == 1) {
			det = this.get(0, 0);
		} else if (this.getColsCount() == 2) {
			det = this.get(0, 0) * this.get(1, 1) - this.get(0, 1)
					* this.get(1, 0);
		} else {
			for (int i = 0; i < this.getColsCount(); i++) {
				if (i % 2 == 0) {
					det += this.get(0, i)
							* this.subMatrix(0, i, false).determinant();
				} else {
					det -= this.get(0, i)
							* this.subMatrix(0, i, false).determinant();
				}
			}
		}
		return det;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IMatrix#subMatrix(int, int, boolean)
	 */
	@Override
	public IMatrix subMatrix(int row, int col, boolean liveView) {
		IMatrix sub;

		if (liveView) {
			sub = new MatrixSubMatrixView(this, row, col);
		} else {

			sub = new MatrixSubMatrixView(this.copy(), row, col);
		}
		return sub;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IMatrix#nInvert()
	 */
	@Override
	public IMatrix nInvert() throws IncompatibleOperandException {
		if (this.determinant() == 0) {
			throw new IncompatibleOperandException(
					"The determinant of the matrix we want to invert cant be 0");
		}
		double determinant = this.determinant();
		IMatrix invert = this.newInstance(this.getRowsCount(),
				this.getColsCount());
		for (int i = this.getRowsCount() - 1; i >= 0; i--) {
			for (int j = this.getColsCount() - 1; j >= 0; j--) {
				if ((i + j) % 2 == 0) {
					invert.set(i, j, this.subMatrix(i, j, true).determinant());
				} else {
					invert.set(i, j, -this.subMatrix(i, j, true).determinant());
				}

			}
		}
		invert.scalarMultiply(1 / determinant);
		return invert.nTranspose(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IMatrix#toArray()
	 */
	@Override
	public double[][] toArray() {
		double[][] array = new double[this.getRowsCount()][this.getColsCount()];
		for (int i = 0; i < this.getRowsCount(); i++) {
			for (int j = 0; j < this.getColsCount(); j++) {
				array[i][j] = this.get(i, j);
			}
		}
		return array;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.IMatrix#toVector(boolean)
	 */
	@Override
	public IVector toVector(boolean liveView) {
		IVector vec;
		if (liveView){
			vec=new VectorMatrixView(this);
		}
		else{
			vec=new VectorMatrixView(this.copy());
		}
		return vec;
	}

}
