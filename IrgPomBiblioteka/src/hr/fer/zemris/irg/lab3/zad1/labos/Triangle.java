package hr.fer.zemris.irg.lab3.zad1.labos;

import hr.fer.zemris.linearna.IMatrix;

import javax.media.opengl.GL;

public class Triangle {
	// The x coordinates.
	private float[] x;
	// The y coordinates.
	private float[] y;
	// The z coordinates.
	private float[] z;

	// The a parametar.
	private float a;
	// The b parametar.
	private float b;
	// The c parametar.
	private float c;
	// The d parametar.
	private float d;

	/**
	 * Creates a trinagle from 3 points.
	 * 
	 * @param first
	 *            the first point
	 * @param second
	 *            the second point
	 * @param third
	 *            the third point
	 */
	public Triangle(float[] first, float[] second, float[] third) {
		x = new float[3];
		y = new float[3];
		z = new float[3];

		x[0] = first[0];
		x[1] = second[0];
		x[2] = third[0];

		y[0] = first[1];
		y[1] = second[1];
		y[2] = third[1];

		z[0] = first[2];
		z[1] = second[2];
		z[2] = third[2];

		a = (y[1] - y[0]) * (z[2] - z[0]) - (z[1] - z[0]) * (y[2] - y[0]);
		b = -(x[1] - x[0]) * (y[2] - y[0]) + (z[1] - z[0]) * (x[2] - x[0]);
		c = (x[1] - x[0]) * (y[2] - y[0]) - (y[1] - y[0]) * (x[2] - x[0]);
		d = -x[0] * a - y[0] * b - z[0] * c;
	}

	/**
	 * Checks in which position is the point compared to this triangle (over on
	 * under )(+,0,-).
	 * 
	 * @param p
	 *            the point we compare
	 * @return a positive number if its over, 0 if its in , a negative number if
	 *         its under.
	 */
	public float position(Point p) {
		return a * p.getX() + b * p.getY() + c * p.getZ() + d;
	}

	/**
	 * @return the x
	 */
	public float[] getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(float[] x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public float[] getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(float[] y) {
		this.y = y;
	}

	/**
	 * @return the z
	 */
	public float[] getZ() {
		return z;
	}

	/**
	 * @param z
	 *            the z to set
	 */
	public void setZ(float[] z) {
		this.z = z;
	}

	/**
	 * @return the a
	 */
	public float getA() {
		return a;
	}

	/**
	 * @param a
	 *            the a to set
	 */
	public void setA(float a) {
		this.a = a;
	}

	/**
	 * @return the b
	 */
	public float getB() {
		return b;
	}

	/**
	 * @param b
	 *            the b to set
	 */
	public void setB(float b) {
		this.b = b;
	}

	/**
	 * @return the c
	 */
	public float getC() {
		return c;
	}

	/**
	 * @param c
	 *            the c to set
	 */
	public void setC(float c) {
		this.c = c;
	}

	/**
	 * @return the d
	 */
	public float getD() {
		return d;
	}

	/**
	 * @param d
	 *            the d to set
	 */
	public void setD(float d) {
		this.d = d;
	}

	public void draw(GL gl) {
		gl.glBegin(GL.GL_LINE_LOOP);
		gl.glColor3f(0, 0, 0);
		//gl.glColor3f(x[0], y[0], z[0]);
		gl.glVertex3f(x[0], y[0], z[0]);
		//gl.glColor3f(x[1], y[1], z[1]);
		gl.glVertex3f(x[1], y[1], z[1]);
		//gl.glColor3f(x[2], y[2], z[2]);
		gl.glVertex3f(x[2], y[2], z[2]);
		//gl.glColor3f(x[0], y[0], z[0]);
		gl.glVertex3f(x[0], y[0], z[0]);
		gl.glEnd();
		gl.glFlush();
		
	}
	
	public float getMinX(){
		float minX=x[0];
		if(x[1]<minX){
			minX=x[1];
		}
		if(x[2]<minX){
			minX=x[2];
		}
		return minX;
	}
	
	public float getMaxX(){
		float maxX=x[0];
		if(x[1]>maxX){
			maxX=x[1];
		}
		if(x[2]>maxX){
			maxX=x[2];
		}
		return maxX;
	}
	
	
	public float getMinY(){
		float minY=y[0];
		if(y[1]<minY){
			minY=y[1];
		}
		if(y[2]<minY){
			minY=y[2];
		}
		return minY;
	}
	
	public float getMaxY(){
		float maxY=y[0];
		if(y[1]>maxY){
			maxY=y[1];
		}
		if(y[2]>maxY){
			maxY=y[2];
		}
		return maxY;
	}
	
	public float getMinZ(){
		float minZ=z[0];
		if(z[1]<minZ){
			minZ=z[1];
		}
		if(z[2]<minZ){
			minZ=z[2];
		}
		return minZ;
	}
	
	public float getMaxZ(){
		float maxZ=z[0];
		if(z[1]>maxZ){
			maxZ=z[1];
		}
		if(z[2]>maxZ){
			maxZ=z[2];
		}
		return maxZ;
	}
	
	public void changePoints(IMatrix m){

		for (int i=0;i<3;i++){
			float xi=(float)(this.x[i]*m.get(0, 0)+this.y[i]*m.get(1, 0)+this.z[i]*m.get(2, 0)+m.get(3, 0));
			float yi=(float)(this.x[i]*m.get(0, 1)+this.y[i]*m.get(1, 1)+this.z[i]*m.get(2, 1)+m.get(3, 1));
			float zi=(float)(this.x[i]*m.get(0, 2)+this.y[i]*m.get(1, 2)+this.z[i]*m.get(2, 2)+m.get(3, 2));
			double h=m.get(0, 3)+m.get(1, 3)+m.get(2, 3)+m.get(3, 3);
			this.x[i]=(float)(xi*(1.0/h));
			this.y[i]=(float)(yi*(1.0/h));
			this.z[i]=(float)(zi*(1.0/h));
		

		}
		
		
	}
}
