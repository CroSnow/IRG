package hr.fer.zemris.irg.lab1.zad3.labos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import hr.fer.zemris.irg.lab1.zad3.common.GLDisplay;

import javax.swing.*;

/**
 * The mouse handler for activating the mouse event for the Renderer.
 * 
 * @author Ivan Hrastinski
 * @version 1
 */
public class MouseInputHandler extends MouseAdapter {
	private Renderer renderer;
//	private GLDisplay glDisplay;
//	private int mousePresedTimes = 0;

	public MouseInputHandler(Renderer renderer, GLDisplay glDisplay) {
		this.renderer = renderer;
		//this.glDisplay = glDisplay;
		glDisplay.registerMouseEventForHelp(MouseEvent.MOUSE_CLICKED, 0,
				"The mouse is clicked");

	}

/**
 *Tels the renderer that the mouse is pressed.
 */
	public void mouseClicked(MouseEvent e) {

		renderer.lineInput(e);
	}


}
