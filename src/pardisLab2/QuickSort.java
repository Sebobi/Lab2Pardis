package pardisLab2;

import java.util.Arrays;
import java.util.Random;


public class QuickSort {
	
	private static int LIMIT = 8000;
	
	
    public static int[] sort(int arr[]) 
    {  
    		if(arr.length <= LIMIT) {
    			MergeSequential.sort(arr);
    			return arr;
    		}
            /* pi is partitioning index, arr[pi] is  
              now at right place */
    		Random random = new Random();
    		
			int place = random.nextInt(arr.length);	
        	int pivot = arr[place];
        	
        	
        	int[] array1 = Arrays.stream(arr).parallel().filter(e -> e<pivot).toArray();
        	int[] array2 = Arrays.stream(arr).parallel().filter(e -> e == pivot).toArray();
        	int[] array3 = Arrays.stream(arr).parallel().filter(e -> e > pivot).toArray();
        	
        	
            array1 = sort(array1);
            array3 = sort(array3);
            
            for(int i =0;i<array1.length;i++) {
            	arr[i] = array1[i];
            }
            
            for(int i =0;i<array2.length;i++) {
            	arr[array1.length + i] = array2[i];
            }
            
            for(int i =0;i<array3.length;i++) {
            	arr[array1.length + array2.length + i] = array3[i];
            	
            }
            
            return arr;
            
        
    } 

}
