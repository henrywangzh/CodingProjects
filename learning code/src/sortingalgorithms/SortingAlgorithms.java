package sortingalgorithms;

public class SortingAlgorithms {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = {23,34,3,6,9};
		int[] results = mergeSort(test);
		for(int i = 0; i < results.length; i++) {
			System.out.println(results[i]);
		}
	}
	public static int[] merge(int[] left, int[] right, int[] a) {
		
		int ll = left.length;
		int lr = right.length;
		int i = 0, j = 0, k = 0;
		while(i < ll && j < lr) {
			if (left[i] < right[j]) {
				a[k] = left[i];
				i++;
			}
			else {
				a[k] = right[j];
				j++;
			}
			k++;
		}
		while(i< ll) {
			a[k] = left[i];
			i++;
			k++;
		}
		while(j< lr) {
			a[k] = right[j];
			j++;
			k++;
		}
		return a;
	}
	public static int[] mergeSort(int[] a) {
		if (a.length < 2) {return a;}
		int mid = a.length/2;
		int[] left = new int[mid];
		int[] right = new int[a.length-mid];
		for (int x = 0; x<mid; x++) {
			left[x] = a[x];
		}
		for (int x = mid; x<a.length; x++) {
			right[x-mid] = a[x];
		}
		mergeSort(left);
		mergeSort(right);
		merge(left, right, a);
		
		return a;
	}

}
