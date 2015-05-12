package hr.fer.zemris.irg.lab1.calculations;

import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.IncompatibleOperandException;
import hr.fer.zemris.linearna.Matrix;
import hr.fer.zemris.linearna.Vector;
import hr.fer.zemris.linearna.VectorMatrixView;
/**
 * 
 * @author Ivan Hrastinski
 * @version 1
 */
public class IRGCalculations {

	/**
	 * The method which calculates the baricentric cordinate of 4 point.
	 * 
	 * @param a
	 *            the first point.
	 * @param b
	 *            the second point.
	 * @param c
	 *            the third point.
	 * @param t
	 *            the fourth point.
	 * @param print
	 *            checks to print the value inside this function or not.
	 * @return the baricentric cordinates.
	 * @throws IllegalAccessException
	 * @throws IncompatibleOperandException
	 */
	public static IVector bariCircleSecondMethod(IVector a, IVector b,
			IVector c, IVector t, boolean print) throws IllegalAccessException,
			IncompatibleOperandException {

		IMatrix matrix = new Matrix(3, 3);
		for (int i = 0; i < 3; i++) {
			matrix.set(i, 0, a.get(i));
			matrix.set(i, 1, b.get(i));
			matrix.set(i, 2, c.get(i));
		}
		for (int i = 0; i < 3; i++) {
			if ((matrix.get(i, 0) == 0) && (matrix.get(i, 1) == 0)
					&& (matrix.get(i, 2) == 0)) {
				for (int j = 0; j < 3; j++) {
					matrix.set(i, j, 1);
				}
				t.set(i, 1);
				break;
			}
		}
		IVector rezVec = matrix.nInvert().nMultiply(t.toColumnMatrix(false))
				.toVector(true);
		if (print) {
			System.out.println("Baricentricne koordinate su: (" + rezVec.get(0)
					+ "," + rezVec.get(1) + "," + rezVec.get(2) + ").");
		}
		return rezVec;

	}

	/**
	 * The method which calculates the baricentric cordinate of 4 point.
	 * 
	 * @param a
	 *            the first point.
	 * @param b
	 *            the second point.
	 * @param c
	 *            the third point.
	 * @param t
	 *            the fourth point.
	 * @return the baricentric cordinates.
	 * @throws IncompatibleOperandException
	 */
	public static IVector bariCircleFirstMethod(IVector a, IVector b,
			IVector c, IVector t) throws IncompatibleOperandException {

		double pov = b.nSub(a).nVectorProduct(c.nSub(a)).norm() / 2.0;
		double povA = b.nSub(t).nVectorProduct(c.nSub(t)).norm() / 2.0;
		double povB = a.nSub(t).nVectorProduct(c.nSub(t)).norm() / 2.0;
		double povC = a.nSub(t).nVectorProduct(b.nSub(t)).norm() / 2.0;

		double t1 = povA / pov;
		double t2 = povB / pov;
		double t3 = povC / pov;
		return new Vector(t1, t2, t3);

	}

	/**
	 * The parser which uses a string and runs the bariCircleFirst method.
	 * 
	 * @param a
	 *            the first vector.
	 * @param b
	 *            the second vector.
	 * @param c
	 *            the third vector.
	 * @param t
	 *            the fourth vector.
	 * @return the vector cordinates of the baricentic point.
	 * @throws IncompatibleOperandException
	 */
	public static IVector parseAndRunBariCircleFirstMethod(String a, String b,
			String c, String t) throws IncompatibleOperandException {
		IVector aVec = Vector.parseSimple(a);
		IVector bVec = Vector.parseSimple(b);
		IVector cVec = Vector.parseSimple(c);

		IVector tVec = Vector.parseSimple(t);
		return bariCircleFirstMethod(aVec, bVec, cVec, tVec);
	}

	/**
	 * The parser which uses a string and runs the bariCircleSecond method.
	 * 
	 * @param a
	 *            the first vector.
	 * @param b
	 *            the second vector.
	 * @param c
	 *            the third vector.
	 * @param t
	 *            the fourth vector.
	 * @param print
	 *            checks to print the value inside this function or not.
	 * @return the vector cordinates of the baricentic point.
	 * @throws IncompatibleOperandException
	 * @throws IllegalAccessException
	 */
	public static IVector parseAndRunBariCircleSecondMethod(String a, String b,
			String c, String t, boolean print)
			throws IncompatibleOperandException, IllegalAccessException {
		IVector aVec = Vector.parseSimple(a);
		IVector bVec = Vector.parseSimple(b);
		IVector cVec = Vector.parseSimple(c);

		IVector tVec = Vector.parseSimple(t);
		return bariCircleSecondMethod(aVec, bVec, cVec, tVec, print);
	}

	public static boolean checkIfDetZero(IMatrix matrix)
			throws IncompatibleOperandException {
		if (matrix.determinant() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
