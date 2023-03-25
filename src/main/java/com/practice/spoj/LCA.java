package com.practice.spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LCA {
  static int[] parents;

  static void markParents(List<List<Integer>> adjList) {
    parents[1] = 1;
    for (int i = 1; i < adjList.size(); i++) {
      for (int j = 1; j < adjList.get(i).size(); j++) {
        parents[adjList.get(i).get(j)] = i;
      }
    }
  }

  static int[][] dp;
  static int[] levels;
  static int height;
  static List<List<Integer>> tree;

  static void buildDP(int index, int level, int parent) {
    if (index > tree.size()) return;
    dp[index][0] = parent;
    levels[index] = level;
    for (int j = 1; j <= height; j++) {
      dp[index][j] = dp[dp[index][j - 1]][j - 1];
    }

    for (int j : tree.get(index)) {
      buildDP(j, level + 1, index);
    }
  }

  static void buildDP(List<List<Integer>> adjList) {
    buildDP(1, 0, 1);
  }

  static void lca(int a, int b) {
    if (levels[a] > levels[b]) {
      int tmp = b;
      b = a;
      a = tmp;
    }

    int i = height;
    while (i > 0 && levels[dp[a][i]] >= levels[b]) {
      i++;
    }
    a = dp[a][i-1];
  }

//  static int lca(int a, int b) {
//    boolean[] trace = new boolean[parents.length + 1];
//    while (true) {
//      if (a == b) return a;
//      if (a != 1 && trace[a]) return a;
//      if (b != 1 && trace[b]) return b;
//      trace[a] = trace[b] = true;
//      a = parents[a];
//      b = parents[b];
//    }
//  }

  public static void run() throws IOException {
    var obj = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(obj.readLine());
    for (int _t = 0; _t < t; _t++) {
      System.out.printf("Case %d:\n", _t + 1);
      int nodeCount = Integer.parseInt(obj.readLine());
      var adjList = new ArrayList<List<Integer>>();
      adjList.add(List.of(0));
      for (int i = 0; i < nodeCount; i++) {
        var inp = obj.readLine().split(" ");
        var lis = new ArrayList<Integer>();
        for (String s : inp) lis.add(Integer.parseInt(s));
        adjList.add(lis);
      }
      parents = new int[nodeCount + 1];
      markParents(adjList);
      int q = Integer.parseInt(obj.readLine());
      for (int i = 0; i < q; i++) {
        var inp = obj.readLine().split(" ");
        int a = Integer.parseInt(inp[0]), b = Integer.parseInt(inp[1]);
//        System.out.println(lca(a, b));
      }
    }
  }

  public static void test() {
    int nodeCount = 7;
    parents = new int[nodeCount + 1];
    var adjList = List.of(
            List.of(0), // dummy
            List.of(3, 2, 3, 4),
            List.of(0),
            List.of(3, 5, 6, 7),
            List.of(0),
            List.of(0),
            List.of(0),
            List.of(0)
    );
    markParents(adjList);
    for (var lis : List.of(List.of(5, 7), List.of(2, 7))) {
//      System.out.println("LCA: " + lis.get(0) + "," + lis.get(1) + "=" + lca(lis.get(0), lis.get(1)));
    }
  }

  public static void main(String[] args) throws IOException {
//    test();
    run();
  }
}
