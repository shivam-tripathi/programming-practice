package com.leet.medium;

/**
 * Container With Most Water
 * https://leetcode.com/problems/container-with-most-water/
 *
 * Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of the line i is at (i, ai) and (i, 0). Find two lines, which, together with the x-axis forms a container, such that the container contains the most water.
 *
 * Notice that you may not slant the container.
 *
 * Example 1:
 *
 * Input: height = [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of
 * water (blue section) the container can contain is 49.
 *
 * Example 2:
 *
 * Input: height = [1,1]
 * Output: 1
 *
 * Example 3:
 *
 * Input: height = [4,3,2,1,4]
 * Output: 16
 *
 * Example 4:
 *
 * Input: height = [1,2,1]
 * Output: 2
 *
 * Constraints:
 *
 *     n == height.length
 *     2 <= n <= 3 * 104
 *     0 <= height[i] <= 3 * 104
 */

public class ContainerWithMostWater {
  public int maxArea(int[] height) {
    int ans = 0;
    int left = 0, right = height.length - 1;
    while (left < right) {
      ans = Math.max(ans, Math.min(height[left], height[right]) * (right - left));
      if (height[left] < height[right]) {
        left++;
      } else {
        right--;
      }
    }
    return ans;
  }
}

/**
 * 1. Consider two pointers l and r.
 * 2. Area = min(h[l], h[r]) * (r - l)
 * 3. Assume h[l] < h[r] (we can do same process for reverse as well)
 * 4. Now if any height p exists between l and r which can possibly form a solution with l or r can either be:
 *    case 1. h[p] <= h[l] <= h[r]
 *    case 2. h[l] <= h[p] <= h[r]
 *    case 3. h[l] <= h[r] <= h[p]
 * 5. In case 1, area by (l, p) and area by (p, r) will always be less than area by (l, r) as not only height at p is
 * less than l (and if it's equal, width is less than r-l).
 * 6. In case 2, area by (l, p) is less than area by (l, r) as height for the area remains h[l] (it is the minimum in
 * both pairs), but the width decreases. Area between p and r can be a solution, but as we shift the minimum of two,
 * we will reach p from l side anyways (if it presents optimal solution).
 * 7. In case 3, area by r and p can be solution, but as h[p] >= h[l], we will move pointer to eventually. Area between
 * l and p will not be better off than l and r, as width decreases but min height remains same.
 */
