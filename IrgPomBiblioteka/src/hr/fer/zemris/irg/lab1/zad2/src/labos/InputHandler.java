package hr.fer.zemris.irg.lab1.zad2.src.labos;

import hr.fer.zemris.irg.lab1.zad2.src.common.GLDisplay;

import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;

/*
 * Klasa u kojoj se definira korištenje tipkovnice. 
 */


class InputHandler extends KeyAdapter {
    private Renderer renderer;
    

    public InputHandler(Renderer renderer, GLDisplay glDisplay) {
        this.renderer = renderer;
        glDisplay.registerKeyStrokeForHelp(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "Mijenjamo boju unazad");
        glDisplay.registerKeyStrokeForHelp(KeyStroke.getKeyStroke(KeyEvent.VK_N, 0), "Mijenjamo boju");
    //    glDisplay.registerKeyStrokeForHelp(KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, 0), "Zoom in");

    }

    public void keyPressed(KeyEvent e) {
        processKeyEvent(e, true);
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_P:
                renderer.changeColorBack();
            	break;
            case KeyEvent.VK_N:
                renderer.changeColor();
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

