// Given an array arr[], find the maximum j – i such that arr[j] > arr[i]
import java.util.*;

class Solution {
    // DO NOT MODIFY THE LIST. IT IS READ ONLY
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
        // for (int i = 0; i < 10; i++) {
        //     list.add(random.nextInt(100));
        // }

        list.add(1);
        list.add(10);

        for (ListIterator<Integer> iter = list.listIterator(); iter.hasNext();) {
            System.out.print(String.format("%2d ", iter.next()));
        }
        System.out.println();

        Solution solution = new Solution();
        System.out.println(solution.maximumGap(list));
    }
}