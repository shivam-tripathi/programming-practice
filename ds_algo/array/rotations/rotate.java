/**
 * Juggles first gcd(arr.length, shift) numbers in cicular manner, O(n) O(1)
 */

import java.util.*;

class Solution {
    int gcd(int a, int b) {
        if (a < b) {
            int temp = b;
            b = a;
            a = temp;
        }

        while (b > 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
    void rotate(ArrayList<Integer> arr, int d) {
        System.out.print("Rotated with " + d + ": ");
        int size = arr.size();
        int times = gcd(size, d);
        for (int i = 0; i < times; i++) {
            int begin = arr.get(i);
            int index = i, item = begin;

            while(true) {
                int nextIndex = (index - d) % size;
                if (nextIndex < 0) {
                    nextIndex += size;
                }
                // System.out.println("Next index: " + nextIndex + nextIndex % size);
                int temp = arr.get(nextIndex);
                arr.set(nextIndex, item);
                item = temp;
                index = nextIndex;
                if (item == begin) {
                    break;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Random random = new Random();
        int size = 12;
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            // arr.add(random.nextInt(100));
            arr.add(i);
            System.out.print(arr.get(i) + " ");
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> tempArr = new ArrayList<>();
            for (Integer item: arr) {
                tempArr.add(item);
            }
            solution.rotate(tempArr, i);
        }
    }
}

