/**
 * Given an array of distinct elements. The task is to find triplets in array whose sum is zero.
 * Ask -> Is array distinct? Is array sorted?
 */
import java.util.*;

class Main {
    /* 1. Naive: O(n^3) Run for all three possible numbers */

    /**
     * O(n^2)
     * 1. Find and store in hashset all pair sums and then compare it against each element - O(n^2)
     *      time and aux complexity
     * 2. Store all elements in hash set and then compare it against all elements - O(n^2) time and
     *      O(n) aux complexity => better than 1.
     *  GOAL: Print ordered set of items whose sum is zero.
     *      + All sets beginnin with i are covered in the ith iteration.
     *      + ith element is then compared with all items after i (as items before i have been
     *          covered in the previous iteration)
     *      + We replace the O(n^3) solution's innermost loop with a HashMap, which then makes it O(n^2)
     */
    static int sumTripletZeroHash(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }

        int count = 0;

        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                int revSum = -1 * (arr[i] + arr[j]);
                if (map.containsKey(revSum)) {
                    int revIndex = map.get(revSum);
                    if (revIndex > j) {
                        count++;
                        System.out.println(i + " " + j + " " + revIndex);
                    }
                }
            }
        }
        return count;
    }

    static int sumTripletZeroHashNonDistinct(int[] arr) {
        int count = 0;
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.get(arr[i]).add(i);
            } else {
                ArrayList<Integer> arrList = new ArrayList<>();
                arrList.add(i);
                map.put(arr[i], arrList);
            }
        }

        for (int i = 0; i < arr.length - 2; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                int inverse = -1 * (arr[i] + arr[j]);
                if (map.containsKey(inverse)) {
                    ArrayList<Integer> valueIndexes = map.get(inverse);
                    for (int k = 0; k < valueIndexes.size(); k++) {
                        if (valueIndexes.get(k) > j) {
                            System.out.println(arr[i] + " " + arr[j] + " " + arr[valueIndexes.get(k)]);
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    /**
     * O(n^2) with O(1) auxillary space
     */
    public static int twoPointer(int[] arr) {
        Arrays.sort(arr);
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int start = i + 1, end = arr.length - 1;
            while (start < end) {
                if (arr[start] + arr[end] < -arr[i]) {
                    start++;
                } else if (arr[start] + arr[end] > -arr[i]) {
                    end--;
                } else if (arr[start] + arr[end] == -arr[i]) {
                    System.out.println(arr[i] + " " + arr[start] + " " + arr[end]);
                    count++;
                    start++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {0, -1, 2, -3, 1};
        System.out.println(sumTripletZeroHash(arr) + "\n");
        System.out.println(sumTripletZeroHashNonDistinct(arr) + "\n");
        System.out.println(twoPointer(arr));
    }
}