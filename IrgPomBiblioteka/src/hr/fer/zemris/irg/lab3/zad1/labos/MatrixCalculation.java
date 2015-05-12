package hr.fer.zemris.irg.lab3.zad1.labos;

import hr.fer.zemris.linearna.*;

public class MatrixCalculation {
	public static IMatrix calculateMatrix(Point O,Point G){
		IMatrix t1=T1(O, G);
		IMatrix t2=T2(O, G);
		IMatrix t3=T3(O, G);
		IMatrix t4=T4(O, G);
		IMatrix t5=T5(O, G);
		IMatrix m=null;
		try {
			m=t1.nMultiply(t2).nMultiply(t3).nMultiply(t4).nMultiply(t5);
		} catch (IncompatibleOperandException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}
	
	private static IMatrix T1(Point O, Point G){
		double[][] args=new double[4][4];
		args=basic();
		args[3][0]=-O.getX();
		args[3][1]=-O.getY();
		args[3][2]=-O.getZ();
		IMatrix m=new Matrix(4,4,args,false);
		return m;
	}
	
	private static IMatrix T2(Point O, Point G){
		Point G1=new Point(G.getX()-O.getX(),G.getY()-O.getY(),G.getZ()-O.getZ());
		double cosA=G1.getX()/( Math.sqrt(G1.getX()*G1.getX()+G1.getY()*G1.getY()));
		double sinA=G1.getY()/( Math.sqrt(G1.getX()*G1.getX()+G1.getY()*G1.getY()));
		double[][]args=basic();
		args[0][0]=cosA;
		args[0][1]=-sinA;
		args[1][0]=sinA;
		args[1][1]=cosA;
		IMatrix m=new Matrix(4, 4, args, false);
		
		
		return m;
	}
	
	private static IMatrix T3(Point O, Point G){
		Point G1=new Point(G.getX()-O.getX(),G.getY()-O.getY(),G.getZ()-O.getZ());
		double x=Math.sqrt(G1.getX()*G1.getX()+G1.getY()*G1.getY());
		Point G2=new Point((float)x,0,G1.getZ());
		double sinB=G2.getX()/Math.sqrt(G2.getX()*G2.getX()+G2.getZ()*G2.getZ());
		double cosB=G2.getZ()/Math.sqrt(G2.getX()*G2.getX()+G2.getZ()*G2.getZ());
		double[][] args=basic();
		args[0][0]=cosB;
		args[0][2]=sinB;
		args[2][0]=-sinB;
		args[2][2]=cosB;
		IMatrix m=new Matrix(4, 4, args, false);
		return m;
	}
	
	private static IMatrix T4(Point O, Point G){
		double[][] args=new double[4][4];

		args=basic();

		args[0][0]=0;
		args[0][1]=-1;
		args[1][0]=1;
		args[1][1]=0;
		IMatrix m=new Matrix(4,4,args,false);
		return m;
	}
	
	private static IMatrix T5(Point O, Point G){
		double[][] args=new double[4][4];
		args=basic();
		args[0][0]=-1;

		IMatrix m=new Matrix(4,4,args,false);
		return m;
	}
	
	
	private static double[][] basic(){
		double[][] b=new double[4][4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(i==j){
					b[i][j]=1;
				}else{
					b[i][j]=0;
				}
			}
		}
		return b;
	}
	
	private static double calculateH(Point O,Point G){
		return Math.sqrt(Math.pow(O.getX()-G.getX(), 2)+Math.pow(O.getY()-G.getY(), 2)+Math.pow(O.getZ()-G.getZ(), 2));
		
	}
	
	public static IMatrix calculateHMatrix(Point O, Point G){
		double[][] args=basic();
		double H=calculateH(O, G);
		args[2][2]=0;
		args[2][3]=1.0/H;
		args[3][3]=0;
		System.out.println("h="+H);
		System.out.println(new Matrix(4, 4,args,false));
		return new Matrix(4, 4,args,false);
		
	}
	
}
