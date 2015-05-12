package hr.fer.zemris.irg.lab2.zad1.labos;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import hr.fer.zemris.irg.lab2.zad1.common.GLDisplay;

/* 
 * Poèetni razred u kojem možete definirati tekst koji æe pisat na vrhu OpenGL prozora.
 * Poèetnu velièinu prozora možete promjeniti u klasi GLDisplay (globalne varijable)
 * Ako želite koristiti full-screen to možete tako da promjenite u GLDisplay klasi 
 * u metodi GLDisplay(String, Capabilities) boolean varijablu fullscreen na true. 
 */

/**
 * Nadopunjen tako da ucitava broj tocaka, pa ucita te tocke i stvara novi
 * poligon iz tih tocaka.Taj polygon predaje rendereru. Dodani Handleri za mis i
 * tipkovnicu , space za mijenjanje nacina crtanja, klik misa za definiranje
 * tocke koju provjeravamo.
 * 
 * @author Ivan Hrastinski
 * @version 1
 */
public class Main {
	public static void main(String[] args) {
		GLDisplay neheGLDisplay = GLDisplay.createGLDisplay("Poligoni");
		System.out.println("Molim broj vrhova poligona: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

		String stringNumberOfPoints = "";
		try {
			stringNumberOfPoints = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stringNumberOfPoints = stringNumberOfPoints.trim();
		int numberOfPoints = 0;
		String line = "";
		int x;
		int y;
		Point p;
		ArrayList<Point> points = new ArrayList<Point>();
		Polygon poly;

		numberOfPoints = Integer.parseInt(stringNumberOfPoints);
		for (int i = 0; i < numberOfPoints; i++) {
			System.out.println("Unesite vrh formata 'x,y':");
			try {
				line = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			x = Integer.parseInt(line.split(",")[0].trim());
			y = Integer.parseInt(line.split(",")[1].trim());
			p = new Point(x, y);
			points.add(p);

		}
		p = new Point(points.get(0));
		points.add(p);
		poly = new Polygon(points);
		Renderer renderer = new Renderer(poly);
		MouseInputHandler mouseHandler = new MouseInputHandler(renderer,
				neheGLDisplay);
		InputHandler inputHandler = new InputHandler(renderer, neheGLDisplay);
		neheGLDisplay.addGLEventListener(renderer);
		neheGLDisplay.addKeyListener(inputHandler);
		neheGLDisplay.addMouseListener(mouseHandler);
		neheGLDisplay.start();
	}
}
