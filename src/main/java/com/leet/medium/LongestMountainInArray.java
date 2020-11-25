package medium;

import java.util.Arrays;
import java.util.stream.*;

class LongestMountainInArraySolution {
  public int longestMountain(int[] A) {
    int beginIndex = 0, endIndex = A.length-1;
    while (beginIndex < A.length-1 && A[beginIndex] >= A[beginIndex+1]) {
      beginIndex++;
    }

    if (beginIndex >= A.length -1) {
      return 0;
    }

    while (endIndex > 0 && A[endIndex] >= A[endIndex-1]) {
      endIndex--;
      if (endIndex <= beginIndex) {
        return 0;
      }
    }

    if (endIndex == 0 || endIndex <= beginIndex) {
      return 0;
    }

    int prevSink = beginIndex, length = 0, i;
    for (i = beginIndex; i <= endIndex; i++) {
      if (i > beginIndex && A[i] == A[i-1]) {
        prevSink = -1;
        continue;
      }
      // find next sink
      if (i > beginIndex && i < endIndex && A[i-1] > A[i] && A[i] < A[i+1]) {
        System.out.println("Sink:" + i);
        if (prevSink != -1) {
          length = Math.max(length, i - prevSink + 1);
          prevSink = i;
        } else {
          prevSink = i;
        }
      }
    }
    if (prevSink != -1 && prevSink != endIndex) {
      length = Math.max(length, endIndex - prevSink + 1);
    }
    return length;
  }
}

class LongestMountainInArraySolution2 {
  int UP = 0;
  int DOWN = 1;
  public int longestMountain(int[] A) {
    int start = 0, end = 0, direction = UP, length = 0;
    for (int i = 1; i < A.length; i++) {
      if (A[i] == A[i-1]) {
        start = end = i;
        direction = UP;
        continue;
      }
      if (direction == UP) {
        if (A[i] > A[i-1]) {
          end++;
        } else {
          if (start != end) {
            direction = DOWN;
            end++;
          } else {
            start = end = i;
          }
        }
      } else {
        if (A[i] < A[i-1]) {
          end++;
          length = Math.max(length, end - start + 1);
        } else {
          System.out.println("start=" + start + ";end=" + end);
          length = Math.max(end - start + 1, length);
          end = i;
          start = i-1;
        }
      }
    }
    if (direction == DOWN) {
      System.out.println("start=" + start + ";end=" + end);
      // System.out.println(length + " | " + start + " -> " + end + " | direction = " + (direction == UP ? "UP" : "DOWN"));
      length = Math.max(length, end - start + 1);
    }
    return length;
  }
}

public class LongestMountainInArray {
  public static void main(String[] args) {
    String inp = "2,1,4,7,3,2,5";
    System.out.println(inp);
    int[] arr = new int[7];
    String[] input = inp.split(",");
    for (int i = 0; i < input.length; i++) {
      arr[i] = Integer.parseInt(input[i]);
    }
    System.out.println(new LongestMountainInArraySolution2().longestMountain(arr));
  }
}
