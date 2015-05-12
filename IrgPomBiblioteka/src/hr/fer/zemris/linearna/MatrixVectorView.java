/**
 * 
 */
package hr.fer.zemris.linearna;

/**
 * @author Ivan Hrastinski
 * @version 1
 */
public class MatrixVectorView extends AbstractMatrix {
	private boolean asRowMatrix;
	private IVector vector;

	public MatrixVectorView(IVector vector, boolean asRowMatrix) {
		this.asRowMatrix = asRowMatrix;
		this.vector = vector.copy();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#getRowsCount()
	 */
	@Override
	public int getRowsCount() {
		int rows = 1;
		if (!asRowMatrix) {
			rows = this.vector.getDimensions();
		}
		return rows;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#getColsCount()
	 */
	@Override
	public int getColsCount() {
		int cols = 1;
		if (asRowMatrix) {
			cols = this.vector.getDimensions();
		}
		return cols;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#get(int, int)
	 */
	@Override
	public double get(int row, int col) {
		double value;
		value=0;
		if (asRowMatrix) {
			value = this.vector.get(col);
		}
		if(!asRowMatrix){
			value=this.vector.get(row);
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#set(int, int, double)
	 */
	@Override
	public IMatrix set(int row, int col, double value) {

		if (asRowMatrix) {
			try {
				this.vector.set(col,value);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(!asRowMatrix){
			try {
				this.vector.set(row,value);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this;
	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#copy()
	 */
	@Override
	public IMatrix copy() {
		
		return new MatrixVectorView(vector.copy(), asRowMatrix);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#newInstance(int, int)
	 */
	@Override
	public IMatrix newInstance(int row, int col) {
		boolean rowMatrix=false;
		int size=row;
		if (row==1){
			rowMatrix=true;
			size=col;
		}
		return new MatrixVectorView(this.vector.newInstance(size), rowMatrix);
	}

}
