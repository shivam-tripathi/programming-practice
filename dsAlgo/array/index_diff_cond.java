// Given an array arr[], find the maximum j – i such that arr[j] > arr[i]
import java.util.*;

class Solution {
    /**
     * If for m and n such that m < n and arr[m] < arr[n], then for all cases we'll pick m over n for i
     * If for m and n such that m > n and arr[m] > arr[n] then for all cases we'll pick m over n for j
     *
     * Idea is to store number from left in decreasing order and from right in increasing order (these
     * are the one's of interest). The i will belong to left decreasing and j to right increasing.
     *
     * Imagine two bands starting at 0: one for i and other for j. For a given i, we'll try to stretch
     * the j band as far as possible. If the j still can be stretched (j < maxSize) and could not because
     * it is no longer that arr[j] > arr[i] - we will stretch i till we find arr[i] small enough again
     * so that we can stretch the j band. This continues till one of i or j runs out of bounds.
     *
     * We can easily verify that if m-1 could be stretched till n-1, and fails at n: m could be automatically
     * stretched till n-1. We need to find m+k which will satisfy n now.
     */
    public int maximumGap(final List<Integer> A) {
        int[] lMin = new int[A.size()];
        int[] rMax = new int[A.size()];
        int index, prev;

        ListIterator<Integer> iter = A.listIterator();
        lMin[0] = iter.next();
        for (int i = 1; i < lMin.length; i++) {
            int item = iter.next();
            lMin[i] = item < lMin[i-1] ? item : lMin[i-1];
        }

        rMax[rMax.length - 1] = iter.previous();
        for (int i = rMax.length - 2; i >= 0; i--) {
            int item = iter.previous();
            rMax[i] = rMax[i + 1] < item ? item : rMax[i + 1];
        }

        for (int i = 0; i < lMin.length; i++) {
            System.out.print(String.format("%2d ", lMin[i]));
        }
        System.out.println();

        for (int i = 0; i < rMax.length; i++) {
            System.out.print(String.format("%2d ", rMax[i]));
        }
        System.out.println();

        int lMinIndex = 0, rMaxIndex = 0, diff = -1;
        while (lMinIndex < lMin.length && rMaxIndex < rMax.length) {
            if (lMin[lMinIndex] <= rMax[rMaxIndex]) {
                diff = diff > rMaxIndex - lMinIndex ? diff : rMaxIndex - lMinIndex;
                rMaxIndex++;
            } else {
                lMinIndex++;
            }
        }

        return diff;
    }
}

class Main {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(100));
        }

        for (ListIterator<Integer> iter = list.listIterator(); iter.hasNext();) {
            System.out.print(String.format("%2d ", iter.next()));
        }
        System.out.println();

        Solution solution = new Solution();
        System.out.println(solution.maximumGap(list));
    }
}