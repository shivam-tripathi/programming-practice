import java.util.*;

class Solution {
    int getIndex(int in, int d, int size) {
        return (in - d + size) % size;
    }

    void rotate(ArrayList<Integer> arr, int d) {
        int size = arr.size();
        if (d % size == 0) {
            return;
        }

        int tempa = size, tempb = d % size; // Find gcd
        while (tempb != 0) {
            int temp = tempa % tempb;
            tempa = tempb;
            tempb = temp;
        }

        for (int i = 0; i < tempa; i++) {
            int beginItem = arr.get(i);
            int prevIndex = i;
            int prevItem = arr.get(prevIndex);
            while (true) {
                int nextIndex = (prevIndex - d + size) % size;
                int nextItem = arr.get(nextIndex);

                arr.set(nextIndex, prevItem);

                prevItem = nextItem;
                prevIndex = nextIndex;

                if (prevItem == beginItem) {
                    break;
                }
            }
        }
    }

    int search(ArrayList<Integer> arr, int item, int shift) {
        int size = arr.size();
        int begin = 0, end = size - 1;
        while (begin <= end) {
            int mid = (begin + end) / 2;
            int midIndex = getIndex(mid, shift, size);
            int midItem = arr.get(midIndex);
            if (midItem == item) {
                return midIndex;
            }
            if (item < midItem) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return -1;
    }
}

class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        int size = 18, shift = 3;
        for (int i = 0; i < size; i++) {
            arr.add(i);
        }

        Solution solution = new Solution();
        solution.rotate(arr, shift);
        for (Integer i: arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < size + 3; i++) {
            int solIndex = solution.search(arr, i, shift);
            System.out.println(solIndex < 0 ? "Not found " + i : "Found " + arr.get(solIndex) + " at index " + solIndex);
        }
    }
}