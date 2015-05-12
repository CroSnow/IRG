package hr.fer.zemris.irg.lab1.zad2.src.labos;
/**
 * Enums for different colors in its RGB values.
 * @author Ivan Hrastinski
 * @version 1
 */
public enum Colors {
	RED(1.0f,0.0f,0.0f,1.0f),
	GREEN(0.0f,1.0f,0.0f,1.0f),
	BLUE(0.0f,0.0f,1.0f,1.0f),
	YELLOW(1.0f,1.0f,0.0f,1.0f),
	MAGENTA(1.0f, 0.0f, 1.0f,1.0f), 
	CYAN(0.0f, 1.0f, 1.0f,1.0f),
	WHITE(1.0f, 1.0f, 1.0f,1.0f),
	BLACK(0.0f,0.0f,0.0f,1.0f);
	//Red value.
	private final float r;
	//Green value.
	private final float g;
	//Blue value.
	private final float b;
	//
	private final float a;
	
	private Colors(float r,float g, float b, float a){
		this.r=r;
		this.g=g;
		this.b=b;
		this.a=a;
	}

	/**
	 * @return the r
	 */
	public float getR() {
		return r;
	}

	/**
	 * @return the g
	 */
	public float getG() {
		return g;
	}

	/**
	 * @return the b
	 */
	public float getB() {
		return b;
	}

	/**
	 * @return the a
	 */
	public float getA() {
		return a;
	}
}
