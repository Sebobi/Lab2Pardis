package pardisLab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class MergeStream {
	
	
	private static final int LIMIT = 50000;
	
	public static void sortStream2(int array[]) {
		sortStream2(array,0,array.length-1);
	}
	
	public static void sortStream2(int array[], int left, int right) {
		if(left < right) {
			
			if(right-left <= LIMIT) {
				//Stream solve
				streamSort(array,left,right);
				
			} else {

				int middle = (left+right)/2;
				
				sortStream2(array,left,middle);
				sortStream2(array,middle+1,right);
				
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
  public static void sort(int[] arr) {
    int[] array = arr;
    int n = array.length;
    for (int currSize = 1; currSize < 2 * n; currSize *= 2) {
      int[][] grouped = new int[(int)Math.ceil(n / (2.0 * currSize))][2 * currSize];
      for (int i = 0, left = 0; left < n; i++, left += 2 * currSize) {
        int right = Math.min(left + (2 * currSize) - 1, n - 1);

        System.arraycopy(array, left, grouped[i], 0, right - left + 1);

        // Special case for last array
        if (i == grouped.length - 1) {
          for (int j = right - left + 1; j < grouped[i].length; j++) {
            grouped[i][j] = Integer.MAX_VALUE;
          }
        }
      }
      array = Arrays.asList(grouped).parallelStream().flatMapToInt(group -> {
        MergeSequential.merge(group, 0, Math.max(0, group.length / 2 - 1), group.length - 1);
        return Arrays.stream(group);
      }).toArray();
    }
    System.arraycopy(array, 0, arr, 0, arr.length);
  }
}
