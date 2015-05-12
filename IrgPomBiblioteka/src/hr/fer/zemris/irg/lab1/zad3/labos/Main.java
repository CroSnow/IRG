package hr.fer.zemris.irg.lab1.zad3.labos;
import hr.fer.zemris.irg.lab1.zad3.common.GLDisplay;

/* 
 * Poèetni razred u kojem možete definirati tekst koji æe pisat na vrhu OpenGL prozora.
 * Poèetnu velièinu prozora možete promjeniti u klasi GLDisplay (globalne varijable)
 * Ako želite koristiti full-screen to možete tako da promjenite u GLDisplay klasi 
 * u metodi GLDisplay(String, Capabilities) boolean varijablu fullscreen na true. 
 */


public class Main {
    public static void main(String[] args) {
    	GLDisplay neheGLDisplay = GLDisplay.createGLDisplay("Crtam crte");
        Renderer renderer = new Renderer();
        InputHandler inputHandler = new InputHandler(renderer, neheGLDisplay);
        MouseInputHandler mouseHandler=new MouseInputHandler(renderer,neheGLDisplay);
        neheGLDisplay.addGLEventListener(renderer);
        neheGLDisplay.addKeyListener(inputHandler);
        neheGLDisplay.addMouseListener(mouseHandler);
        neheGLDisplay.start();
    }
}
