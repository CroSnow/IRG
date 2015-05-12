package hr.fer.zemris.irg.lab1.demonstratura;

import hr.fer.zemris.irg.lab1.calculations.IRGCalculations;
import hr.fer.zemris.linearna.IVector;
import hr.fer.zemris.linearna.IncompatibleOperandException;
import hr.fer.zemris.linearna.Vector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Program za rjesavanje jednadbi. Korisnik unosi sustav a program izracuna
 * rjesenje.
 * 
 * @author Ivan Hrastinski
 * @version 1
 */
public class RjesavanjeJedn {

	public static void main(String[] args) throws IllegalAccessException, IncompatibleOperandException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String prvaJedn="";
		String drugaJedn="";
		String trecaJedn="";
		double[] prvaJednBrojke;
		double[] drugaJednBrojke;
		double[] trecaJednBrojke;
		System.out.println("Upisite prvu jednadbu.\n");
		try {
			prvaJedn = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.print("Upisite drugu jednadbu.\n");
		try {
			drugaJedn = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("Upisite trecu jednadbu.\n");
		try {
			trecaJedn = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		prvaJednBrojke=Parsers.StringNumbersToDoubleArray(prvaJedn);
		drugaJednBrojke=Parsers.StringNumbersToDoubleArray(drugaJedn);
		trecaJednBrojke=Parsers.StringNumbersToDoubleArray(trecaJedn);
		IVector a=new Vector(prvaJednBrojke[0],drugaJednBrojke[0],trecaJednBrojke[0]);
		IVector b=new Vector(prvaJednBrojke[1],drugaJednBrojke[1],trecaJednBrojke[1]);
		IVector c=new Vector(prvaJednBrojke[2],drugaJednBrojke[2],trecaJednBrojke[2]);
		IVector t=new Vector(prvaJednBrojke[3],drugaJednBrojke[3],trecaJednBrojke[3]);
		
		IVector result=IRGCalculations.bariCircleSecondMethod(a, b, c, t,false);
		System.out.println("[x,y,z]=["+result.get(0)+","+result.get(1)+","+result.get(2)+"]");
	}

}
