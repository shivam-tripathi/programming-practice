import java.util.*;

class Solution {
    public ArrayList<Integer> plusOne(ArrayList<Integer> A) {
        int carry = 1;
        int i;
        for (i = A.size() - 1; i >= 0; i--) {
            if (A.get(i) == 0 && carry == 0) {
                break;
            }
            int number = A.get(i);
            int sum = number + carry;
            carry = 0;
            if (sum / 10 != 0) {
                sum = sum % 10;
                carry = 1;
            }
            A.set(i, sum);
        }

        ArrayList<Integer> sumArr = new ArrayList<Integer>();
        boolean trailingZeros = true;
        if (carry != 0 && i < 0 ) {
            sumArr.add(1);
            trailingZeros = false;
        }
        for (i = 0; i < A.size(); i++) {
            if (trailingZeros && A.get(i) != 0) {
                trailingZeros = false;
            }
            if (!trailingZeros) {
                sumArr.add(A.get(i));
            }
        }
        return sumArr;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] a = {9, 9, 9, 9, 9};
        ArrayList<Integer> input = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            input.add(a[i]);
        }

        ArrayList<Integer> output = solution.plusOne(input);

        System.out.println("SUM:");
        for (int i = 0; i < output.size(); i++) {
            System.out.print(output.get(i) + " ");
        }

        System.out.println();
    }
}