package pardisLab2;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ExecutionException;

public class MergeMain {
	
	
	public static void main(String[] args) {
		

		System.out.println("Available processors: " + Runtime.getRuntime().availableProcessors());
		
		
		int size = 10000000;
		
		int array[] = RandomArray.getRandomArrayRange(size, 0, 5000);
		
		System.out.println("Attempting sequential sort");
		

	    long time = System.currentTimeMillis();
		
		MergeSequential.sort(array);
		
		System.out.println("Sorting took: " + (System.currentTimeMillis()-time) + " ms");

	    System.out.println("Sort done. Is Sorted: " + isSorted(array));
		
		//printArray(array);

		
		
		array = RandomArray.getRandomArrayRange(size, 0, 5000);
		
		
		int numberOfProcessors = Runtime.getRuntime().availableProcessors();
	    ForkJoinPool pool = new ForkJoinPool(numberOfProcessors-1);

	    System.out.println("Attempting fork solve");
	    
	    time = System.currentTimeMillis();
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
	    System.out.println("Sort done. Is Sorted: " + isSorted(array));
	    System.out.println("Most tasks queued: " + mostTasks);
	   
	    //printArray(array);
		
	    
	    System.out.println("Starting ParallelStream sorter");
	    
	    array = RandomArray.getRandomArrayRange(size, 0, 5000);
	    
	    
	    
	    System.out.println("Attempting parallel stream sort");
	    time = System.currentTimeMillis();
	    MergeStream.sort(array);
	    System.out.println("Sorting took: "+ (System.currentTimeMillis() - time) + " ms");
	    System.out.println("Sort done. Is sorted: " + isSorted(array));
	    
	    
	    //printArray(array);
	    

	    array = RandomArray.getRandomArrayRange(size, 0, 5000);
	    
	    System.out.println("Attempting executor service sort");
      try {
        time = System.currentTimeMillis();
        int[] sorted = new MergeSortExecutorService().sort(array);
        System.out.println("Sorting took: "+ (System.currentTimeMillis() - time) + " ms");
        System.out.println("Sort done. Is sorted: " + isSorted(sorted));
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
		
      System.out.println("Attempting ParallellStream 2");
      array = RandomArray.getRandomArrayRange(size,0,5000);
      
      time = System.currentTimeMillis();
      MergeStream.sortStream2(array, 0, array.length-1);
      
      System.out.println("Sorting took: " + (System.currentTimeMillis() - time) + " ms");
      System.out.println("Is sorted: " + isSorted(array));
      
      System.out.println("Attempting Parallellstream quicksort");
      
      array = RandomArray.getRandomArrayRange(size,0,5000);
      time = System.currentTimeMillis();
      array = QuickSort.sort(array);
      
      System.out.println("Sorting took: " + (System.currentTimeMillis() - time) + " ms");
      System.out.println("Is sorted: " + isSorted(array));
      
      
	}
	

	
	public static boolean isSorted(int[] array) {
		
		for(int i=0;i<array.length-1;i++) {
			if(array[i] > array[i+1]) {
				return false;
			}
		}
		return true;
		
	}
	
	
	
	
	public static void printArray(int[] array) {

		System.out.println("Array:");
		for(int i =0;i<array.length;i++) {
			System.out.print(array[i] + " ");
			
		}
		System.out.println();
	}
	
	

}
