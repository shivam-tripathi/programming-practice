package com.leet.medium;

import com.util.Pair;

import java.util.*;

public class FourSum {
  public static List<List<Integer>> kSum(int[] nums, int target, int k, int index) {
    List<List<Integer>> ans = new ArrayList<>();
    // two sum
    if (k == 2) {
      int low = index, high = nums.length - 1;
      while (low < high) {
        int sum = nums[low] + nums[high];
        if (target == sum) {
          List<Integer> temp = new ArrayList<>();
          temp.add(nums[low]);
          temp.add(nums[high]);
          ans.add(temp);
          // skip duplication
          while (low < high && nums[low] == nums[low+1]) {
            low++;
          }
          while (low < high && nums[high] == nums[high-1]) {
            high--;
          }
          low++;
          high--;
        } else if (target > sum) {
          low++;
        } else {
          high--;
        }
      }
    } else {
      for (int i = index; i < nums.length; i++) {
        List<List<Integer>> list = kSum(nums, target - nums[i], k - 1, i + 1);
        for (List<Integer> item: list) {
          item.add(0, nums[i]);
        }
        ans.addAll(list);
        while (i < nums.length - 1 && nums[i] == nums[i+1]) {
          i++;
        }
      }
    }
    return ans;
  }

  public static List<List<Integer>> fourSum(int[] nums, int target) {
    Arrays.sort(nums);
    return kSum(nums, target, 4, 0);
  }

  public static void main(String[] args) {
    try {
      int[] ints = new int[]{1, 0, -1, 0, -2, 2};
      System.out.println(fourSum(ints, 0));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

// https://fastdl.mongodb.org/linux/mongodb-linux-x86_64-ubuntu1604-4.4.2.tgz
