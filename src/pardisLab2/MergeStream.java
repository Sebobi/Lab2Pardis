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
		//Parallell stream sort
		ArrayList<Integer> list = new ArrayList<>(right-left-1);
		
		for(int i=left;i<=right;i++) {
			list.add(array[i]);
			//System.out.print(i + " ");
		}
		IntegerPointer i = new IntegerPointer();
		i.value = left;
		list.parallelStream().sorted()
				.forEachOrdered(e -> putAndIncrement(array,e,i));
				

		
	}
	
	private static void putAndIncrement(int array[],int e, IntegerPointer i) {
		array[i.value] = e;
		i.value++;
	}
	
	


}

class IntegerPointer {
	public int value;
}
