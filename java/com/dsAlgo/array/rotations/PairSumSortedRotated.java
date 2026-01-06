package com.dsAlgo.array.rotations;

/**
 * Same two pointer technique after finding pivot
 */

import java.util.*;

class PairSumSortedRotatedSolution {
    void rotate(ArrayList<Integer> arr, int shift) {
        int size = arr.size();
        if (shift % size == 0) {
            return;
        }
        shift = shift % size;
        int a = size, b = shift;
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        for (int i = 0; i < a; i++) {
            int prevIndex = i;
            int prevItem = arr.get(prevIndex), beginItem = prevItem;

            while (true) {
                int nextIndex = (prevIndex + size - shift) % size;
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

    int findPivot(ArrayList<Integer> arr) {
        int size = arr.size();
        int begin = 0, end = size - 1, mid;
        while(begin != (begin + end) / 2) {
            mid = (begin + end) / 2;
            if (arr.get(begin) < arr.get(mid) && arr.get(mid) < arr.get(end)) {
                break;
            }

            if (arr.get(begin) > arr.get(mid)) {
                end = mid;
            }
            if (arr.get(end) < arr.get(mid)) {
                begin = mid;
            }
        }
        return begin;
    }

    int getIndex(int index, int shift, int size) {
        return (index + size - shift) % size;
    }

    void sumPair(ArrayList<Integer> arr, int sum) {
        int size = arr.size();
        int shift = size - findPivot(arr) - 1;
        int begin = 0, end = size - 1;
        while (begin != end) {
            int beginItem = arr.get(getIndex(begin, shift, size));
            int endItem = arr.get(getIndex(end, shift, size));

            if (beginItem + endItem == sum) {
                System.out.println(beginItem + " + " + endItem);
                begin++;
            }

            if (beginItem + endItem < sum) {
                begin++;
            }

            if (beginItem + endItem > sum) {
                end--;
            }
        }
    }
}

public class PairSumSortedRotated {
    public static void main(String[] args) {
        PairSumSortedRotatedSolution solution = new PairSumSortedRotatedSolution();
        int size = 10, shift = 3;

        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            arr.add(i);
        }
        solution.rotate(arr, shift);
        for (Integer item: arr) System.out.print(item); System.out.println();
        solution.sumPair(arr, 9);
    }
}