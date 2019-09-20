package pardisLab2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;

public class MergeSortExecutorService {

  private static ExecutorService exec = Executors.newCachedThreadPool();//newFixedThreadPool(Runtime.getRuntime().availableProcessors());

  public int[] sort(int[] arr) throws InterruptedException, ExecutionException {
    int[] sortedArr = new SortTask(arr, 0, arr.length, Runtime.getRuntime().availableProcessors()-1).call();
    exec.shutdown();
    return sortedArr;
  }

  private class SortTask implements Callable<int[]> {
    int[] arr;
    int a, b, threadLimit;

    public SortTask(int[] arr, int a, int b, int threadLimit) {
      this.arr = arr; this.a = a; this.b = b; this.threadLimit = threadLimit;
    }

    public int[] call() throws InterruptedException, ExecutionException {
      if (a >= b - 1) {
        return new int[]{arr[a]};
      }
      int half = (a + b) / 2;
      if (threadLimit <= 0) {
        return merge(new SortTask(arr, a, half, threadLimit).call(), new SortTask(arr, half, b, threadLimit).call());
      }
      Future<int[]> firstHalfSorted = exec.submit(new SortTask(arr, a, half, threadLimit/2));
      Future<int[]> secondHalfSorted = exec.submit(new SortTask(arr, half, b, threadLimit/2));
      return merge(firstHalfSorted.get(), secondHalfSorted.get());
    }

    private int[] merge(int[] f, int[] s) {
      int[] res = new int[f.length + s.length];
      int fi = 0;
      int si = 0;
      int i = 0;
      while (fi < f.length || si < s.length) {
        if (si == s.length || (fi < f.length && f[fi] < s[si])) {
          res[i++] = f[fi++];
        } else {
          res[i++] = s[si++];
        }
      }
      return res;
    }
  }
}
