package hr.fer.zemris.linearna;
/**
 * The Transposed view of the original matrix.
 * @author Ivan Hrastinski
 * @version 1
 */
public class MatrixTransposeView extends AbstractMatrix {
	//The matrix we get.
	private IMatrix matrix;
	
	
	
	public MatrixTransposeView(IMatrix matrix){
		super();
		this.matrix=matrix;
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#getRowsCount()
	 */
	@Override
	public int getRowsCount() {
		
		return this.matrix.getColsCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#getColsCount()
	 */
	@Override
	public int getColsCount() {

		return this.matrix.getRowsCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#get(int, int)
	 */
	@Override
	public double get(int row, int col) {

		return this.matrix.get(col, row);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#set(int, int, double)
	 */
	@Override
	public IMatrix set(int row, int col, double value) {
		this.matrix.set(col, row, value);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#copy()
	 */
	@Override
	public IMatrix copy() {
		
		return new MatrixTransposeView(this.matrix.copy());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#newInstance(int, int)
	 */
	@Override
	public IMatrix newInstance(int row, int col) {

		return new Matrix(row,col);
	}

}
