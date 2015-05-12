package hr.fer.zemris.irg.lab2.zad1.labos;

import hr.fer.zemris.irg.lab2.zad1.common.GLDisplay;
import hr.fer.zemris.irg.lab2.zad1.labos.Renderer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseInputHandler extends MouseAdapter {
	private Renderer renderer;
	
	
	public MouseInputHandler(Renderer renderer, GLDisplay glDisplay) {
		this.renderer = renderer;
		//this.glDisplay = glDisplay;
		glDisplay.registerMouseEventForHelp(MouseEvent.MOUSE_CLICKED, 0,
				"The mouse is clicked");

	}
	
	public void mouseClicked(MouseEvent e) {

		renderer.pointInput(e);
	}

}
