/**
 * Positive negative rearragement (one side and alternate) with O(1) space and ordered.
 */

import java.util.*;

class Solution {
    // Use insertion sort based approach but keep the order intact O(n^2)
    void insertionBased(ArrayList<Integer> arr) {
        int size = arr.size(), i, j;
        for (i = 1; i < size; i++) {
            int item = arr.get(i);
            if (item < 0) {
                for (j = i - 1; j >= 0 && arr.get(j) >= 0; j--) {
                    arr.set(j + 1, arr.get(j));
                }
                arr.set(j + 1, item);
            }
            if (item == 0) {
                for (j = i - 1; j >= 0 && arr.get(j) > 0; j--) {
                    arr.set(j + 1, arr.get(j));
                }
                arr.set(j + 1, item);
            }
        }
    }

    void reverse(ArrayList<Integer> arr, int begin, int end) {
        if (begin > end || end >= arr.size()) {
            return;
        }
        while(begin < end) {
            int temp = arr.get(begin);
            arr.set(begin++, arr.get(end));
            arr.set(end--, temp);
        }
    }

    // Merge sort with O(1) extra space (we don't need full sort, just ordered separation')
    void mergeBased(ArrayList<Integer> arr, int begin, int end) {
        if (begin == end) {
            return;
        }
        int mid = (begin + end) / 2;
        mergeBased(arr, begin, mid);
        mergeBased(arr, mid + 1, end);

        /* leftPointer = where left positive starts, rightPointer = where right negative ends
         -a -b -c d e f -a1 -b1 -c1 d1 e1 f1 (Flip d -> -c1)
         -a -b -c -c1 -b1 -a1 f e d d1 e1 f1 (Flip -c1 -> -a1 and f -> d)
         -a -b -c -a1 -b1 -c1 d e f d1 e1 f1 */
        if (arr.get(mid) < 0 || arr.get(mid + 1) >= 0) { // No action: Left all negative or right all positive
            return;
        }

        int leftPointer = begin;
        for (int i = begin; i <= mid; i++) {
            if (arr.get(i) < 0) { leftPointer = i + 1; } else break;
        }

        int rightPointer = mid;
        for (int i = mid + 1; i <= end; i++) {
            if (arr.get(i) < 0) { rightPointer = i; }
        }

        if (leftPointer < rightPointer) reverse(arr, leftPointer, rightPointer);
        reverse(arr, leftPointer, leftPointer + (rightPointer - mid - 1));
        reverse(arr, rightPointer - (mid - leftPointer), rightPointer);
    }

    void mergeBased(ArrayList<Integer> arr) {
        mergeBased(arr, 0, arr.size() - 1);
    }

    // Inplace ordered alternate positive negative
    // If an element is in an incorrect position, find the next item which should be here and right
    // rotate the array by 1. Complexity is O(n^2)
    void rightRotateByOne(ArrayList<Integer> arr, int begin , int end) {
        int last = arr.get(end);
        for (int i = end; i > begin; i--) {
            arr.set(i, arr.get(i - 1));
        }
        arr.set(begin, last);
    }

    int nextIndexOfOppositeSign(ArrayList<Integer> arr, int begin, int item) {
        int index = -1;
        for (int i = begin; i < arr.size(); i++) {
            if ((item < 0 && arr.get(i) >= 0) || (item >= 0 && arr.get(i) < 0)) {
                index = i;
                break;
            }
        }
        return index;
    }

    void altOrderedPartitioned(ArrayList<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            if ((i % 2 == 1 && arr.get(i) < 0) || (i % 2 == 0 && arr.get(i) >= 0)) {
                int j = nextIndexOfOppositeSign(arr, i + 1, arr.get(i));
                if (j != -1) {
                    rightRotateByOne(arr, i, j);
                } else {
                    break;
                }
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        // int[] a = {0, -38, -17, -58, -48, -92, -69, 71, 96, 95, -12, 100};
        // int[] a = {0, -90, -30, -32, 92, -21, -71, 64, -11, 51, 0, -39};
        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
             arr.add(random.nextInt(100) * (random.nextBoolean() ? -1 : 1) * (random.nextInt(50)/10 == 0 ? 0 : 1));
            // arr.add(a[i]);
        }

        System.out.print("Original Array:    "); for (Integer item: arr) System.out.print(item + " "); System.out.println();

        ArrayList<Integer> ansArr = new ArrayList<>(arr);
        int zeroCount = 0, forwIndex = 0, revIndex = arr.size();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) < 0) {
                ansArr.set(forwIndex++, arr.get(i));
            }
            if (arr.get(i) == 0) {
                zeroCount++;
            }
            if (arr.get(i) >= 0) {
                ansArr.set(--revIndex, arr.get(i));
            }
        }
        Solution solution = new Solution();
        solution.reverse(ansArr, revIndex, arr.size() - 1);
        System.out.print("Partitioned Array: "); for (Integer item: ansArr) System.out.print(item + " "); System.out.println();

        ArrayList<Integer> mergeArr = new ArrayList<>(arr);
        solution.mergeBased(mergeArr);
        System.out.print("MergeBased Array:  "); for (Integer item: mergeArr) System.out.print(item + " "); System.out.println();

        ArrayList<Integer> altArr = new ArrayList<>(arr);
        solution.altOrderedPartitioned(altArr);
        System.out.print("Alternate Array: "); for (Integer item: altArr) System.out.print(item + " "); System.out.println();
    }
}
