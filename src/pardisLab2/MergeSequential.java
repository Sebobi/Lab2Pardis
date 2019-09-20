package pardisLab2;

public class MergeSequential {
	


	
	
	
	public static void sort(int array[]) {
		sort(array,0,array.length-1);
	}
	
	public static void sort(int array[], int left, int right) {
		if(left < right) {
			int middle = (left+right)/2;
			
			sort(array,left,middle);
			sort(array,middle+1,right);
			
			merge(array,left,middle,right);
		}
		
	}
	
	

	public static void merge(int array[], int left, int middle, int right) {
		
		if (middle + 1 >= array.length || array[middle] <= array[middle + 1])
			return;
		
		
		int n1 = middle-left+1;
		int n2 = right-middle;
		
		
		int lArr[] = new int[n1];
		int rArr[] = new int[n2];
		
		for(int i =0;i <n1;i++) {
			lArr[i] = array[left+i];
		}
		for(int i=0;i<n2;i++) {
			rArr[i] = array[middle +1 + i];
		}
		
		
		int k = left;
		

		int i = 0;
		int j = 0;
		
		while(i < n1 &&  j< n2) {
			if(lArr[i] <= rArr[j]) {
				array[k] = lArr[i];
				i++;
			}
			else {
				array[k] = rArr[j];
				j++;
			}
			k++;
		}
		
		while(i < n1) {
			array[k] = lArr[i];
			i++;
			k++;
		}
		
		while(j < n2) {
			array[k] = rArr[j];
			j++;
			k++;
		}
		
		
		
		
	}


}
