package hr.fer.zemris.irg.lab3.zad2.labos;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import hr.fer.zemris.irg.lab3.zad2.common.GLDisplay;

/* 
 * Po�etni razred u kojem mo�ete definirati tekst koji �e pisat na vrhu OpenGL prozora.
 * Po�etnu veli�inu prozora mo�ete promjeniti u klasi GLDisplay (globalne varijable)
 * Ako �elite koristiti full-screen to mo�ete tako da promjenite u GLDisplay klasi 
 * u metodi GLDisplay(String, Capabilities) boolean varijablu fullscreen na true. 
 */


public class Main {
    public static void main(String[] args) {
    	GLDisplay neheGLDisplay = GLDisplay.createGLDisplay("Tekst koji �e pisati na vrhu OpenGL prozora");
    	BufferedReader reader=null;
    	try {
			reader=new BufferedReader(new FileReader("BernsteinPoints"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Bernstein bern= Bernstein.parseFromFile(reader);
        Renderer renderer = new Renderer(bern);
        InputHandler inputHandler = new InputHandler(renderer, neheGLDisplay);
        neheGLDisplay.addGLEventListener(renderer);
        neheGLDisplay.addKeyListener(inputHandler);
        neheGLDisplay.start();
    }
}
