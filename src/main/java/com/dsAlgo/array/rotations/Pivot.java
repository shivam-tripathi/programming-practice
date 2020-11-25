package com.dsAlgo.array.rotations;

import java.util.*;

class PivotSolution {
    void rotate(ArrayList<Integer> arr, int shift) {
        int size = arr.size();
        if (shift % size == 0) {
            return;
        }

        int a = size, b = shift % size;
        while(b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        for (int i = 0; i < a; i++) {
            int prevIndex = i;
            int prevItem = arr.get(prevIndex);
            int beginItem = prevItem;
            while(true) {
                int nextIndex = (prevIndex - shift + size) % size;
                int nextItem = arr.get(nextIndex);

                arr.set(nextIndex, prevItem);

                prevIndex = nextIndex;
                prevItem = nextItem;
                if (prevItem == beginItem) {
                    break;
                }
            }
        }
    }

    int pivot(ArrayList<Integer> arr) {
        int size = arr.size();
        int begin = 0, end = size - 1, mid = (begin + end) / 2;
        while (begin != (begin + end) / 2) {
            mid = (begin + end) / 2;
            // System.out.println("\t" + begin + " [" + mid + "] " + end);
            if (arr.get(mid) < arr.get(begin)) {
                end = mid;
            } else if (arr.get(mid) > arr.get(end)) {
                begin = mid;
            }
        }
        return begin;
    }
}

public class Pivot {
    public static void main(String[] args) {
        PivotSolution solution = new PivotSolution();

        for (int size = 2; size < 100; size ++) {
            /* Generate input arrayList. All elements must be unique */
            Random random = new Random();
            ArrayList<Integer> outArr = new ArrayList<>();
            SortedSet<Integer> set = new TreeSet<>();
            for (int k = 0; k < size;) {
                int item = random.nextInt();
                if (!set.contains(item)) {
                    set.add(item);
                    outArr.add(item);
                    k++;
                }
            }
            Collections.sort(outArr); // Input must be sorted
            // for (Integer item: outArr) System.out.print(item + " "); System.out.println();

            System.out.println("Processing for size " + size);
            for (int shift = 1; shift < size; shift++) {
                ArrayList<Integer> arr = new ArrayList<>(outArr);
                System.out.print("\tRotating array by " + shift);
                solution.rotate(arr, shift);
                int minItem = arr.get(solution.pivot(arr) + 1);
                if (minItem != set.first()) {
                    System.out.println("\tError!");
                } else {
                    System.out.println(" Works!");
                }
            }
        }
    }
}