package com.dsAlgo.array.order; /**
 * Find the kth largest number
 * All methods which worked for extracting k largest elements would by default work for
 * extracting kth largest element. These are:
 * 1. kBubble
 * 2. kSelection
 * 3. sort
 * 4. kArray
 * 5. kMaxHeap
 * 6. kMinHeap (heap select)
 *
 * Method for O(n) solution (using median of medians as pivot is done separately)
 */

import java.util.*;

class KthLargestSolution {
    Random random;
    KthLargestSolution() {
        random = new Random();
    }

    int partition(ArrayList<Integer> arr, int begin, int end) {
        // Select a random pivot
        int pivotIndex = random.nextInt(end - begin) + begin;

        // Move the pivot to last position
        int item = arr.get(end);
        arr.set(end, arr.get(pivotIndex));
        arr.set(pivotIndex, item);


        // Partition the array
        int i = begin - 1;
        for (int j = begin; j < end; j++) {
            if (arr.get(j) < arr.get(end)) {
                i++;
                if (i != j) {
                    int temp = arr.get(j);
                    arr.set(j, arr.get(i));
                    arr.set(i, temp);
                }
            }
        }


        int temp = arr.get(end);
        arr.set(end, arr.get(i + 1));
        arr.set(i + 1, temp);
        return i + 1;
    }

    boolean quickSelect(ArrayList<Integer> arr, int begin, int end, int k) {
        if (begin > end) {
            return false;
        }

        if (begin == end) {
            boolean action = begin == k;
            return action;
        }

        int mid = partition(arr, begin, end);

        if (mid == k) {
            return true;
        }

        if (mid > k) {
            return quickSelect(arr, begin, mid - 1, k);
        }

        return quickSelect(arr, mid + 1, end, k);
    }

    int quickSelect(ArrayList<Integer> arr, int k) {
        if (quickSelect(arr, 0, arr.size() - 1, k)) {
            return arr.get(k);
        }

        return -1;
    }
}

public class KthLargest {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();
        int size = 15;

        for (int i = 0; i < size; i++) {
            arr.add(random.nextInt());
        }

        ArrayList<Integer> sortedArr = new ArrayList<>(arr);
        Collections.sort(sortedArr);
        for (Integer item: sortedArr) System.out.print(item + " "); System.out.println();

        KthLargestSolution solution = new KthLargestSolution();
        for (int i = 0; i < size; i++) {
            int item = solution.quickSelect(new ArrayList<>(arr), i);
            System.out.println(i + " " + item + " " + sortedArr.get(i) + " " + (item == sortedArr.get(i)));
        }
    }
}
