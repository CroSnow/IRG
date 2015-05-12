package hr.fer.zemris.irg.lab1.zad2.src.labos;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

/*
 * Klasa u kojoj se odvija iscrtavanje na ekran (metoda display). Inicijalizacija parametara se odvija
 * u metodi init. Metoda reshape æe se aktivirati kada korisnik promijeni širinu ili visinu prozora.
 * 
 * U programu se iscrtavaju linija i trokut ispod nje. Tipkama r i b mijenjaju se boje na liniji i trokutu.
 * Tipkom "t" mijenja se translacija objekta. 
 * 
 */

class Renderer implements GLEventListener {// poèetna širina prozora
	private int windowWidth = 800;
	// poèetna visina prozor
	private int windowHeight = 600;
	private GLU glu = new GLU();
	// Polje koje pamti sve trokute za iscrtavanje.
	private ArrayList<Triangle> triangles;
	// Brojac kojim se odredjuje u kojem dijelu crtanja trokuta se trenutno
	// nalazimo
	private int mousePresed = 0;
	// Tocka odredjena prvim pritiskom misa.
	private float[] first;
	// Tocka odredjene drugim pritiskom misa.
	private float[] second;
	// Tocka odredjena trecim pritiskom misa.
	private float[] third;

	private boolean translatiraj;
	// Razlicite boje koje se mogu koristit.
	private final Colors[] colors = { Colors.RED, Colors.GREEN, Colors.BLUE,
			Colors.CYAN, Colors.YELLOW, Colors.MAGENTA };
	// Aktivna boja. Inicijalno postavljena na crvenu.
	private Colors activeColor = colors[0];
	// Setac koji se krece po razlicitim bojama
	private int colorWalker = 0;
	// Pomocna zastavica koja provjerava dali se treba crtati pomocna crta.
	private boolean lineToDraw = false;
	// Pomocna varijabla koja provjerava dali se treba crtati pomocni trokut.
	private boolean triToDraw = false;

	// iscrtavanje objekata
	public void display(GLAutoDrawable gLDrawable) {
		final GL gl = gLDrawable.getGL();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glPointSize(1.0f);
		/**
		 * Iscrtava pravokutnik u gornjem desnom kutu u kojem je naznacena boja
		 * koja se trenutno koristi.
		 */
		if ((windowWidth >= 5) && (windowHeight >= 5)) {
			gl.glColor3f(activeColor.getR(), activeColor.getG(),
					activeColor.getB());
			gl.glBegin(GL.GL_QUADS);
			gl.glVertex2f(windowWidth, 0);
			gl.glVertex2f(windowWidth - 5, 0);
			gl.glVertex2f(windowWidth - 5, 5);
			gl.glVertex2f(windowWidth, 5);
			gl.glEnd();
		}
		if (!triangles.isEmpty()) {

			for (Triangle toDraw : triangles) {
				toDraw.draw(gl);
			}
		}
		if (triToDraw) {
			drawTempTri().draw(gl);
		}
		if (lineToDraw) {
			gl.glColor3f(activeColor.getR(), activeColor.getG(),
					activeColor.getB());
			gl.glBegin(GL.GL_LINE_STRIP);
			gl.glVertex2f(first[0], first[1]);
			gl.glVertex2f(second[0], second[1]);
			gl.glEnd();
		}
		gl.glFlush();
	}

