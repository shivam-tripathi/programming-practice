package com.dsAlgo.array.arrange;
/**
 * Given an array of numbers, arrange them in a way that yields the largest value.
 * For example, if the given numbers are {54, 546, 548, 60}, the arrangement
 * 6054854654 gives the largest value. And if the given numbers are
 * {1, 34, 3, 98, 9, 76, 45, 4}, then the arrangement 998764543431 gives the
 * largest value.
 *
 * Notes:
 * - Looks most simple, is most complicated
 * - Numbers are non negative. ALWAYS consider this.
 * - What happens when two numbers are equal?
 * - What happens if a number is prefix of other one?
 *
 * If we two string have same prefix, we loop over over repeatedly till both strings have
 * been compared. Assume number a and number ax. If a > x then it makes sense to use aax
 * rather than axa. Hence one other method could be make all strings of number repeat themselves
 * to the twice size of the max integer. Twice because consider 12 and 121. Thus if
 * |a| = |x| then we compare aa with ax. But this is not really needed, as we can use
 * same index just % it with size of the strings.
 */

import java.util.*;
import java.lang.*;
import java.math.BigInteger;
import java.util.concurrent.ThreadLocalRandom;

class ExtendedNum
{
    int originalValue;
    long modifiedValue;

    public ExtendedNum(int originalValue, int n)
    {
        this.originalValue = originalValue;
        String s = Integer.toString(originalValue);
        StringBuilder sb = new StringBuilder(s);
        StringBuilder ans = new StringBuilder();
        while (ans.length() <= n + 1)
            ans.append(sb);

        s = ans.substring(0, n + 1);
        modifiedValue = Long.parseLong(s);
    }

    public String toString()
    {
        return "[" + modifiedValue +
                ", " + originalValue + "]";
    }
}


class FormBiggestNumberSolution {
     String solveIntArr(ArrayList<Integer> arr) {
        arr.sort((a, b) -> {
            ArrayList<Integer> arrA = new ArrayList<>();
            ArrayList<Integer> arrB = new ArrayList<>();

            if (a == 0 && b != 0) return -1;
            if (a != 0 && b == 0) return 1;
            if (a == 0) return 0;

            while (a != 0) {
                arrA.add(a % 10);
                a /= 10;
            }
            while (b != 0) {
                arrB.add(b % 10);
                b /= 10;
            }

            int index = 0, minSize = Math.min(arrA.size(), arrB.size());
            while (!(index > arrA.size() * 2 && index > arrB.size() * 2)) {
                int aI = index % arrA.size();
                int bI = index % arrB.size();
                if (arrA.get(arrA.size() - 1 - aI) > arrB.get(arrB.size() - 1 - bI)) {
                    return 1;
                } else if (arrA.get(arrA.size() - 1 - aI) < arrB.get(arrB.size() - 1 - bI)) {
                    return -1;
                }

                index++;
            }

            return 0;
        });

        StringBuilder ans = new StringBuilder();
        for (int i = arr.size() - 1; i >= 0; i--) {
            ans.append(arr.get(i).toString());
        }

        return ans.toString();
    }

    String solveStringArr(ArrayList<String> arr) {
        arr.sort((a, b) -> {
            int indexA, indexB, index = 0;
            // System.out.println("Comparing " + a + " " + b);
            if (a.equals(b)) {
                return 0;
            }
            while (!(index >= 2 * a.length() && index >= 2 * b.length())) {
                indexA = index % a.length();
                indexB = index % b.length();
                // System.out.println("\tComparing " + a.charAt(indexA) + " " + b.charAt(indexB));
                if (a.charAt(indexA) > b.charAt(indexB)) {
                    return 1;
                } else if (a.charAt(indexA) < b.charAt(indexB)) {
                    return -1;
                }
                index++;
            }
            return 0;
        });
        StringBuilder ans = new StringBuilder();
        for (int i = arr.size() - 1; i >= 0; i--) {
            ans.append(arr.get(i));
        }
        return ans.toString();
    }

    public String largestNumber(List<Integer> arr) {
        // finding number of digits in maximum element
        // present in array
        int n = Collections.max(arr).toString().length();

        ArrayList<ExtendedNum> en =
                new ArrayList<>();
        for (Integer integer : arr)
            en.add(new ExtendedNum(integer, n));

        // sort based on modified value
        en.sort((p1, p2) ->
                (int) (p2.modifiedValue - p1.modifiedValue));

        StringBuilder sb = new StringBuilder();
        for (ExtendedNum extendedNum : en) sb.append(Long.toString(extendedNum.originalValue));


        // To remove any zeroes at head.
        BigInteger bi = new BigInteger(sb.toString());
        return bi.toString();
    }
}

public class FormBiggestNumber {
    public static void main(String[] args) {
        // Integer[] a = {1, 34, 3, 98, 9, 76, 45, 4};
        // Integer[] a = {54, 546, 548, 60};
        // Integer[] a = {1, 34, 3, 98, 9, 76, 45, 4, 12, 121};
        FormBiggestNumberSolution solution = new FormBiggestNumberSolution();
        Random rand = ThreadLocalRandom.current();

        int testCases = 1000;
        int size = 1000;
        while (testCases-- > 0) {
            ArrayList<Integer> arr = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                arr.add(rand.nextInt(100));
            }

            String ans1 = solution.solveIntArr(arr);
            String ans2 = solution.largestNumber(arr);
            System.out.print(ans1.equals(ans2) + "\n");
        }

        // String[] strA = {"1","34","3","98","9","76","45","4","12","121"};
        // ans = solution.solveStringArr(new ArrayList<String> (Arrays.asList(strA)));
        // System.out.println(ans);
    }
}
