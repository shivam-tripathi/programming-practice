package com.dsAlgo.array.arrange; /**
 * Loop over array, if (arr[i] == i || arr[i] == -1) then i++ else move arr[i] to arr[i] position
 * and arr[arr[i]] to i position - repeat (no change in i).
 */

import java.util.*;

class IndexIsElementSolution {
    void rearrage(ArrayList<Integer> arr) {
        int index = 0;
        while(index < arr.size()) {
            if (arr.get(index) != index && arr.get(index) != -1) {
                int item = arr.get(index);
                int temp = arr.get(item);
                arr.set(item, item);
                arr.set(index, temp);
            } else {
                index++;
            }
        }
    }
}

public class IndexIsElement {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        int size = 20;
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr.add(random.nextBoolean() ? i : -1);
        }

        IndexIsElementSolution solution = new IndexIsElementSolution();
        solution.rearrage(arr);
        for (Integer item: arr) System.out.printf("%2d ", item); System.out.println();
        for (int i = 0; i < arr.size(); i++) System.out.printf("%2d ", i); System.out.println();
    }
}