	// ova metoda treba ostati prazna!!
	public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged,
			boolean deviceChanged) {
	}

	/**
	 * Called by the drawable immediately after the OpenGL context is
	 * initialized for the first time. Can be used to perform one-time OpenGL
	 * initialization such as setup of lights and display lists.
	 * 
	 * @param gLDrawable
	 *            The GLAutoDrawable object.
	 */
	public void init(GLAutoDrawable gLDrawable) {

		this.triangles = new ArrayList<Triangle>();
		GL gl = gLDrawable.getGL();
		gl.glShadeModel(GL.GL_SMOOTH); // Enable Smooth Shading
		gl.glClearColor(1.0f, 1.0f, 1.0f, 0.5f); //

	}

	/**
	 * Called by the drawable during the first repaint after the component has
	 * been resized. The client can update the viewport and view volume of the
	 * window appropriately, for example by a call to GL.glViewport(int, int,
	 * int, int); note that for convenience the component has already called
	 * GL.glViewport(int, int, int, int)(x, y, width, height) when this method
	 * is called, so the client may not have to do anything in this method.
	 * 
	 * @param gLDrawable
	 *            The GLAutoDrawable object.
	 * @param x
	 *            The X Coordinate of the viewport rectangle.
	 * @param y
	 *            The Y coordinate of the viewport rectanble.
	 * @param width
	 *            The new width of the window.
	 * @param height
	 *            The new height of the window.
	 */
	public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width,
			int height) {
		final GL gl = gLDrawable.getGL();
		this.windowHeight = height;
		this.windowWidth = width;
		if (height <= 0) // avoid a divide by zero error!
			height = 1;
		final float h = (float) width / (float) height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL.GL_PROJECTION);
		gl.glLoadIdentity();

		gl.glOrtho(0, width - 1, height - 1,0, 0, 1);

		// glu.gluPerspective(45.0f, h, 1.0, 20.0);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		gl.glDisable(GL.GL_DEPTH_TEST);
	//	gl.glLoadIdentity();

	}

	/**
	 * The method which decides in which part of the triangle drawing cicle we
	 * are when the mouse is pressed.
	 * 
	 * @param e
	 */
	public void mouseClicked(MouseEvent e) {
		mousePresed++;
		switch (mousePresed) {
		case 1:
			firstPress(e.getPoint().x, e.getPoint().y);
			break;
		case 2:
			secondPress(e.getPoint().x, e.getPoint().y);
			break;
		case 3:
			thirdPress(e.getPoint().x, e.getPoint().y);
			mousePresed = 0;
			break;

		}
	}

	/**
	 * The first mouse click. The first point is saved.
	 * 
	 * @param x
	 *            cordinates of the mouse pointer.
	 * @param y
	 *            cordinates of the mouse pointer.
	 */
	private void firstPress(int x, int y) {
		first = new float[] { x, y, 0 };

	}

	/**
	 * The Second mouse click.The second point is saved.
	 * 
	 * @param x
	 *            cordinates of the mouse pointer.
	 * @param y
	 *            cordinates of the mouse pointer.
	 */
	private void secondPress(int x, int y) {
		second = new float[] { x, y, 0 };

	}

	/**
	 * The first mouse click.The Third point is saved. A new Triangle is made
	 * and added to the list of triangles.
	 * 
	 * @param x
	 *            cordinates of the mouse pointer.
	 * @param y
	 *            cordinates of the mouse pointer.
	 */
	private void thirdPress(int x, int y) {
		third = new float[] { x, y, 0 };
		Triangle done = new Triangle(first, second, third, activeColor);
		this.triangles.add(done);
	}

	/**
	 * Method for changing the current active collor.
	 */
	public void changeColor() {
		colorWalker++;
		colorWalker = colorWalker % 6;
		this.activeColor = colors[colorWalker];

	}

	/**
	 * Event for the mouse move which decides which temporary shape should be
	 * drawn, if any.
	 * 
	 * @param e
	 *            the mouse event.
	 */
	public void mouseMoved(MouseEvent e) {
		if (mousePresed == 1) {
			second = new float[] { e.getPoint().x, e.getPoint().y, 0 };
			lineToDraw = true;
			triToDraw = false;
		} else if (mousePresed == 2) {
			third = new float[] { e.getPoint().x, e.getPoint().y, 0 };
			triToDraw = true;
			lineToDraw = false;
		} else {
			lineToDraw = false;
			triToDraw = false;
		}
	}

	/**
	 * Method for the temporary triangle.
	 * 
	 * @return the temporary trianlge made from the first point , second point ,
	 *         and the current mouse position.
	 */
	private Triangle drawTempTri() {
		return new Triangle(first, second, third, activeColor);
	}

	/**
	 * Method for changing the current active collor.
	 */
	public void changeColorBack() {
		if (colorWalker == 0) {
			colorWalker = 5;
		} else {
			colorWalker--;
			colorWalker = colorWalker % 6;
		}
		this.activeColor = colors[colorWalker];

	}

	public void translatiraj() {
		translatiraj = !translatiraj;
	}

}
