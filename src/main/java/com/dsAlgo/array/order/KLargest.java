package com.dsAlgo.array.order; /**
 * Write an efficient program for printing k largest elements in an array.
 * Elements in array can be in any order.
 */

import java.util.*;

class KLargestSolution {
    abstract class Heap {
        ArrayList<Integer> heap;

        Heap(ArrayList<Integer> arr) {
            heap = arr;
            construct(0);
        }

        abstract boolean compare(int left, int right);

        void heapify(int index) {
            if (index >= heap.size()) {
                return;
            }
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;

            int max_index = index;
            if (leftChildIndex < heap.size() && compare(heap.get(leftChildIndex), heap.get(max_index))) {
                max_index = leftChildIndex;
            }

            if (rightChildIndex < heap.size() && compare(heap.get(rightChildIndex), heap.get(max_index))) {
                max_index = rightChildIndex;
            }

            if (max_index != index) {
                int item = heap.get(max_index);
                heap.set(max_index, heap.get(index));
                heap.set(index, item);
                heapify(max_index);
            }
        }

        void buildUp(int index) {
            if (index < 0 || index >= heap.size()) {
                return;
            }

            int parentIndex = index / 2;
            if (compare(heap.get(index), heap.get(parentIndex))) {
                int item = heap.get(index);
                heap.set(index, heap.get(parentIndex));
                heap.set(parentIndex, item);
                buildUp(parentIndex);
            }
        }

        // O(n) -> summation(2^(h-j).j) for level h-j
        void construct(int index) {
            if (index >= heap.size()) {
                return;
            }

            construct(2 * index + 1);
            construct(2 * index + 2);
            heapify(index);
        }

        int extract() {
            int item = heap.get(0);
            int lastItem = heap.remove(heap.size() - 1);
            heap.set(0, lastItem);
            heapify(0);
            return item;
        }

        int peek() {
            return heap.get(0);
        }

        void insert(int item) {
            heap.add(item);
            buildUp(heap.size() - 1);
        }

        void printHeap() {
            System.out.print("Heap: ");
            for (Integer item: heap) System.out.print(item + " "); System.out.println();
        }
    }

    class MaxHeap extends Heap {
        MaxHeap(ArrayList<Integer> arr) {
            super(arr);
        }

        @Override
        boolean compare(int left, int right) {
            return left > right;
        }
    }

    class MinHeap extends Heap {
        MinHeap(ArrayList<Integer> arr) {
            super(arr);
        }

        @Override
        boolean compare(int left, int right) {
            return left < right;
        }
    }
    // O(nk)
    ArrayList<Integer> kSelection(ArrayList<Integer> arr, int k) {
        ArrayList<Integer> kMax = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int index = i;
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(index) < arr.get(j)) {
                    index = j;
                }
            }
            int item = arr.get(index);
            kMax.add(item);
            arr.set(index, arr.get(i));
            arr.set(i, item);
        }
        return kMax;
    }

    // O(nk)
    ArrayList<Integer> kBubble(ArrayList<Integer> arr, int k) {
        int size = arr.size();
        ArrayList<Integer> kMax = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            for (int j = 1; j < size - i; j++) {
                if (arr.get(j - 1) > arr.get(j)) {
                    int item = arr.get(j - 1);
                    arr.set(j - 1, arr.get(j));
                    arr.set(j, item);
                }
            }
            kMax.add(arr.get(size - i - 1));
        }

        return kMax;
    }

    // O((n-k)k) + O(klogk) (if sorted)
    ArrayList<Integer> kMaxArray(ArrayList<Integer> arr, int k) {
        ArrayList<Integer> kMax = new ArrayList<>();
        int size = arr.size();

        for (int i = 0; i < k; i++) {
            kMax.add(arr.get(i));
        }

        for (int i = k; i < arr.size(); i++) {
            int item = arr.get(i);

            int min_index = 0;
            for (int j = 0; j < k; j++) {
                if (kMax.get(min_index) > kMax.get(j)) {
                    min_index = j;
                }
            }

            if (kMax.get(min_index) < item) {
                kMax.set(min_index, item);
            }
        }
        kMax.sort((a, b) -> Integer.compare(b, a));
        return kMax;
    }

    // O(n) + O(klogn)
    ArrayList<Integer> kMaxMaxHeap(ArrayList<Integer> arr, int k) {
        MaxHeap maxHeap = new MaxHeap(arr);
        ArrayList<Integer> kMax = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            kMax.add(maxHeap.extract());
        }

        return kMax;
    }

    // O(k) + (n-k)logk + klogk (if sorted)
    // Similar to kMaxArray solution, except instead of array we use MinHeap
    ArrayList<Integer> kMaxMinHeap(ArrayList<Integer> arr, int k) {
        ArrayList<Integer> kMax = new ArrayList<>();
        for(int i = 0; i < k; i++) {
            kMax.add(arr.get(i));
        }

        MinHeap minHeap = new MinHeap(kMax);

        for (int i = k; i < arr.size(); i++) {
            if (minHeap.peek() < arr.get(i)) {
                minHeap.extract();
                minHeap.insert(arr.get(i));
            }
        }

        kMax.sort((a, b) -> Integer.compare(b, a));
        return kMax;
    }
}

public class KLargest {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();
        int size = 15, k = 5;

        for (int i = 0; i < size; i++) {
            arr.add(random.nextInt());
        }

        for (Integer item: arr) System.out.print(item + " "); System.out.println();
        KLargestSolution solution = new KLargestSolution();

        for (Integer item: solution.kBubble(new ArrayList<Integer>(arr), k)) System.out.print(item + " "); System.out.println();
        for (Integer item: solution.kSelection(new ArrayList<Integer>(arr), k)) System.out.print(item + " "); System.out.println();
        for (Integer item: solution.kMaxArray(new ArrayList<Integer>(arr), k)) System.out.print(item + " "); System.out.println();
        for (Integer item: solution.kMaxMaxHeap(new ArrayList<Integer>(arr), k)) System.out.print(item + " "); System.out.println();
        for (Integer item: solution.kMaxMinHeap(new ArrayList<Integer>(arr), k)) System.out.print(item + " "); System.out.println();
    }
}
