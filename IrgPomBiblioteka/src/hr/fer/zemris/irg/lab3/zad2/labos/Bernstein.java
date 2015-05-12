package hr.fer.zemris.irg.lab3.zad2.labos;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

import javax.media.opengl.GL;

import hr.fer.zemris.irg.lab3.zad1.labos.Point;
public class Bernstein {
	private LinkedList<Point> points;
	private int n;
	public Bernstein(){
		this.points=new LinkedList<Point>();
	}
	
	
	public void addPoint(Point p){
		this.points.add(p);
		this.n=points.size()-1;
	}
	public void draw(GL gl){
		gl.glColor3f(0, 1, 0);
		gl.glBegin(GL.GL_LINE_STRIP);
		Point p;
		for(float t=0;t<=1;t+=0.01){
			p=calculateP(t);
			gl.glVertex3f(p.getX(), p.getY(), p.getZ());
			
		}
		gl.glEnd();
		gl.glFlush();
	}
	private Point calculateP(float t) {
		float x=0;
		float y=0;
		float z=0;
		for(int i=0;i<=n;i++){
			x+=((float)factorial(n)/(float)(factorial(i)*factorial(n-i)))*Math.pow(t, i)*Math.pow(1-t, n-i)*points.get(i).getX();
			y+=((float)factorial(n)/(float)(factorial(i)*factorial(n-i)))*Math.pow(t, i)*Math.pow(1-t, n-i)*points.get(i).getY();
			z+=((float)factorial(n)/(float)(factorial(i)*factorial(n-i)))*Math.pow(t, i)*Math.pow(1-t, n-i)*points.get(i).getZ();

		}
		Point p=new Point(x, y, z);
		return p;
	}


	public void drawControl(GL gl){
		gl.glColor3f(1, 0, 0);
		gl.glBegin(GL.GL_LINE_LOOP);
		for(Point p: points){
			gl.glVertex3f(p.getX(), p.getY(), p.getZ());
		}
		gl.glEnd();
		gl.glFlush();
	}
	
	private int factorial(int n){
		int ret=n;
		for(int i=n-1;i>1;i--){
			ret*=i;
		}
		if (ret==0){
			ret=1;
		}
		return ret;
	}
	
	public static Bernstein parseFromFile(BufferedReader reader){
		String line="";
		Bernstein bern=new Bernstein();
		try {
			line=reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (line!=null){
			line=line.trim();
			float x=Float.parseFloat(line.split(",")[0]);
			float y=Float.parseFloat(line.split(",")[1]);
			float z=Float.parseFloat(line.split(",")[2]);
			Point p=new Point(x, y, z);
			bern.addPoint(p);
			try {
				line=reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bern;
	}
}
