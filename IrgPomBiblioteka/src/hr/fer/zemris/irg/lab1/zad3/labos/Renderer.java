package hr.fer.zemris.irg.lab1.zad3.labos;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

import hr.fer.zemris.irg.lab1.zad2.src.labos.Colors;

/*
 * Klasa u kojoj se odvija iscrtavanje na ekran (metoda display). Inicijalizacija parametara se odvija
 * u metodi init. Metoda reshape æe se aktivirati kada korisnik promijeni širinu ili visinu prozora.
 * 
 * U programu se iscrtavaju linija i trokut ispod nje. Tipkama r i b mijenjaju se boje na liniji i trokutu.
 * Tipkom "t" mijenja se translacija objekta. 
 * 
 */

class Renderer implements GLEventListener {
	private GLU glu = new GLU();

	// Poèetna širina prozora.
	private int windowWidth = 800;
	// Poèetna visina prozora.
	private int windowHeight = 600;
	// Zastavica koja prati jeli to prvi ili drugi klik misem.
	private boolean firstPres = true;
	// Lista u koju se spremaju svi pravci za crtanje.
	private ArrayList<Line> linesToDraw;
	// Pomocna varijabla u kojoj se pamti prva tocka pravca.
	private Point start;
	// Zastavica kojom se odredjuje pomocno crtanje crvenih pravca.
	private boolean kontrola = true;
	// Zastavica kojom se odredjuje odsijecanje pravca.
	private boolean odsijecanje = false;

	// iscrtavanje objekata
	public void display(GLAutoDrawable gLDrawable) {
		final GL gl = gLDrawable.getGL();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glPointSize(1.0f);

		if (odsijecanje) {
			gl.glColor3f(Colors.GREEN.getR(), Colors.GREEN.getG(),
					Colors.GREEN.getB());
			gl.glBegin(GL.GL_LINE_STRIP);
			{
				gl.glVertex2i(windowWidth / 4, windowHeight / 4);
				gl.glVertex2i(3 * windowWidth / 4, windowHeight / 4);
				gl.glVertex2i(3 * windowWidth / 4, 3 * windowHeight / 4);
				gl.glVertex2i(windowWidth / 4, 3 * windowHeight / 4);
				gl.glVertex2i(windowWidth / 4, windowHeight / 4);
				gl.glEnd();
			}
			if (!linesToDraw.isEmpty()) {
				for (Line drawingLine : linesToDraw) {
					drawingLine.drawCut(gl, windowWidth / 4, windowHeight / 4,
							3 * windowWidth / 4, 3 * windowHeight / 4);
				}
			}

		} else {
			if (!linesToDraw.isEmpty()) {
				for (Line drawingLine : linesToDraw) {
					drawingLine.draw(gl);
				}
			}
		}
		if (kontrola) {
			if (!linesToDraw.isEmpty()) {
				for (Line drawingLine : linesToDraw) {
					drawingLine.drawRedLine(gl);
				}
			}

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
		GL gl = gLDrawable.getGL();
		gl.glShadeModel(GL.GL_SMOOTH); // Enable Smooth Shading
		gl.glClearColor(Colors.WHITE.getR(), Colors.WHITE.getG(),
				Colors.WHITE.getB(), 1); // Crna pozadina
		linesToDraw = new ArrayList<Line>();

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
		gl.glOrtho(0, width - 1, height - 1, 0, 0, 1);
		// glu.gluPerspective(45.0f, h, 1.0, 20.0);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		//gl.glLoadIdentity();
		gl.glDisable(GL.GL_DEPTH_TEST);

		;
	}

	/**
	 * Metoda koja se brine za crtanje pravce pritiskom misa nad canvasom.
	 * 
	 * @param e
	 *            podaci primljeni preko misa.
	 */
	public void lineInput(MouseEvent e) {
		if (firstPres) {
			firstPres = false;
			start = new Point(e.getPoint().x, e.getPoint().y);
		} else {
			firstPres = true;
			Point end = new Point(e.getPoint().x, e.getPoint().y);
			linesToDraw.add(new Line(start, end));

		}

	}

	// ove metode pozivaju se iz klase InputHandler i mijenjaju translaciju i
	// boju trokuta i linije

	/**
	 * Mijenja zastavicu kontrola.
	 */
	public void promijeniKontrolu() {
		kontrola = !kontrola;
	}

	/**
	 * Mijenja zastavicu odsijecanje.
	 */
	public void promijeniOdsijecanje() {
		odsijecanje = !odsijecanje;
	}

}
