import java.util.*;
import java.util.stream.IntStream;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Object;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ModernArt(scan);

	}

	public static void CrazyFencing(Scanner s) {
		int n = s.nextInt();
		double[] h = new double[n+1];
		double[] w = new double[n];
		double area = 0;
		for(int i = 0; i<n+1; i++) {
			h[i] = s.nextDouble();
		
		}
		s.nextLine();
		for(int j = 0; j < n; j++) {
			w[j] = s.nextDouble();
	
		}
		for(int k = 0; k<n; k++) {
			double avgheight = (h[k]+h[k+1])/2;
			area+=avgheight*w[k];
		}
	System.out.println(area);
	}
	
	public static void ModernArt(Scanner s) {
		int m = s.nextInt();
		int n = s.nextInt();
		int k = s.nextInt();
		int[] rowNum = new int[m];
		int[] columnNum = new int[n];
		ArrayList<Integer> prevColumn = new ArrayList<Integer>();
		ArrayList<Integer> prevRow = new ArrayList<Integer>();
		int[] rowCalled = new int[m];
		int[] columnCalled = new int[n];
		
		int gold = 0;
		for(int i = 0; i < k; i++) {
			String type = s.next();
			int num = s.nextInt();
			if(type.equals("R")) {
				if(prevRow.contains(num)) {
					rowNum[num-1]=n-rowNum[num-1];
					
				}
				else {
					rowNum[num-1]=n-prevColumn.size();
					prevRow.add(num);
					
				}
				rowCalled[num-1]++;
				for (int x = 0; x < n; x++) {				
					
						if((columnCalled[x]+rowCalled[num-1])%2==0) {
							columnNum[x]--;
							
						}
						else {
							columnNum[x]++;
						}
					
						
			}
				
			}
			else {
				if(prevColumn.contains(num)) {
					columnNum[num-1]=m-columnNum[num-1];
					prevColumn.add(num);
					
				}
				else {
					columnNum[num-1]=m-prevRow.size();
					prevColumn.add(num);
					
				}
				columnCalled[num-1]++;	
				for (int x = 0; x < m; x++) {
					
					if((rowCalled[x]+columnCalled[num-1])%2==0) {
						rowNum[x]--;
						
					}
					else {
						rowNum[x]++;
					}
					
					
		}
				
				
			}
		}
		gold=IntStream.of(rowNum).sum();
		System.out.println(gold);
	}
}
