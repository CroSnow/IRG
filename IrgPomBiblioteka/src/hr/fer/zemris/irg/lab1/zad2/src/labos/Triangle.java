package hr.fer.zemris.irg.lab1.zad2.src.labos;

import javax.media.opengl.GL;
/**
 * A basic class for drawing triangles.
 * @author Ivan Hrastinski
 * @version 1
 */
public class Triangle {
	// The first point of the triangle.
	private float[] a;
	// The second point of the triangle.
	private float[] b;
	// The third point of the triangle.
	private float[] c;
	// The color of the triangle.
	private Colors color;

	public Triangle(float[] a, float[] b, float[] c, Colors color) {
		this.a = new float[3];
		this.b = new float[3];
		this.c = new float[3];
		this.color = color;
		for (int i = 0; i < 3; i++) {
			this.a[i] = a[i];
			this.b[i] = b[i];
			this.c[i] = c[i];

		}
	}

	/**
	 * Draws 3d triangles in the given gl.
	 * @param gl the gl we draw on.
	 */
	public void draw(GL gl){
		
		gl.glColor3d(this.color.getR(),this.color.getG(),this.color.getB());
		gl.glBegin(GL.GL_TRIANGLES);		//Definiramo crtanje trokuta
		
        gl.glVertex3f(a[0], a[1], a[2]);	
        gl.glVertex3f(b[0], b[1], b[2]);	
        gl.glVertex3f(c[0], c[1], c[2]);   
        
		/*
        gl.glVertex2f(a[0], a[1]);	
        gl.glVertex2f(b[0], b[1]);	
        gl.glVertex2f(c[0], c[1]);   	
		*/
        gl.glEnd();							// Završavamo crtanje trokuta
		
	}
	/**
	 * @return the a
	 */
	public float[] getA() {
		return a;
	}

	/**
	 * @param a
	 *            the a to set
	 */
	public void setA(float... a) {
		for (int i = 0; i < 3; i++) {
			this.a[i] = a[i];
		}
	}

	/**
	 * @return the b
	 */
	public float[] getB() {
		return b;
	}

	/**
	 * @param b
	 *            the b to set
	 */
	public void setB(float... b) {
		for (int i = 0; i < 3; i++) {
			this.b[i] = b[i];
		}

	}

	/**
	 * @return the c
	 */
	public float[] getC() {
		return this.c;
	}

	/**
	 * @param c
	 *            the c to set
	 */
	public void setC(float... c) {
		for (int i = 0; i < 3; i++) {
			this.c[i] = c[i];
		}
	}

	/**
	 * @return the color
	 */
	public Colors getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(Colors color) {

		this.color = color;

	}

}
