package hr.fer.zemris.irg.lab2.zad1.labos;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import hr.fer.zemris.irg.lab1.zad2.src.labos.Colors;

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

class Renderer implements GLEventListener {
	private GLU glu = new GLU();
	// Poèetna širina prozora.
	private int windowWidth = 800;
	// Poèetna visina prozora.
	private int windowHeight = 600;
	// The polygon we draw.
	private Polygon poly;
	// The point we compare if its inside the polygon.
	private Point toCompare;
	// Flags with which we switch the method of drawing.
	private boolean drawType = true;
	// Flag which checks if the point is compared so it does not compare
	// multiple times for the same point.
	private boolean compared = true;
	// Flag which checks if we have given a point to check.
	private boolean pointIsAlive = false;

	/**
	 * Constructor which takes the polygon we want to draw.
	 * 
	 * @param p
	 *            the polygon we defined.
	 */
	Renderer(Polygon p) {
		this.poly = p;
	}

	// iscrtavanje objekata
	public void display(GLAutoDrawable gLDrawable) {
		final GL gl = gLDrawable.getGL();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glPointSize(1.0f);
		if (drawType) {
			poly.draw(gl);
			if (pointIsAlive) {
				gl.glPointSize(3.0f);
				gl.glBegin(GL.GL_POINTS);
				gl.glColor3f(Colors.RED.getR(), Colors.RED.getG(),
						Colors.RED.getB());
				gl.glVertex2d(this.toCompare.getX(), this.toCompare.getY());
				gl.glEnd();
				if (!compared) {
					if (poly.contains(toCompare)) {
						System.out.println("Tocka je unutar polygona");
					} else {
						System.out.println("Tocka je izvan polygona");
					}
					compared = true;
				}
			}
		} else {
			poly.color(gl, Colors.MAGENTA);
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
				Colors.WHITE.getB(), 1);

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
		gl.glOrtho(0, width - 1, 0, height - 1, 0, 1);
		// glu.gluPerspective(45.0f, h, 1.0, 20.0);
		gl.glMatrixMode(GL.GL_MODELVIEW);
		// gl.glLoadIdentity();
		gl.glDisable(GL.GL_DEPTH_TEST);
		;
	}

	/**
	 * The method which saves and manages the flags for ckecking if a point is
	 * inside or outside the polygon.
	 * 
	 * @param e
	 *            the coordinates of the point to check.
	 */
	public void pointInput(MouseEvent e) {
		if (drawType) {
			this.compared = false;
			this.pointIsAlive = true;

			this.toCompare = new Point(e.getPoint().x, this.windowHeight
					- (e.getPoint().y + 1));
		}

	}

	/**
	 * Changes the flags between coloring the polygon and checking if the point
	 * is inside the polygon.
	 */
	public void changeTypeToDraw() {
		this.pointIsAlive = false;
		this.compared = true;
		this.drawType = !this.drawType;

	}

}
