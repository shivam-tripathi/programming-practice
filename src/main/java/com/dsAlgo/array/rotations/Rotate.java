package com.dsAlgo.array.rotations;
/**
 * Juggles first gcd(arr.length, shift) numbers in cicular manner, O(n) O(1)
 */

import java.util.*;

class RotateSolution {
  int gcd(int a, int b) {
    if (a < b) {
      int temp = b;
      b = a;
      a = temp;
    }

    while (b > 0) {
      int temp = a % b;
      a = b;
      b = temp;
    }

    return a;
  }

  void rotate(ArrayList<Integer> arr, int d) {
    int size = arr.size();
    if (d % size != 0) {
      int times = gcd(size, d);
      for (int i = 0; i < times; i++) {
        int beginItem = arr.get(i);
        int prevIndex = i;
        int prevItem = arr.get(prevIndex);
        while (true) {
          int nextIndex = (prevIndex - d + size) % size;
          int nextItem = arr.get(nextIndex);

          arr.set(nextIndex, prevItem);

          prevItem = nextItem;
          prevIndex = nextIndex;

          if (prevItem == beginItem) {
            break;
          }
        }
      }
    }

    System.out.print("Rotated with " + d + ": ");
    for (int i = 0; i < size; i++) {
      System.out.print(arr.get(i) + " ");
    }
    System.out.println();
  }
}

public class Rotate {
  public static void main(String[] args) {
    RotateSolution solution = new RotateSolution();
    Random random = new Random();
    int size = 12;
    ArrayList<Integer> arr = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      // arr.add(random.nextInt(100));
      arr.add(i);
      System.out.print(arr.get(i) + " ");
    }
    System.out.println();

    for (int i = 0; i < 10; i++) {
      ArrayList<Integer> tempArr = new ArrayList<>(arr);
      solution.rotate(tempArr, i);
    }
  }
}

