package com.practice.spoj;

import java.util.Scanner;


public class GSS3 {
  static class SegmentTree {
    int[] tree;
    int[] arr;

    SegmentTree(int[] arr) {
      int size = 2 * (int) Math.pow(2, (int) Math.ceil(Math.log(arr.length) / Math.log(2)));
      tree = new int[size];
      this.arr = arr;
      construct(0, arr.length - 1, 0);
    }

    int construct(int left, int right, int idx) {
      if (idx >= arr.length || left > right) return Integer.MIN_VALUE;
      if (left == right) return arr[idx] = arr[left];
      int mid = left + (right - left) / 2;
      return arr[idx] = Math.max(
              construct(left, mid, 2 * idx + 1),
              construct(mid + 1, right, 2 * idx + 2)
      );
    }

    void modify(int pos, int val) {
      modify(0, arr.length - 1, pos, val, 0);
    }

    int modify(int left, int right, int pos, int val, int idx) {
      if (left > right) return Integer.MIN_VALUE;
      if (left == right && left == pos) return tree[idx] = val;
      int mid = left + (right - left) / 2;
      if (mid >= pos) {
        tree[idx] = Math.max(tree[idx], modify(left, mid, pos, val, 2 * idx + 1));
      } else {
        tree[idx] = Math.max(tree[idx], modify(mid + 1, right, pos, val, 2 * idx + 2));
      }
      return tree[idx];
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }
  }
}
