package hr.fer.zemris.irg.lab1.demonstratura;
/**
 * A class which has diferent parsers
 * @author Ivan Hrastinski
 * @version 1
 */
public class Parsers {

	/**
	 * Parses a string which contains doubles divided by a , to an double array.
	 * @param input
	 * @return
	 */
	public static double[] StringNumbersToDoubleArray(String input){
		String[] split=input.split(",");
		double [] numbers=new double[split.length];
		for(int i=0;i<split.length;i++){
			numbers[i]=Double.parseDouble(split[i]);
		}
		return numbers;
		
	}
}
