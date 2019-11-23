import java.util.*;

class Solution {
    void reverse(ArrayList<Integer> arr) {
        int begin = 0, end = arr.size() - 1;
        while (begin < end) {
            int beginItem = arr.get(begin);
            int endItem = arr.get(end);
            arr.set(begin, endItem);
            arr.set(end, beginItem);
            begin++;
            end--;
        }
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Random random = new Random();

        ArrayList<Integer> arr = new ArrayList<>();
        int size = 15;
        for (int i = 0; i < size; i++) {
            arr.add(random.nextInt(100));
        }

        for (Integer item: arr) System.out.print(String.format("%3d ", item)); System.out.println();
        solution.reverse(arr);
        for (Integer item: arr) System.out.print(String.format("%3d ", item)); System.out.println();
    }
}