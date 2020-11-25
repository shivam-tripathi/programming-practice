package com.leet.medium;

import java.util.*;
import java.util.stream.*;

class PermutationsIISolution {
    private Set<Integer> doneSet = new HashSet<>();
    private List<List<Integer>> permutes = new ArrayList<>();

    void solve(int[] nums, ArrayList<Integer> permute, int doneInt) {
        int doneCount = 0;
        ArrayList<Integer> newPermute = permute == null ? new ArrayList<>() : (ArrayList<Integer>) permute.clone();
        newPermute.add(-1);

        for (int i = 0; i < nums.length; i++) {
            if ((doneInt & (1<<i)) == 0) {
                newPermute.set(newPermute.size() - 1, nums[i]);
                int hashCode = newPermute.hashCode();
                if (!doneSet.contains(hashCode)) {
                    solve(nums, newPermute, doneInt | (1<<i));
                    doneSet.add(hashCode);
                }
            } else {
                doneCount++;
            }
        }

        if (doneCount == nums.length) {
            permutes.add(permute);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        solve(nums, null, 0);
        return permutes;
    }
}

class Solution2 {
    private Set<Integer> doneSet = new HashSet<>();
    private List<List<Integer>> permutes = new ArrayList<>();

    void solve(int[] nums, ArrayList<Integer> permute, int doneInt) {
        int doneCount = 0;
        ArrayList<Integer> newPermute = permute == null ? new ArrayList<>() : (ArrayList<Integer>) permute.clone();
        newPermute.add(-1);

        for (int i = 0; i < nums.length; i++) {
            if ((doneInt & (1<<i)) == 0) {
                newPermute.set(newPermute.size() - 1, nums[i]);
                int hashCode = newPermute.hashCode();
                if (!doneSet.contains(hashCode)) {
                    solve(nums, newPermute, doneInt | (1<<i));
                    doneSet.add(hashCode);
                }
            } else {
                doneCount++;
            }
        }

        if (doneCount == nums.length) {
            permutes.add(permute);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        solve(nums, null, 0);
        return permutes;
    }
}

//class Solution3 {
//    private Set<Integer> doneSet = new HashSet<>();
//    private List<List<Integer>> permutes = new ArrayList<>();
//
//    void solve(int[] nums, Integer[] permute, int size, int doneInt, String doneString) {
//        int doneCount = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if ((doneInt & (1<<i)) == 0) {
//                String key = doneString + nums[i];
//                if (!doneSet.contains(key)) {
//                    permute[size+1] = nums[i];
//                    solve(nums, newPermute, size+1, doneInt | (1<<i));
//                    doneSet.add(key);
//                }
//            } else {
//                doneCount++;
//            }
//        }
//
//        if (doneCount == nums.length) {
//            permutes.add(Arrays.asList(permute));
//        }
//    }
//
//    public List<List<Integer>> permuteUnique(int[] nums) {
//        Integer[] permute = new Integer[nums.length];
//        solve(nums, permute, 0, 0, "");
//        return permutes;
//    }
//}

public class PermutationsII {
    public static void main(String[] args) {
        PermutationsIISolution p = new PermutationsIISolution();
        System.out.println(p.permuteUnique(new int[] {1, 1, 2}));
    }
}