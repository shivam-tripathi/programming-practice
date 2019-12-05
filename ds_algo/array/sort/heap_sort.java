/**
 * Heap sort
 */

import java.util.*;

class Solution {
    void heapify(ArrayList<Integer> arr, int size, int index) {
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        int maxIndex = index;

        if (leftIndex < size && arr.get(leftIndex) > arr.get(maxIndex)) {
            maxIndex = leftIndex;
        }

        if (rightIndex < size && arr.get(rightIndex) > arr.get(maxIndex)) {
            maxIndex = rightIndex;
        }

        if (maxIndex != index) {
            int temp = arr.get(maxIndex);
            arr.set(maxIndex, arr.get(index));
            arr.set(index, temp);
            heapify(arr, size, maxIndex);
        }
    }

    void heapSort(ArrayList<Integer> arr) {
        int size = arr.size();

        // O(n)
        for (int i = size - 1; i >= 0; i--) {
            heapify(arr, size, i);
        }

        // O(nlogn)
        for (int i = 0; i < size; i++) {
            int top = arr.get(0);
            arr.set(0, arr.get(size - i -1));
            arr.set(size - i - 1, top);
            heapify(arr, size - i - 1, 0);
        }
    }
}

class Main {
    public static void main(String args[]) {
        Random random = new Random();
        Solution solution = new Solution();

        ArrayList<Integer> arr = new ArrayList<>();
        int size = 15;
        for (int i = 0; i < size; i++) {
            arr.add(random.nextInt());
        }

        ArrayList<Integer> arrCopy = new ArrayList<>(arr);
        Collections.sort(arrCopy);

        for (Integer item: arr) System.out.print(item + " "); System.out.println();

        solution.heapSort(arr);

        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i).equals(arrCopy.get(i)));
        }
    }
}
