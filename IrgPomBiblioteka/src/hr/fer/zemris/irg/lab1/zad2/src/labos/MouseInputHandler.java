
package hr.fer.zemris.irg.lab1.zad2.src.labos;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import hr.fer.zemris.irg.lab1.zad2.src.common.GLDisplay;

import javax.swing.*;

/**
 * The mouse handler for activating the mouse event for the Renderer.
 * 
 * @author Ivan Hrastinski
 * @version 1
 */
public class MouseInputHandler extends MouseAdapter {
	private Renderer renderer;
	private GLDisplay glDisplay;
	private int mousePresedTimes = 0;

	public MouseInputHandler(Renderer renderer, GLDisplay glDisplay) {
		this.renderer = renderer;
		this.glDisplay = glDisplay;
		glDisplay.registerMouseEventForHelp(MouseEvent.MOUSE_CLICKED, 0,
				"The mouse is clicked");
		glDisplay.registerMouseEventForHelp(MouseEvent.MOUSE_MOVED, 0,
				"The mouse has moved");

	}

	/**
	 * Activates on click and incresed the times pressed. The number of times
	 * presed is moded by 3. If the pressed times is greater than 1 it activates
	 * the move event listener.
	 */
	public void mouseClicked(MouseEvent e) {
		mousePresedTimes++;
		mousePresedTimes = mousePresedTimes % 3;
		if (mousePresedTimes > 0) {
			glDisplay.addMouseMotionListener(this);
		} else {
			glDisplay.removeMouseMotionListener(this);
		}
		renderer.mouseClicked(e);
	}

	/**
	 * The move event.
	 */
	public void mouseMoved(MouseEvent e) {

		renderer.mouseMoved(e);
	}

}
