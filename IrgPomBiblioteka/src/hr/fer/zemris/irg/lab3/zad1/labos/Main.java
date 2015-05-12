package hr.fer.zemris.irg.lab3.zad1.labos;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;




import hr.fer.zemris.irg.lab3.zad1.common.GLDisplay;
import hr.fer.zemris.linearna.IMatrix;
import hr.fer.zemris.linearna.Matrix;

/* 
 * Poèetni razred u kojem možete definirati tekst koji æe pisat na vrhu OpenGL prozora.
 * Poèetnu velièinu prozora možete promjeniti u klasi GLDisplay (globalne varijable)
 * Ako želite koristiti full-screen to možete tako da promjenite u GLDisplay klasi 
 * u metodi GLDisplay(String, Capabilities) boolean varijablu fullscreen na true. 
 */


public class Main {
	// The file name from which we read.
	private static String inputFile = "kocka.obj";
	// The shape from the file.
	private static Shape shape;
	// All the points of the shape.
	private static ArrayList<Point> points;
    public static void main(String[] args) {
    	BufferedReader reader=null;
    	BufferedReader viewReader=null;
		points = new ArrayList<Point>();
		System.out.println("Begin");
		try {
			viewReader=new BufferedReader(new FileReader("glediste"));
			reader = new BufferedReader(new FileReader(inputFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		shape = new Shape();
		parseInput(reader);
		
		Point O=null;
		Point G=null;
		
		String line="";
		try {
			line=viewReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(line!=null){

			if (line.startsWith("G")){
				String toParse=line.split("\\(")[1].trim().split("\\)")[0].trim();
				String x=toParse.split(",")[0].trim();
				String y=toParse.split(",")[1].trim();
				String z=toParse.split(",")[2].trim();
				G=new Point(Float.parseFloat(x), Float.parseFloat(y), Float.parseFloat(z));
				
			}
			else if (line.startsWith("O")){
				String toParse=line.split("\\(")[1].trim().split("\\)")[0].trim();
				String x=toParse.split(",")[0].trim();
				String y=toParse.split(",")[1].trim();
				String z=toParse.split(",")[2].trim();
				O=new Point(Float.parseFloat(x), Float.parseFloat(y), Float.parseFloat(z));
				
			}
			try {
				line=viewReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		shape.changePoints(MatrixCalculation.calculateMatrix(O, G));
		shape.changePoints(MatrixCalculation.calculateHMatrix(O, G));
    	GLDisplay neheGLDisplay = GLDisplay.createGLDisplay("Transformacija pogleda");
        Renderer renderer = new Renderer(shape,O,G);
        InputHandler inputHandler = new InputHandler(renderer, neheGLDisplay);
        neheGLDisplay.addGLEventListener(renderer);
        neheGLDisplay.addKeyListener(inputHandler);
        neheGLDisplay.start();
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
