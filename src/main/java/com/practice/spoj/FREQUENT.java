package com.practice.spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class FREQUENT {
  static int n;
  static int q;
  static int[] arr;

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
      br = new BufferedReader(
              new InputStreamReader(System.in)
      );
    }

    public String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    public int nextInt() {
      return Integer.parseInt(next());
    }

    public String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }
  }

  static class Node {
    int leftElem;
    int leftElemFreq;
    int rightElem;
    int rightElemFreq;
    int maxFreqElem;
    int maxFreq;

    Node() {}
    Node(int le, int lef, int re, int ref, int me, int mef) {
      leftElem = le; rightElem = re; maxFreqElem = me; leftElemFreq = lef; rightElemFreq = ref; maxFreq = mef;
    }
  }

  static class SegmentTree {
    Node[] nodes;

    SegmentTree() {
      nodes = new Node[4 * arr.length];
      build();
    }

    public Node mergeNode(Node lnode, Node rnode) {
      // Node to save
      Node node = new Node();
      int maxFreq = 0, maxFreqElem = 0;
      if (lnode.rightElem == rnode.leftElem) {
        maxFreq = lnode.rightElemFreq + rnode.leftElemFreq;
        maxFreqElem = lnode.rightElem;
      }

      // Left elem freq
      int addToLeftFreq = 0;
      if (lnode.leftElem == lnode.rightElem && lnode.rightElem == rnode.leftElem) {
        addToLeftFreq = rnode.leftElemFreq;
      }
      node.leftElem = lnode.leftElem;
      node.leftElemFreq = lnode.leftElemFreq + addToLeftFreq;

      // Right elem freq
      int addToRightFreq = 0;
      if (rnode.leftElem == rnode.rightElem && rnode.leftElem == lnode.rightElem) {
        addToRightFreq = lnode.rightElemFreq;
      }
      node.rightElem = rnode.rightElem;
      node.rightElemFreq = rnode.rightElemFreq + addToRightFreq;

      if (maxFreq < lnode.maxFreq) {
        maxFreq = lnode.maxFreq;
        maxFreqElem = lnode.maxFreqElem;
      }
      if (maxFreq < rnode.maxFreq) {
        maxFreq = rnode.maxFreq;
        maxFreqElem = rnode.maxFreqElem;
      }
      if (maxFreq < node.leftElemFreq) {
        maxFreq = node.leftElemFreq;
        maxFreqElem = node.leftElem;
      }
      if (maxFreq < node.rightElemFreq) {
        maxFreq = node.rightElemFreq;
        maxFreqElem = node.rightElem;
      }
      node.maxFreqElem = maxFreqElem;
      node.maxFreq = maxFreq;

      return node;
    }

    public Node build(int l, int r, int pos) {
      if (l > r) return new Node();
      if (l == r) {
        Node node = new Node(arr[l], 1, arr[l], 1, arr[l], 1);
        nodes[pos] = node;
        return node;
      }
      int mid = l + (r - l) / 2;
      Node lnode = build(l, mid, pos * 2 + 1);
      Node rnode = build(mid + 1, r, pos * 2 + 2);
      nodes[pos] = mergeNode(lnode, rnode);
      return nodes[pos];
    }

    public void build() {
      build(0, arr.length - 1, 0);
    }

    Node query(int l, int r, int x, int y, int pos) {
      if (l > r || pos >= nodes.length ||  y < l || x > r) return new Node();
      if (x <= l && r <= y) return nodes[pos];
      int mid = l + (r - l) / 2;
      Node lnode = query(l, mid, x, y, pos * 2 + 1);
      Node rnode = query(mid + 1, r, x, y, pos * 2 + 2);
      return mergeNode(lnode, rnode);
    }

    Node query(int x, int y) {
      return query(0, arr.length - 1, x, y, 0);
    }
  }

  static void test() {
    arr  = new int[] {-2, -2, -1, -1, 0, 0, 1, 2, 5, 5, 7, 7, 7, 10, 10, 10};
    n = arr.length;
    var queries = List.of(
            new int[]{1, 3},
            new int[]{1, 10},
            new int[]{5, 10},
            new int[]{5, 9}
    );
    q = queries.size();
  }

  public static void alt(String[] args) throws IOException {
//    test();
    var obj = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      String[] inp = obj.readLine().trim().split(" ");
      if (inp.length == 1 && Integer.parseInt(inp[0]) == 0) break;
      n = Integer.parseInt(inp[0]);
      q = Integer.parseInt(inp[1]);
      arr = new int[n];
      inp = obj.readLine().trim().split(" ");
      for (int i = 0; i < n; i++) {
        arr[i] = Integer.parseInt(inp[i]);
      }
      var tree = new SegmentTree();
      for (int i = 0; i < q; i++) {
        inp = obj.readLine().trim().split(" ");
        System.out.println(tree.query(Integer.parseInt(inp[0]), Integer.parseInt(inp[1])));
      }
    }
  }

  public static void main(String[] args) {
    var sc = new FastReader();
    while (true) {
      int n = sc.nextInt();
      if (n == 0) break;
      int q = sc.nextInt();
      arr = new int[n];
      for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
      var seg = new SegmentTree();
      for (int i = 0; i < q; i++) {
        int x = sc.nextInt() - 1;
        int y = sc.nextInt() - 1;
        Node node = seg.query(x, y);
        System.out.println(node.maxFreq);
      }
    }
  }
}
