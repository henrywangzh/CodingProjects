package matrixmultiplication;

import java.util.Date;

public class matrixMultiplication {

	public static void main(String[] args) throws InterruptedException	{
		// TODO Auto-generated method stub
		
		int size = 200;
		int[][] matrix1 = matrixgenerator(size);
		int[][] matrix2 = matrixgenerator(size);
		int i = 10;
		long l = 10;
		double d = 10d;
		float f = 10.0f;
		short s = 10;
		//int sumtest = intSum();
		//int[][] test = matrixmultiplication(matrix1, matrix2, 1);
		 stupidextraclass firsthalf = new stupidextraclass(matrix1, matrix2, 1);
		stupidextraclass secondhalf = new stupidextraclass(matrix1, matrix2, 2);
		 Thread thread1 = new Thread(firsthalf);
		Thread thread2 = new Thread(secondhalf);
		 thread1.start();
		thread1.join();
		thread2.start();
		thread2.join();
		int[][] firstvalue = firsthalf.getMatrix3();
		int[][] secondvalue = secondhalf.getMatrix3();
		 matrixaddition(size, firstvalue, secondvalue);
//		Thread thread1 = new Thread() {
//			public void run() {
//				int[][] firsthalf = matrixmultiplication(matrix1, matrix2, 1);
//				for (int i = 0; i < size/2; i++) {
//					for (int j = 0; j < size; j++) {
//						System.out.println("first " + firsthalf[i][j]);
//					}
//				}
//			}
//		};
//		thread1.start();
//
//		Thread thread2 = new Thread() {
//			public void run() {
//				int[][] secondhalf = matrixmultiplication(matrix1, matrix2, 2);
//				for (int i = size/2; i < size; i++) {
//					for (int j = 0; j < size; j++) {
//						System.out.println("second " + secondhalf[i][j]);
//					}
//				}
//			}
//		};
//		thread2.start();
		
	
		//matrixaddition(size, firsthalf, secondhalf);
	}
	public static int intSum() {
		Date date = new Date();
		int sum = 0;
		for (int i = 0; i < 10000000; i++) {
			sum += 1;
		}
		Date date2 = new Date();
		System.out.println("Thread Finished calculation " +(date2.getTime()-date.getTime()));
		return sum;
	}
	public static int[][] matrixaddition(int size, int[][] firsthalf, int[][] secondhalf) {
		Date date = new Date();
		int[][] finalmatrix = new int[size][size];
		for (int i = 0; i < size/2; i++) {
			for (int j = 0; j< size; j++) {
				finalmatrix[i][j] = firsthalf[i][j];
				//System.out.println(finalmatrix[i][j]);
			}
		}
		for (int i = size/2; i < size; i++) {
			for (int j = 0; j< size; j++) {
				finalmatrix[i][j] = secondhalf[i][j];
				//System.out.println(finalmatrix[i][j]);
			}
			
		}
		Date date2 = new Date();
		System.out.println("plugging in time: " + (date2.getTime() - date.getTime()));
		return finalmatrix;
	}
	public static int[][] matrixgenerator(int size) {
		int[][] matrix = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				matrix[i][j] = (int) (Math.floor(Math.random() * 100) + 1);
				// System.out.println(matrix[i][j]);
			}

		}
		System.out.println(matrix[0][0]);
		// System.out.println();
		return matrix;
	}

	public static int[][] matrixmultiplication(int[][] matrix1, int[][] matrix2, int x) {
		Date date = new Date();
		int product = 0;
		//System.out.println(matrix1.length + " " + date.getTime());
		//int[][] matrix3 = new int[matrix1.length][matrix1.length];
		int[][] matrix3 = new int[500][1000];
		for (int i = (matrix1.length-matrix1.length/x);i < (matrix1.length-(matrix1.length/x)+(matrix1.length/2)); i++) {

			for (int j = 0; j < matrix2[i].length; j++) {
				product = 0;
				for (int k = 0; k < matrix2.length; k++) {
					product += matrix1[i][k] * matrix2[k][j];
				}
				matrix3[i][j] = product;
				// System.out.println(matrix3[i][j]);
			}
		}
		Date date2 = new Date();
		//System.out.println(matrix1.length + " " + date2.getTime());
		System.out.println("Main Finished calculation " + (date2.getTime()-date.getTime()));
		
		return matrix3;
	}
//	public static int[][] matrixmultiplication1(int[][] matrix1, int[][] matrix2) {
//		Date date = new Date();
//		System.out.println(matrix1.length + " " + date.getTime());
//		int[][] matrix3 = new int[matrix1.length][matrix1.length];
//		for (int i = 0;i < matrix1.length/2; i++) {
//
//			for (int j = 0; j < matrix2[i].length; j++) {
//				int product = 0;
//				for (int k = 0; k < matrix2.length; k++) {
//					product += matrix1[i][k] * matrix2[k][j];
//				}
//				matrix3[i][j] = product;
//				// System.out.println(matrix3[i][j]);
//			}
//		}
//		Date date2 = new Date();
//		System.out.println(matrix1.length + " " + date2.getTime());
//		System.out.println(date2.getTime()-date.getTime());
//		System.out.println("Full size Finished calculation");
//		return matrix3;
//	}
}
