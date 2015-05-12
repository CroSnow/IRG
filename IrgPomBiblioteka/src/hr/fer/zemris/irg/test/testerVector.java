package hr.fer.zemris.irg.test;

import hr.fer.zemris.linearna.*;

public class testerVector {
	
	
	public static void main(String[] args) {
		IVector a = new Vector(-2, 4, 1);
		IVector b = a.copyPart(2);
		IVector c = a.copyPart(5);
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(a.norm());
		
		try {
			System.out.println(a.cosine(a));
		} catch (IncompatibleOperandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(a.nNormalize());
		try {
			System.out.println(a.nFromHomogeneus());
		} catch (IncompatibleOperandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println(a.nSub(a));
		} catch (IncompatibleOperandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(a.nScalarMultiply(5));
		
	}

}
