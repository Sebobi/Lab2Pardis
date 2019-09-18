package pardisLab2;

import java.util.Random;

public class RandomArray {

	public static int[] getRandomArray(int size) {
		int array[] = new int[size];
		Random random = new Random();
		
		for(int i =0;i<size;i++) {
			array[i] = random.nextInt();
		}
		
		
		
		return array;
	}
	
	public static int[] getRandomArrayRange(int size, int low, int high) {
		
		int array[] = new int[size];
		Random random = new Random();
		
		for(int i=0;i<size;i++) {
			array[i] = random.nextInt(high) + low;
		}
		
		return array;
	}
	
}
