package pardisLab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class MergeStream {
	
	private static final int LIMIT = 50000;
	
	public static void sort(int[] array) {
		//Arrays.parallelSort(array);
		sort(array,0,array.length-1);
	}
	
	public static void sort(int array[], int left, int right) {
		if(left < right) {
			
			if(right-left <= LIMIT) {
				//Stream solve
				streamSort(array,left,right);
				
			} else {

				int middle = (left+right)/2;
				
				sort(array,left,middle);
				sort(array,middle+1,right);
				
				MergeSequential.merge(array,left,middle,right);
				
				
			}
			
		}
		
	}
	private static void streamSort(int array[], int left, int right) {

		int[] sorted = Arrays.stream(array, left, right+1).parallel().sorted().toArray();
		
		for(int i =0;i<sorted.length;i++) {
			array[left+i] = sorted[i];
		}
		
	}
}
