package hr.fer.zemris.irg.lab1.demonstratura;

import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.IncompatibleOperandException;
import hr.fer.zemris.linearna.Matrix;
import hr.fer.zemris.linearna.Vector;

public class PrvaGrupaZad {

	public static void main(String[] args) throws IncompatibleOperandException {
		IVector a=Vector.parseSimple("2 3 -4");
		IVector b=Vector.parseSimple("-1 4 -3");
		IVector c=Vector.parseSimple("2 2 4");
		IVector v1=a.nAdd(b);
		double s=v1.scalarProduct(b);
		IVector v2=v1.nVectorProduct(c);
		IVector v3=v2.nNormalize();
		IVector v4=v2.nScalarMultiply(-1);
		System.out.println(v1);
		System.out.println(v2);
		System.out.println(v3);
		System.out.println(v4);
		System.out.println(s);
		IMatrix m1=Matrix.parseSimple("1 2 3|2 1 3|4 5 1").nAdd(Matrix.parseSimple("-1 2 -3|5 -2 7|-4 -1 3"));
		IMatrix m2=Matrix.parseSimple("1 2 3|2 1 3|4 5 1").nMultiply(Matrix.parseSimple("-1 2 -3|5 -2 7|-4 -1 3").nTranspose(false));
		IMatrix m3=Matrix.parseSimple("-24 18 5|20 -15 -4|-5 4 1").nInvert().nMultiply(Matrix.parseSimple("1 2 3|0 1 4|5 6 0").nInvert());
		System.out.println(m1);
		System.out.println(m2);
		System.out.println(m3);

	}

}
