package hr.fer.zemris.linearna;

/**
 * The sub matrix from the original matrix in which is excludes a specific row
 * and a specific column.
 * 
 * @author Ivan Hrastinski
 * @version 1
 */
public class MatrixSubMatrixView extends AbstractMatrix {
	// The row indexes which valid.
	private int[] rowIndexes;
	// The column indexes which are valid.
	private int[] colIndexes;
	// The original matrix.
	private IMatrix matrix;

	/**
	 * The basic constructor which takes the exluded row , column and the
	 * original matrix.
	 * 
	 * @param matrix
	 *            the original matrix.
	 * @param row
	 *            the excluded row.
	 * @param col
	 *            the excluded column.
	 */
	public MatrixSubMatrixView(IMatrix matrix, int row, int col) {
		this.matrix=matrix;
		this.rowIndexes=new int[this.matrix.getRowsCount()-1];
		this.colIndexes=new int[this.matrix.getColsCount()-1];
		int rowPos=0;
		int colPos=0;
		for (int i=0;i<this.matrix.getRowsCount();i++){
			if (i!=row){
				this.rowIndexes[rowPos]=i;
				rowPos++;
			}
		}
		for(int i=0;i<this.matrix.getColsCount();i++){
			if(i!=col){
				this.colIndexes[colPos]=i;
				colPos++;
			}
		}
		

	}
	
	private MatrixSubMatrixView(IMatrix matrix,int rows[],int cols[]){
		this.matrix=matrix;
		this.colIndexes=cols;
		this.rowIndexes=rows;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#getRowsCount()
	 */
	@Override
	public int getRowsCount() {
		return this.rowIndexes.length;
	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#getColsCount()
	 */
	@Override
	public int getColsCount() {
	return this.colIndexes.length;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#get(int, int)
	 */
	@Override
	public double get(int row, int col) {
		return this.matrix.get(this.rowIndexes[row], this.colIndexes[col]);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#set(int, int, double)
	 */
	@Override
	public IMatrix set(int row, int col, double value) {
		this.matrix.set(this.rowIndexes[row], this.colIndexes[col], value);
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#copy()
	 */
	@Override
	public IMatrix copy() {

		return new MatrixSubMatrixView(this.matrix.copy(),this.rowIndexes,this.colIndexes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.linearna.AbstractMatrix#newInstance(int, int)
	 */
	@Override
	public IMatrix newInstance(int row, int col) {
		int[] newRows=new int[this.rowIndexes.length-1];
		int[] newCols=new int[this.colIndexes.length-1];
		int i=0;
		for(int rowElement:rowIndexes){
			if (rowElement!=row){
				newRows[i]=rowElement;
				i++;
			}
		}
		i=0;
		for(int colElement:this.colIndexes){
			if(colElement!=col){
				newCols[i]=colElement;
			}
		}
		return new MatrixSubMatrixView(this.matrix, newRows, newCols);
	}

}
