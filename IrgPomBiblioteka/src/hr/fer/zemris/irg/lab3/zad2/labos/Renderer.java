package hr.fer.zemris.irg.lab3.zad2.labos;

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
    private Bernstein bern;

    
    public Renderer (Bernstein bern){
    	this.bern=bern;
    }
    //iscrtavanje objekata
    public void display(GLAutoDrawable gLDrawable) {
        final GL gl = gLDrawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();

    	   gl.glTranslatef(-2, 0.0f, -5.0f); //objekti se poveæaju jer se približe kameri (z-os)
    	   bern.draw(gl);
    	   bern.drawControl(gl);
    	
    }


    //ova metoda treba ostati prazna!!
    public void displayChanged(GLAutoDrawable gLDrawable, boolean modeChanged, boolean deviceChanged) {
    }

    /** Called by the drawable immediately after the OpenGL context is
     * initialized for the first time. Can be used to perform one-time OpenGL
     * initialization such as setup of lights and display lists.
     * @param gLDrawable The GLAutoDrawable object.
     */
    public void init(GLAutoDrawable gLDrawable) {
        GL gl = gLDrawable.getGL();
        gl.glShadeModel(GL.GL_SMOOTH);              // Enable Smooth Shading
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);    // Crna pozadina

    }


    /**
     * Called by the drawable during the first repaint after the component has
     * been resized. The client can update the viewport and view volume of the
     * window appropriately, for example by a call to
     * GL.glViewport(int, int, int, int); note that for convenience the component
     * has already called GL.glViewport(int, int, int, int)(x, y, width, height)
     * when this method is called, so the client may not have to do anything in
     * this method.
     * @param gLDrawable The GLAutoDrawable object.
     * @param x The X Coordinate of the viewport rectangle.
     * @param y The Y coordinate of the viewport rectanble.
     * @param width The new width of the window.
     * @param height The new height of the window.
     */
    public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) {
        final GL gl = gLDrawable.getGL();

        if (height <= 0) // avoid a divide by zero error!
            height = 1;
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();;
    }


    
}
