/**
 * Loop over array, if (arr[i] == i || arr[i] == -1) then i++ else move arr[i] to arr[i] position
 * and arr[arr[i]] to i position - repeat (no change in i).
 */

import java.util.*;

class Solution {
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

class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        int size = 20;
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr.add(random.nextBoolean() ? i : -1);
        }

        Solution solution = new Solution();
        solution.rearrage(arr);
        for (Integer item: arr) System.out.print(String.format("%2d ", item)); System.out.println();
        for (int i = 0; i < arr.size(); i++) System.out.print(String.format("%2d ", i)); System.out.println();
    }
}