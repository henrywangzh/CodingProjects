import java.util.*;
import java.util.stream.IntStream;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Object;

public class CodeForcesProblems {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); 
	
		System.out.print(printfCopy("%s is a %s and will ace this %s try", "Henry", "genius", "first"));
	}
	
	public static void watermelon(Scanner s) {
		int x = s.nextInt();
		if(x % 2 == 0 && x!=2) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
		
	}
	
	public static void tooLong(Scanner s) {
		int a = s.nextInt();
		
		for(int i = 0; i < a; i++) {
			
			String word = s.next();
			int length = word.length();
			if(length > 10) {
				System.out.println(word.substring(0, 1) + String.valueOf(length-2) + word.substring(length-1, length));
			}
			else {
				System.out.println(word);
			}
			
		}
			
		}
		
	public static void Team(Scanner s) {
		int a = s.nextInt();
	
		int submit = 0;
		for(int i = 0; i<a; i++) {
			int counter = 0;
			for(int x = 0; x<3; x++) {
				counter += s.nextInt();
			}
			if(counter >= 2) {
				submit++;
			}
		}
		System.out.println(submit);
	}
	public static void nextRound(Scanner s) {
		int total = s.nextInt();
		int cutoff = s.nextInt();
		int pass = 0;
		int a[] = new int[total];

        for (int i = 0; i < total; i++) {
            a[i] = s.nextInt(); 
        }
        int minimum = a[cutoff-1];
       
        for (int j = 0; j < total; j++) {
            if(a[j] >= minimum && a[j]!=0) {
            	pass++;
            } else {
            	break;
            }
        	}
            System.out.println(pass);
        
        
	}
	public static void dominoPiling(Scanner s) {
		int m = s.nextInt();
		int n = s.nextInt();
		if(m % 2 == 0 || n % 2 == 0) {
			System.out.println(m*n/2);
		}
		else {
			System.out.println((int)Math.floor(m*n/2));
		}
		} 
	public static void Bit(Scanner s) {
		int x = s.nextInt();
		s.nextLine();
		int counter = 0;
		for(int i = 0; i<x; i++) {
			String statement = s.nextLine();
			if(statement.contains("++")) {
				counter++;
			} 
			else {
				counter--;
				
			}
		}
		System.out.println(counter);
	}
	public static void PetyaAndStrings(Scanner s) {
		String a = s.nextLine().toLowerCase();
		String b = s.nextLine().toLowerCase();
		
		int answer = a.compareTo(b);
		if(answer>0) {
			System.out.println(1);
		} 
		else if(answer<0) {
			System.out.println(-1);
		}
		else {
			System.out.println(0);
		}
	}
	public static void BeautifulMatrix(Scanner s) {
		String[] matrix = new String[5];
		int[] position = new int[2];
		for(int i = 0; i < 5; i++) {
			matrix[i] = s.nextLine();
			if(matrix[i].contains("1")) {
				
				position[0] = i+1;
		
				position[1] = (int) (Math.floor((matrix[i].indexOf("1")+2)/2));

			}
		}
		int distance = Math.abs(position[0]-3) + Math.abs(position[1]-3);
		System.out.println(distance);
	}
	
	public static void HelpfulMaths(Scanner s) {
		String sums = s.nextLine();
		String[] nums = sums.split("\\+");
		int[] ints = new int[nums.length];
		for(int i = 0; i < nums.length; i++) {
			ints[i] = Integer.parseInt(nums[i]);
		}
		Arrays.sort(ints);
		for(int a = 0; a < ints.length; a++) {
			if(a != ints.length-1) {
				System.out.print(ints[a]+ "+");
			}
			else {
				System.out.print(ints[a]);
			}
		}
		
	}
	
	public static void QueueAtSchool(Scanner s) {
		int line = s.nextInt();
		int time = s.nextInt();
		String queue = s.next();
		for(int i = 0; i < time; i++) {
			queue = queue.replaceAll("BG", "GB");
		}
		System.out.println(queue);
	}
	
	public static void Football(Scanner s) {
		String line = s.next();
		if (line.contains("0000000") || line.contains("1111111")) {
			System.out.println("YES");
		}
		else {
			System.out.println("NO");
		}
	}
	
	public static void Twins(Scanner s) {
		int num = s.nextInt();
		Integer[] coins = new Integer[num];
		int sum = 0;
		for(int i = 0; i < num; i++) {
			coins[i] = s.nextInt();
			sum+=coins[i];
		}
		int minimum = sum/2;
		int mine = 0;
		int total = 0;
		Arrays.sort(coins, Collections.reverseOrder());
		for(int a = 0; a < coins.length; a++) {
			if(mine<=minimum) {
				mine+=coins[a];
				total++;
			}
		}
		System.out.println(total);
	}
	public static void HQ9(Scanner s) {
		String program = s.next();
		if(program.contains("H") || program.contains("Q") || program.contains("9")) {
			System.out.println("YES");
			
		}
		else {
			System.out.println("NO");
		}
	}
	
	public static void KefaFirstSteps(Scanner s) {
		int count = s.nextInt();
		s.nextLine();
		String[] nums = s.nextLine().split(" ");
		int[] ints = new int[nums.length];
		for(int i = 0; i < ints.length; i++) {
			ints[i] = Integer.parseInt(nums[i]);
		}
		int counter = 1;
		int max = 1;
		for(int i = 0; i < ints.length-1; i++) {
			if(ints[i] < ints[i+1] || ints[i] == ints[i+1]) {
				counter++;
				if(counter > max) {
					max = counter;
				}
				
			}
			else {
				counter = 1;
			}
		}
			System.out.println(max);
	}
	public static void TheatreSquare(Scanner s) {
		double length = s.nextInt();
		double width = s.nextInt();
		double side = s.nextInt();
		double test =  Math.ceil(length/side);
		double test1 = Math.ceil(width/side);
		long total = (long) (test * test1);
		System.out.println(total);
	}
	
	public static void hashTest() {
		
		HashSet<String> cars = new HashSet<String>();
	    cars.add("Volvo");
	    cars.add("BMW");
	    cars.add("Ford");
	    cars.add("BMW");
	    
	    
	    HashMap<String, Integer> test = new HashMap<String, Integer>();
	    test.put("Henry", 18);
	}
	
	public static void CloningToys(Scanner s) {
		int x = s.nextInt();
		int y = s.nextInt();
		boolean haveCClone = false;
		int oCloneNum = y-1;
		if (oCloneNum != 0) {
			haveCClone = true;
		}
		int cCloneNum = oCloneNum;
		
		if((x-cCloneNum) % 2 == 0 && y!=0 && x >= cCloneNum) {
			if(haveCClone || x==0) {
				System.out.println("Yes");
			}
			else {
				System.out.println("No");
			}
			
		}
		else {
			System.out.println("No");
		}
	}
	public static void CavePainting(Scanner s) {
		long x = s.nextLong();
		long y = s.nextLong();
		for (int i = 1; i <= y; i++) {
			if(x % i != i-1) {
				System.out.println("No");
				break;
			}
			else if(i >= y-1) {
				System.out.println("Yes");
				break;
			}
		}
	}
	
	public static void Boredom(Scanner s) {
		long x = s.nextLong();
		int max = 100001;
		long[] c = new long[max];
		for (int i = 0; i < x; i++) {
			int a = s.nextInt();
			c[a]++;
		}
		long[] f = new long[max];
		f[0] = 0;
		f[1] = c[1];
		for(int i = 2; i< max; i++) {
			f[i] = Math.max(f[i-1], f[i-2]+i*c[i]);
		}
		System.out.println(f[max-1]);
	}
	
	public static void TwoSubStrings(Scanner s) {
		String str = s.next();
		int AB = str.indexOf("AB");
		int BA = str.indexOf("BA");
		if((AB != -1 && str.indexOf("BA", AB+2) != -1) || (BA != -1 && str.indexOf("AB", BA+2) != -1)) {
			System.out.println("YES");		
		}
		else {
			System.out.println("NO");
		}
	}
	public static void DivisibilityBy8(Scanner s) {
		String str = "00" + s.next();
		int a = str.length();
		boolean found = false;
		outerloop:
		for (int i = 0; i < a; i++) {
			for (int j = i+1; j < a; j++) {
				for (int k = j+1; k < a; k++) {
					int x = (str.charAt(i)- '0')*100 + (str.charAt(j)-'0')*10 + (str.charAt(k)-'0');
					if (x % 8 ==0) {
						System.out.println("YES");
						System.out.println(x);
						found = true;
						break outerloop;
					}
				}
			}
		}
		if(!found) {
			System.out.println("NO");
		}
	}
	
	public static void WoodCutters(Scanner s) {
		int total = s.nextInt();
		int[] x = new int[total];
		int[] h = new int[total];
		int min = 0;
		int count = 2;
		for (int i = 0; i < total; i++) {
			x[i] = s.nextInt();
			h[i] = s.nextInt();
		}
		min = x[0];
		if(total == 1) {
			System.out.println(1);
		}
		else {
			for (int j = 1; j<total-1; j++) {
				if (min < (x[j]-h[j])) {
					min = x[j];
					count++;
				}
				else if (x[j]+h[j] < x[j+1]) {
					min = x[j]+h[j];
					count++;
				}
				else {
					min = x[j];
				}
			}
			System.out.println(count);
			
		}
	}
	
	public static void DivisibilityBy8V2(Scanner s) {
		String str = s.next();
		for (int i = 0; i < 993; i+=8) {
			String iStr = Integer.toString(i);
			
			
			Pattern pattern = Pattern.compile(iStr.substring(0, iStr.length()-1).replaceAll(".", "$0.*").concat(iStr.substring(iStr.length()-1)));
			
			//System.out.println(iStr.substring(0, iStr.length()-1).replaceAll(".", "$0.*").concat(iStr.substring(iStr.length()-1)));
			Matcher matcher = pattern.matcher(str);
			boolean matchFound = matcher.find();
		
		    if(matchFound) {
		    	System.out.println("YES");
		    	System.out.println(i);
		    	break;
		    }
		    if(i>991) {
		    	System.out.println("NO");
		    }
		}
	}
	
	public static void BerlandCrossword(Scanner s) {
		int num = s.nextInt();
		
		for(int i = 0; i< num; i++) {
			int n = s.nextInt();
			int top = s.nextInt();
			int right = s.nextInt();
			int bottom = s.nextInt();
			int left = s.nextInt();
			boolean possible = false;
			for(int k = 0; k<(1<<4); k++) {
				int t = top;
				int r = right;
				int b = bottom;
				int l = left;
				if((k&1)!=0) {
					t--;
					r--;
				}
				if((k&2)!=0) {
					r--;
					b--;
				}
				if((k&4)!=0) {
					b--;
					l--;
				}
				if((k&8)!=0) {
					l--;
					t--;
				}
				if(t>=0 && t<=(n-2) && r>=0 && r<=(n-2) && b>=0 && b<=(n-2) && l>=0 && l<=(n-2)) {
					System.out.println("YES");
					possible = true;
					break;
				}
			}
			if(!possible) {
				System.out.println("NO");
			}
		
		}
	}
	
	public static void test() {
		for(int k = 0; k<3;k++) {
			
			System.out.println("First loop");
			long start = System.currentTimeMillis();  
			for(long i = 0; i<2147483647;i++) { 
				long a = 123456;
				a*=16;
			}
			
			long end = System.currentTimeMillis();
			System.out.println(end-start);
			
			
			System.out.println("Second loop");
			start = System.currentTimeMillis();
			
			for(long i = 0; i<2147483647;i++) {
				long a = 123456;
				a=a<<4;
			}
			end = System.currentTimeMillis();
			System.out.println(end-start);
			
		}
		
	}
	public static String printfCopy(String text, String ... s) {
		int i = 0;
		while(text.contains("%s")) {
			text = text.replaceFirst("%s", s[i]);
			i++;
		}
		return text;
	}
}


