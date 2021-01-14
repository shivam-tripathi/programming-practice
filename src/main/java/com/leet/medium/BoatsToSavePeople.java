package com.leet.medium;

import java.util.Arrays;

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
    int[] bucket = new int[limit+1];
    for(int p: people) bucket[p]++;
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
    int[] people = new int[] {3, 2, 2, 1};
    int limit = 3;
    System.out.println(Arrays.toString(people));
    System.out.println(boatsToSavePeople.numRescueBoats(people, limit));
    System.out.println(boatsToSavePeople.numRescueBoatsLinearTime(people, limit));
  }
}
