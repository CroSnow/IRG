package hr.fer.zemris.irg.lab2.zad1.labos;

import hr.fer.zemris.irg.lab2.zad1.common.GLDisplay;

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
        glDisplay.registerKeyStrokeForHelp(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "Mijenjamo nacin rada (provjera tocke/bojanje poligona)");
     
    }

    public void keyPressed(KeyEvent e) {
        processKeyEvent(e, true);
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                renderer.changeTypeToDraw();
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

