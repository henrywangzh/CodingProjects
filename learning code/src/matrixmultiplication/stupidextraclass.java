package matrixmultiplication;

import java.util.Date;

public class stupidextraclass implements Runnable{
	private volatile int[][] matrix1;
	private volatile int[][] matrix2;
	private int[][] matrix3;
	private volatile int number;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public stupidextraclass(int[][] matrix1, int[][] matrix2, int x) {
		setMatrix1(matrix1);
		setMatrix2(matrix2);
		setNumber(x);
		setMatrix3(matrix1.length);
	}

	public void setMatrix3(int a) {
		this.matrix3 = new int[500][1000];
	}
	public void setMatrix1(int[][] matrix1) {
		this.matrix1 = matrix1.clone();
	}
	public void setMatrix2(int[][] matrix2) {
		this.matrix2 = matrix2.clone();
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public void run() {
		Date date = new Date();
//		int sum = 0;
//		for (int i = 0; i < 10000000; i++) {
//			sum += 1;
//		}
		
		int product = 0;
		int[][] tempmatrix = new int[500][1000];
		for (int i = (matrix1.length-matrix1.length/number);i < (matrix1.length-(matrix1.length/number)+(matrix1.length/2)); i++) {

			for (int j = 0; j < matrix2[i].length; j++) {
				product = 0;
				for (int k = 0; k < matrix2.length; k++) {
					product += matrix1[i][k] * matrix2[k][j];
					 //System.out.println(product);
				}
				tempmatrix[i][j] = product;
				// System.out.println(matrix3[i][j]);
			}
		}
		Date date2 = new Date();
		System.out.println("Thread Finished calculation " +(date2.getTime()-date.getTime()));
		
	}
	
	public int[][] getMatrix3() {
		return matrix3;
	}

}
