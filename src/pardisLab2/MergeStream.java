package pardisLab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class MergeStream {
	
  public static void sort(int[] arr) {
    int[] array = arr;
    int n = array.length;
    for (int currSize = 1; currSize < 2 * n; currSize *= 2) {
      int[][] grouped = new int[(int)Math.ceil(n / (2.0 * currSize))][2 * currSize];
      for (int i = 0, left = 0; left < n; i++, left += 2 * currSize) {
        int middle = Math.min(left + currSize - 1, n - 1);
        int right = Math.min(left + (2 * currSize) - 1, n - 1);

        grouped[i] = Arrays.copyOfRange(array, left, right + 1);

        // Special case for last array
        if (i == grouped.length - 1) {
          for (int j = right - left + 1; j < grouped[i].length; j++) {
            grouped[i][j] = Integer.MAX_VALUE;
          }
        }
      }
      array = Arrays.copyOfRange(Arrays.asList(grouped).parallelStream().flatMapToInt(group -> {
        MergeSequential.merge(group, 0, Math.max(0, group.length / 2 - 1), group.length - 1);
        return Arrays.stream(group);
      }).toArray(), 0, arr.length);
    }
    System.arraycopy(array, 0, arr, 0, arr.length);
  }
}
