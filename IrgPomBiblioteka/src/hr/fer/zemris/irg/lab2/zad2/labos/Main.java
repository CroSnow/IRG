package hr.fer.zemris.irg.lab2.zad2.labos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Checks if a given point is inside or outside the shape.
 * 
 * @author Ivan Hrastinski
 * @version 1
 */
public class Main {
	// The file name from which we read.
	private static String inputFile = "tetrahedron.obj";
	// The shape from the file.
	private static Shape shape;
	// All the points of the shape.
	private static ArrayList<Point> points;

	public static void main(String[] args) {
		BufferedReader reader = null;
		BufferedReader inputPointReader = null;
		inputPointReader = new BufferedReader(new InputStreamReader(System.in));
		points = new ArrayList<Point>();
		System.out.println("Begin");
		try {
			reader = new BufferedReader(new FileReader(inputFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shape = new Shape();
		parseInput(reader);
		boolean pointToCheck = true;
		String point = "";
		while (pointToCheck) {
			System.out
					.println("Upisite tocku za provjeru u formatu 'x y z' ili quit za prekid upisa:");
			try {

				point = inputPointReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (point.startsWith("quit")) {
				pointToCheck = false;
			} else {
				Point v = Point.parseInput(point);
				if (shape.checkPoint(v)) {
					System.out.println("Tocka je unutar lika");
				} else {
					System.out.println("Tocka je izvan lika");
				}

			}

		}
		System.out.println("End");

	}

	/**
	 * Parses from the reader into a shape class.
	 * 
	 * @param reader
	 *            the readar we read from.
	 */
	public static void parseInput(BufferedReader reader) {
		String line = "";
		try {
			line = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (line != null) {
			if (line.startsWith("v")) {
				Point p = Point.parseInput(line.substring(1));
				points.add(p);

			} else if (line.startsWith("f")) {
				line = line.substring(1).trim();
				String pointsString[] = line.split(" ");
				int firstIndex = Integer.parseInt(pointsString[0].trim());
				int secondIndex = Integer.parseInt(pointsString[1].trim());
				int thirdIndex = Integer.parseInt(pointsString[2].trim());
				Triangle t = new Triangle(points.get(firstIndex - 1)
						.getCoordinate(), points.get(secondIndex - 1)
						.getCoordinate(), points.get(thirdIndex - 1)
						.getCoordinate());
				shape.addTriangle(t);
			}

			try {
				line = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
