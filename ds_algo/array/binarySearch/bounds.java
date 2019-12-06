import java.util.*;

class Solution {
    int lowerBound(ArrayList<Integer> arr, int begin, int end, int item) {
       int lower = begin, upper = end, mid;

       if (item > arr.get(end)) {
           return end;
       }

        while (lower != (lower + upper) / 2) {
            mid = (lower + upper) / 2;

            if (item > arr.get(mid)) {
                lower = mid;
            }
            if (item <= arr.get(mid)) {
                upper = mid;
            }
        }

        return lower;
    }

    int upperBound(ArrayList<Integer> arr, int begin, int end, int item) {
        int lower = begin, upper = end, mid;

        if (item < arr.get(begin)) {
            return begin;
        }

        while(lower != (upper + lower) / 2) {
            mid = (lower + upper) / 2;
            if (arr.get(mid) <= item) {
                lower = mid;
            }
            if (arr.get(mid) > item) {
                upper = mid;
            }
        }

        return upper;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<Integer> arr = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            arr.add(random.nextInt(100));
        }

        Collections.sort(arr);

        for (Integer item: arr) System.out.print(item + " "); System.out.println();

        for (int i = 0; i < 10; i++) {
            int item = arr.get(i) + 1;
            int idx = solution.lowerBound(arr, 0, arr.size() - 1, item);
            System.out.println("Lower bound for " + item + " is at index " + idx + " value " + arr.get(idx));
        }

        System.out.println("\n--\n");

        for (int i = 0; i < 10; i++) {
            int item = arr.get(i) - 1;
            int idx = solution.upperBound(arr, 0, arr.size() - 1, item);
            System.out.println("Upper bound for " + item + " is at index " + idx + " value " + arr.get(idx));
        }
    }
}
