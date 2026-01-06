package com.leet.easy;

import java.util.List;
import java.util.stream.Collectors;

class Main {
  public static void main(String[] args) {
    List<Float> ans = List.of(10, 20, 5, 30, 45, 10).stream().map(score -> (float) score).collect(Collectors.toList());
    System.out.println(ans);
  }
}