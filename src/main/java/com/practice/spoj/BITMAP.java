package com.practice.spoj;

import java.io.*;
import java.util.*;
import java.lang.*;

public class BITMAP {
  static int rows;
  static int cols;
  static boolean[][] state;
  static int[][] dist;

  static void solve(ArrayList<Integer> sourcesArr) {
    class Node {
      final int x;
      final int y;
      final int dist;

      Node(int i, int j, int d) {
        x = i;
        y = j;
        dist = d;
      }
    }

    Queue<Node> queue = new LinkedList<Node>();
    for (int i = 0; i < sourcesArr.size(); i++) {
      int y = sourcesArr.get(i++);
      int x = sourcesArr.get(i);
      dist[y][x] = 0;
      queue.add(new Node(x, y, 0));
    }

    while (!queue.isEmpty()) {
      Node node = queue.peek();
      if (node.x - 1 >= 0 && dist[node.y][node.x - 1] == -1) {
        dist[node.y][node.x - 1] = node.dist + 1;
        queue.add(new Node(node.x - 1, node.y, node.dist + 1));
      }
      if (node.x + 1 < cols && dist[node.y][node.x + 1] == -1) {
        dist[node.y][node.x + 1] = node.dist + 1;
        queue.add(new Node(node.x + 1, node.y, node.dist + 1));
      }
      if (node.y - 1 >= 0 && dist[node.y - 1][node.x] == -1) {
        dist[node.y - 1][node.x] = node.dist + 1;
        queue.add(new Node(node.x, node.y - 1, node.dist + 1));
      }
      if (node.y + 1 < rows && dist[node.y + 1][node.x] == -1) {
        dist[node.y + 1][node.x] = node.dist + 1;
        queue.add(new Node(node.x, node.y + 1, node.dist + 1));
      }
      queue.remove();
    }
  }

  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    while (t-- > 0) {
      rows = sc.nextInt();
      cols = sc.nextInt();
      state = new boolean[rows][cols];
      dist = new int[rows][cols];

      ArrayList<Integer> ones = new ArrayList<>();
      for (int i = 0; i < rows; i++) {
        String row = sc.nextLine();
        while (row.equals("") || row == null) {
          row = sc.nextLine();
        }

        int col = 0;
        for (int j = 0; j < row.length(); j++) {
          if (row.charAt(j) == '0') {
            state[i][col] = false;
          } else if (row.charAt(j) == '1') {
            state[i][col] = true;
            ones.add(i);
            ones.add(col);
          } else {
            continue;
          }
          dist[i][col++] = -1;
        }
      }

      solve(ones);

      for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
          System.out.printf("%d ", dist[i][j]);
        }
        System.out.printf("%n");
      }
    }
    sc.close();
  }
}
