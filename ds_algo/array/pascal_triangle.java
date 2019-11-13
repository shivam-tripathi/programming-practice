import java.util.*;

class Solution {
    int getItem(ArrayList<Integer> arr, int index) {
        if (index < 0 || index >= arr.size() - 1) {
            return 0;
        }
        return arr.get(index);
    }
    public ArrayList<Integer> getRow(int A) {
        A += 1;
        ArrayList<Integer> arr = new ArrayList<>();

        arr.add(1);
        if (A == 1) {
            return arr;
        }

        int count = 1;
        while(count < A) {
            int prevItem = 0;
            arr.add(0);
            for (int i = 0; i < arr.size(); i++) {
                int presItem = getItem(arr, i);
                arr.set(i, prevItem + presItem);
                prevItem = presItem;
            }
            System.out.println();
            count++;
        }
        return arr;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        for (int i = 0; i < 5; i++) {
            System.out.println(solution.getRow(i));
        }
    }
}