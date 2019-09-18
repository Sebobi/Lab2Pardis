package pardisLab2;


import java.util.concurrent.RecursiveAction;

public class MergeSortRecursiveAction extends RecursiveAction {
	
	static final int LIMIT = 8000;
	
	int[] array;
	int left;
	int right;
	
	public MergeSortRecursiveAction(int[] array,int left, int right) {
		this.array = array;
		this.left = left;
		this.right = right;
	}
	@Override
	protected void compute() {
		// TODO Auto-generated method stub
		if(left < right) {
			if(right-left <= LIMIT) {

				MergeSequential.sort(array, left, right);	
			} else {

				int middle = (left+right)/2;
				
				MergeSortRecursiveAction leftMerge = new MergeSortRecursiveAction(array,left,middle);				
				MergeSortRecursiveAction rightMerge = new MergeSortRecursiveAction(array,middle+1,right);
				invokeAll(leftMerge,rightMerge);
				
				//wait here?
				
				MergeSequential.merge(array,left,middle,right);
				
			}	
		}
	}

}
