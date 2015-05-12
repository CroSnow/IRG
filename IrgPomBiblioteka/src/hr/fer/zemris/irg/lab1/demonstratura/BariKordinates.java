package hr.fer.zemris.irg.lab1.demonstratura;

import hr.fer.zemris.irg.lab1.calculations.IRGCalculations;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.IncompatibleOperandException;
import hr.fer.zemris.linearna.Vector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class ask the user for 4 points and determines the baricentric kordinates.
 * @author Ivan Hrastinski
 * @version 1
 */
public class BariKordinates {

	public static void main(String[] args) throws IllegalAccessException, IncompatibleOperandException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String input="";
		System.out.println("Tocka A");
		try {
			input=reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IVector a=Vector.parseSimple(input);
		
		
		System.out.println("Tocka B");
		try {
			input=reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IVector b=Vector.parseSimple(input);
		
		
		System.out.println("Tocka C");
		try {
			input=reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IVector c=Vector.parseSimple(input);
		
		
		System.out.println("Tocka T");
		try {
			input=reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IVector t=Vector.parseSimple(input);
		
		IVector bari=IRGCalculations.bariCircleSecondMethod(a, b, c, t,true);
		
		

	}

}
