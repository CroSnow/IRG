package hr.fer.zemris.irg.test;

import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IncompatibleOperandException;
import hr.fer.zemris.linearna.Matrix;

public class MatrixStringTest {
	public static void main(String[] args) throws IncompatibleOperandException {
		IMatrix a=Matrix.parseSimple("3 5 | 2 10");
		IMatrix r=Matrix.parseSimple("2|8");
		IMatrix v=a.nInvert().nMultiply(r);
		System.out.println("Rjesenje sustava je: ");
		System.out.println(v);
	}

}
