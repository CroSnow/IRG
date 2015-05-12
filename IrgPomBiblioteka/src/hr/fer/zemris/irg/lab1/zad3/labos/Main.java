package hr.fer.zemris.irg.lab1.zad3.labos;
import hr.fer.zemris.irg.lab1.zad3.common.GLDisplay;

/* 
 * Po�etni razred u kojem mo�ete definirati tekst koji �e pisat na vrhu OpenGL prozora.
 * Po�etnu veli�inu prozora mo�ete promjeniti u klasi GLDisplay (globalne varijable)
 * Ako �elite koristiti full-screen to mo�ete tako da promjenite u GLDisplay klasi 
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
