/**
 * Find the kth largest number
 * All methods which worked for extracting k largest elements would by default work for
 * extracting kth largest element. These are:
 * 1. kBubble
 * 2. kSelection
 * 3. sort
 * 4. kArray
 * 5. kMaxHeap
 * 6. kMinHeap (heap select)
 */

import java.util.*;

class Solution {
    // Unnecessary complicated, use method 2 for simpler implementation
    int partition(ArrayList<Integer> arr, int begin, int end) {
        Random random = new Random();

        // Move pivot item to the beginning position
        int pivotIndex = random.nextInt(end - begin) + begin;
        int pivotItem = arr.get(pivotIndex);
        arr.set(pivotIndex, arr.get(begin));
        arr.set(begin, pivotItem);

        // Start pointer - everything behind this is smaller than pivotItem
        int startPointer = begin + 1;
        // End pointer - everything behind this is larger than pivotItem
        int endPointer = end;


        while (startPointer <= endPointer) {
            if (arr.get(startPointer) >= pivotItem) {
                int temp = arr.get(startPointer);
                arr.set(startPointer, arr.get(endPointer));
                arr.set(endPointer, temp);
                endPointer--;
            } else {
                startPointer++;
            }
        }

        if (startPointer != begin) {
            int temp = arr.get(startPointer - 1);
            arr.set(startPointer - 1, pivotItem);
            arr.set(begin, temp);
            return startPointer - 1;
        }

        return begin;
    }

    // Like count method to divide all positives and negatives
    int partition2(ArrayList<Integer> arr, int begin, int end) {
        int i = begin - 1;
        // Pivot item -> end, count of < pivot item -> i
        for (int j = begin; j < end; j++) {
            if (arr.get(j) < arr.get(end)) {
                i++;
                if (i != j) {
                    int item = arr.get(j);
                    arr.set(j, arr.get(i));
                    arr.set(i, item);
                }
            }
        }

        int item = arr.get(end);
        arr.set(end, arr.get(i + 1));
        arr.set(i + 1, item);
        return i + 1;
    }

    void quickSort(ArrayList<Integer> arr, int begin, int end) {
        if (begin >= end || end >= arr.size()) {
            return;
        }
        int mid = partition2(arr, begin, end);
        quickSort(arr, begin, mid - 1);
        quickSort(arr, mid + 1, end);
    }

    void quickSort(ArrayList<Integer> arr) {
        quickSort(arr, 0, arr.size() - 1);
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();
        int size = 15;

        for (int i = 0; i < size; i++) {
            arr.add(random.nextInt());
            // arr.add(size - i);
        }

        ArrayList<Integer> arrCopy = new ArrayList<>(arr);
        Collections.sort(arrCopy);
        for (Integer item: arrCopy) System.out.print(item + " "); System.out.println();
        solution.quickSort(arr);
        for (Integer item: arr) System.out.print(item + " "); System.out.println();
    }
}

