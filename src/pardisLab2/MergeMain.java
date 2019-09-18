package pardisLab2;

import java.util.concurrent.ForkJoinPool;

public class MergeMain {
	
	
	public static void main(String[] args) {
		

		int size = 10000000;
		
		int array[] = RandomArray.getRandomArrayRange(size, 0, 5000);
		

		
		MergeSequential.sortWithTimer(array);
		
		//printArray(array);

		
		
		array = RandomArray.getRandomArrayRange(size, 0, 5000);
		
		
		int numberOfProcessors = Runtime.getRuntime().availableProcessors();
	    ForkJoinPool pool = new ForkJoinPool(numberOfProcessors-1);

	    System.out.println("Attempting fork solve");
	    
	    long time = System.currentTimeMillis();
	    MergeSortRecursiveAction recursive = new MergeSortRecursiveAction(array,0,array.length-1);
	    
	    pool.execute(recursive);
	    //Can use pool.invoke if we dont want to check for queued tasks during execution.
	    //pool.invoke(recursive);
	    
	    long mostTasks = 0;
	    while(!recursive.isDone()) {
	    	long taskTemp = pool.getQueuedTaskCount();
	    
	    	if(taskTemp > mostTasks)
	    		mostTasks = taskTemp;
	    }
		
	    System.out.println("Sorting took: " + (System.currentTimeMillis() - time) + " ms");
	    System.out.println("Done");
	    System.out.println("Most tasks queued: " + mostTasks);
	   
	    //printArray(array);
		
		
	}
	

	
	
	
	
	
	
	public static void printArray(int[] array) {

		System.out.println("Array:");
		for(int i =0;i<array.length;i++) {
			System.out.print(array[i] + " ");
			
		}
		System.out.println();
	}
	
	

}
