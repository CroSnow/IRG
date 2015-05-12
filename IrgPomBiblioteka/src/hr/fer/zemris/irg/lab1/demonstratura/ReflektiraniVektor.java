package hr.fer.zemris.irg.lab1.demonstratura;

import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.IncompatibleOperandException;
import hr.fer.zemris.linearna.Vector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Korisnik unosi dva vektore i dobije ispisano rekletirani prvi vektor oko
 * drugoga.
 * 
 * @author Ivan Hrastinski
 * @version 1
 */
public class ReflektiraniVektor {

	public static void main(String[] args) throws IncompatibleOperandException {
		String prvi = "";
		String drugi = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		System.out.println("Unesite prvi vektor");
		try {
			prvi = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Unesite drugi vektor");
		try {
			drugi = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IVector a = Vector.parseSimple(prvi);
		IVector b = Vector.parseSimple(drugi);
		a.normalize();
		IVector v = a.nScalarMultiply(2);
		double scalarPro = a.scalarProduct(b);
		IVector refl = v.nScalarMultiply(scalarPro).sub(b);
		System.out.println(refl);

	}

}
