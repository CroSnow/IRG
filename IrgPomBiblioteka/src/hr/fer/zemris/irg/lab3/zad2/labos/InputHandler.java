package hr.fer.zemris.irg.lab3.zad2.labos;

import hr.fer.zemris.irg.lab3.zad2.common.GLDisplay;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * Klasa u kojoj se definira korištenje tipkovnice. 
 */


class InputHandler extends KeyAdapter {
    private Renderer renderer;

    public InputHandler(Renderer renderer, GLDisplay glDisplay) {
        this.renderer = renderer;
        glDisplay.registerKeyStrokeForHelp(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "Mijenjamo boju u crvenu");
     
    }

    public void keyPressed(KeyEvent e) {
        processKeyEvent(e, true);
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_R:
           
            	break;
  
            default:
                processKeyEvent(e, false);
        }
    }

    private void processKeyEvent(KeyEvent e, boolean pressed) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                // isto kao i keyReleased, samo se aktivira èim se pritisne tipka 
                break;
        }
    }
}

