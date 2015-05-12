package hr.fer.zemris.irg.lab1.zad2.src.labos;
import hr.fer.zemris.irg.lab1.zad2.src.common.GLDisplay;

/* 
 * Poèetni razred u kojem možete definirati tekst koji æe pisat na vrhu OpenGL prozora.
 * Poèetnu velièinu prozora možete promjeniti u klasi GLDisplay (globalne varijable)
 * Ako želite koristiti full-screen to možete tako da promjenite u GLDisplay klasi 
 * u metodi GLDisplay(String, Capabilities) boolean varijablu fullscreen na true. 
 */

/**
 * Dodani Mouse listeneri.
 * @author Ivan Hrastinski
 * @version 1
 */
public class Main {
    public static void main(String[] args) {
    	GLDisplay neheGLDisplay = GLDisplay.createGLDisplay("TRIFORCE");
        Renderer renderer = new Renderer();
        InputHandler inputHandler = new InputHandler(renderer, neheGLDisplay);
        MouseInputHandler mouseInputHandler=new MouseInputHandler(renderer, neheGLDisplay);
        neheGLDisplay.addGLEventListener(renderer);
        neheGLDisplay.addKeyListener(inputHandler);
        neheGLDisplay.addMouseListener(mouseInputHandler);

        neheGLDisplay.start();
    }
}
