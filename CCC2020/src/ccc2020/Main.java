package ccc2020;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println(getSpeed(scan));
	}
	public static String canEscape(Scanner scan) {
		int M = scan.nextInt();
		int N = scan.nextInt();
		int[][] values = new int[M][N];
		int[][] coords = new int[M][N];
		for (int i = 0; i <M; i++) {
			for (int k = 0; k < N; k++) {
				values[i][k] = scan.nextInt();
				coords[i][k] = k;
			}
		}
		
		for (int b = 0; b < M*N; b++) {
			
		}
		
		
		return "yes";
	}
	 
	  
	
	public static float getSpeed(Scanner scan) {
		
		int numObs = scan.nextInt();
		
		int[] time = new int[numObs+1];
		int[] distance = new int[100000000];
		float maxSpeed = 0;
		for (int i = 0; i < numObs; i++) {
			time[i] = scan.nextInt();
			distance[time[i]] = scan.nextInt();
		}
		Arrays.sort(time);
		for (int j = 1; j < numObs; j++) {
			
			for (int y = 1; y < numObs; y++) {
				float d = (float)(distance[time[j+1]]-distance[time[j]]);
				float t = (float)(time[j+1]-time[j]);
				float speed = (float)d/t;
				
				if (Math.abs(speed) >= Math.abs(maxSpeed)) {
					maxSpeed = speed;
				}
			}
				
			
			
		}
		return Math.abs(maxSpeed);
	}
	
}
