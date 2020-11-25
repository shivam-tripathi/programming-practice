package com.dsAlgo.array.arrange; /**
 * Rearrange positive and negative numbers in O(n) time and O(1) extra space
 * 1. Rearrange: Negatives on one side, positive on the other
 * 2. Rearrange: Positive on odd positions, negative on even positive
 */

import java.util.*;

class PositiveNegativeSolution {
    // 2 pointer (unordered)
    void rearrange(ArrayList<Integer> arr) {
        int size = arr.size();
        int begin = 0, end = size - 1;
        while (begin < end) {
            if (arr.get(begin) >= 0 && arr.get(end) < 0) {
                int temp = arr.get(begin);
                arr.set(begin, arr.get(end));
                arr.set(end, temp);
                begin++;
                end--;
            } else if (arr.get(end) >= 0) {
                end--;
            } else if (arr.get(begin) < 0) {
                begin++;
            }
        }
    }

    // Counter (ordered for the first half)
    void rearrange2(ArrayList<Integer> arr) {
        int count = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) < 0) {
                if (i != count) {
                    int item = arr.get(count);
                    arr.set(count, arr.get(i));
                    arr.set(i, item);
                }

                count++;
            }
        }
    }

    // Alt rearrange - 2 pointer. Unordered.
    void rearrangeAlt(ArrayList<Integer> arr) {
        int size = arr.size();
        int pos = 0, neg = 1;
        while (pos < size && neg < size) {
            // System.out.println(pos + " " + neg);
            if (arr.get(pos) >= 0) {
                pos += 2;
            } else if (arr.get(neg) < 0) {
                neg += 2;
            } else if (arr.get(pos) < 0 && arr.get(neg) >= 0) {
                int temp = arr.get(pos);
                arr.set(pos, arr.get(neg));
                arr.set(neg, temp);
                pos += 2;
                neg += 2;
            }
        }

        if (pos < size) {
            int end = size % 2 == 0 ? size - 2 : size - 1; // Last even index
            while (pos < end) {
                if (arr.get(pos) >= 0) pos += 2;
                else if (arr.get(end) < 0) end -= 2;
                else {
                    int temp = arr.get(end);
                    arr.set(end, arr.get(pos));
                    arr.set(pos, temp);
                    pos += 2;
                    end -= 2;
                }
            }
        } else {
            int end = size % 2 == 0 ? size - 1 : size - 2; // Last odd index
            while (neg < end) {
                if (arr.get(neg) < 0) pos += 2;
                else if (arr.get(end) >= 0) end -= 2;
                else {
                    int temp = arr.get(end);
                    arr.set(end, arr.get(neg));
                    arr.set(neg, temp);
                    neg += 2;
                    end -= 2;
                }
            }
        }
    }
}

public class PositiveNegative {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 25; i++) {
            if (i % 10 == 0) {
                arr.add(0);
            }
            arr.add(random.nextInt(100) * (random.nextBoolean() ? -1 : 1));
        }

        for (Integer item: arr) System.out.print(item + " "); System.out.println();

        PositiveNegativeSolution solution = new PositiveNegativeSolution();

        solution.rearrangeAlt(arr);
        for (Integer item: arr) System.out.print(item + " "); System.out.println();

        solution.rearrange2(arr);

        for (Integer item: arr) System.out.print(item + " "); System.out.println();
    }
}