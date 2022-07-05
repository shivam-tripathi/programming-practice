package com.leet.medium;

class Solution {
  void swap(int[] nums, int a, int b) {
    int tmp = nums[a];
    nums[a] = nums[b];
    nums[b] = tmp;
  }

  public void sortColors(int[] nums) {
    int zero = 0, two = nums.length - 1, ptr = 0;
    while (ptr <= two) {
      switch (nums[ptr]) {
      case 0:
        swap(nums, zero++, ptr++);
        break;
      case 1:
        ptr++;
        break;
      case 2:
        swap(nums, two--, ptr);
        break;
      }
    }
  }
}
