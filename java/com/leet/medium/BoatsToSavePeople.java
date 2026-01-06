package com.leet.medium;

import java.util.Arrays;

/**
 * 881. Boats to Save People
 *
 * Medium
 *
 * https://leetcode.com/problems/boats-to-save-people/
 *
 * You are given an array people where people[i] is the weight of the ith
 * person, and an infinite number of boats where each boat can carry a maximum
 * weight of limit. Each boat carries at most two people at the same time,
 * provided the sum of the weight of those people is at most limit.
 *
 * Return the minimum number of boats to carry every given person.
 *
 * Example 1: Input: people = [1,2], limit = 3 Output: 1 Explanation: 1 boat (1,
 * 2)
 *
 * Example 2: Input: people = [3,2,2,1], limit = 3 Output: 3 Explanation: 3
 * boats (1, 2), (2) and (3)
 *
 * Example 3: Input: people = [3,5,3,4], limit = 5 Output: 4 Explanation: 4
 * boats (3), (3), (4), (5)
 *
 * Constraints: 1 <= people.length <= 5 * 104 1 <= people[i] <= limit <= 3 * 104
 */

public class BoatsToSavePeople {
  public int numRescueBoats(int[] people, int limit) {
    Arrays.sort(people);
    int low = 0, high = people.length - 1, count = 0;
    while (low <= high) {
      if (low == high) {
        count++;
        low++;
        high--;
      } else {
        int sum = people[low] + people[high];
        if (sum > limit) {
          count++;
          high--;
        } else {
          count++;
          low++;
          high--;
        }
      }
    }
    return count;
  }

  // Use bucket sort
  public int numRescueBoatsLinearTime(int[] people, int limit) {
    int[] bucket = new int[limit + 1];
    for (int p : people)
      bucket[p]++;
    int idx = 0;
    for (int w = 0; w < bucket.length; w++) {
      int count = bucket[w];
      while (count > 0) {
        people[idx++] = w;
        count--;
      }
    }

    int low = 0, high = people.length - 1, count = 0;
    while (low <= high) {
      if (low == high) {
        count++;
        low++;
        high--;
      } else {
        int sum = people[low] + people[high];
        if (sum > limit) {
          count++;
          high--;
        } else {
          count++;
          low++;
          high--;
        }
      }
    }
    return count;
  }

  public static void main(String[] args) {
    BoatsToSavePeople boatsToSavePeople = new BoatsToSavePeople();
    int[] people = new int[] { 3, 2, 2, 1 };
    int limit = 3;
    System.out.println(Arrays.toString(people));
    System.out.println(boatsToSavePeople.numRescueBoats(people, limit));
    System.out.println(boatsToSavePeople.numRescueBoatsLinearTime(people, limit));
  }
}
