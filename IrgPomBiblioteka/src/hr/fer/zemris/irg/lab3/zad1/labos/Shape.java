package hr.fer.zemris.irg.lab3.zad1.labos;

import hr.fer.zemris.linearna.IMatrix;

import java.util.ArrayList;

import javax.media.opengl.GL;

/**
 * A class which saves a shape in form of triangles.
 * 
 * @author Ivan Hrastinski
 * @version 1
 */
public class Shape {
	// The list of triangles which form this shape.
	private ArrayList<Triangle> partList = new ArrayList<Triangle>();
	private float minX;
	private float maxX;
	private float minY;
	private float maxY;
	private float minZ;
	private float maxZ;

	/**
	 * Forms a shape from triangles.
	 * 
	 * @param triangles
	 */
	public Shape(Triangle... triangles) {
		for (Triangle t : triangles) {
			partList.add(t);
		}
	}

	/**
	 * Empty constructor
	 */
	public Shape() {

	}

	/**
	 * Add a triangle shape to this shape.
	 * 
	 * @param t
	 */
	public void addTriangle(Triangle t) {
		partList.add(t);
	}

	/**
	 * Checks if the given point is inside or outside the shape.
	 * 
	 * @param p
	 *            the point we check for.
	 * @return true if its inside , false else.
	 */
	public boolean checkPoint(Point p) {
		for (Triangle t : partList) {
			if (t.position(p) > 0) {
				return false;
			}
		}
		return true;
	}

	public void draw(GL gl) {

		for (Triangle t : partList) {
			t.draw(gl);
		}

	}
	public void calculateMinMax(){
		minX=partList.get(0).getMinX();
		maxX=partList.get(0).getMaxX();
		minY=partList.get(0).getMinY();
		maxY=partList.get(0).getMaxY();
		minZ=partList.get(0).getMinZ();
		maxZ=partList.get(0).getMaxZ();
		for(Triangle t:partList){
			if(minX>t.getMinX()){
				minX=t.getMinX();
			}
			if(maxX<t.getMaxX()){
				maxX=t.getMaxX();
			}
			
			if(minY>t.getMinY()){
				minY=t.getMinY();
			}
			if(maxY<t.getMaxY()){
				maxY=t.getMaxY();
			}
			
			if(minZ>t.getMinZ()){
				minZ=t.getMinZ();
			}
			if(maxZ<t.getMaxZ()){
				maxZ=t.getMaxZ();
			}
		}
		
	}

	/**
	 * @return the minX
	 */
	public float getMinX() {
		return minX;
	}

	/**
	 * @return the maxX
	 */
	public float getMaxX() {
		return maxX;
	}

	/**
	 * @return the minY
	 */
	public float getMinY() {
		return minY;
	}

	/**
	 * @return the maxY
	 */
	public float getMaxY() {
		return maxY;
	}

	/**
	 * @return the minZ
	 */
	public float getMinZ() {
		return minZ;
	}

	/**
	 * @return the maxZ
	 */
	public float getMaxZ() {
		return maxZ;
	}
	
	public void changePoints(IMatrix m){
		for(Triangle t:partList){
			t.changePoints(m);
		}
	}
}
