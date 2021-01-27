package com.leet.hard;

import com.leet.common.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

public class VerticalOrderTraversalOfABinaryTree {
  static class ColumnItem implements Comparable<ColumnItem> {
    int val;
    int row;
    ColumnItem(int row, int val) {
      this.row = row;
      this.val = val;
    }
    @Override
    public int compareTo(ColumnItem o) {
      int cmp = Integer.compare(this.row, o.row);
      return cmp == 0 ? Integer.compare(this.val, o.val) : cmp;
    }

    @Override
    public String toString() {
      return "ColumnItem{" +
              "val=" + val +
              ", row=" + row +
              '}';
    }
  }

  public static void verticalTraversal(TreeNode node, int column, int row, Map<Integer, List<ColumnItem>> levels) {
    if (node == null) {
      return;
    }
    verticalTraversal(node.left, column - 1, row + 1, levels);
    if (!levels.containsKey(column)) {
      var l = new ArrayList<ColumnItem>();
      l.add(new ColumnItem(row, node.val));
      levels.put(column, l);
    } else {
      levels.get(column).add(new ColumnItem(row, node.val));
    }
    verticalTraversal(node.right, column + 1, row + 1, levels);
  }

  public static List<List<Integer>> verticalTraversal(TreeNode root) {
    Map<Integer, List<ColumnItem>> mapped = new TreeMap<>();
    List<List<Integer>> outLevels = new ArrayList<>();
    verticalTraversal(root, 0, 0, mapped);
    mapped.values().forEach(columnItems -> {
      outLevels.add(columnItems.stream().sorted().map(item -> item.val).collect(Collectors.toList()));
    });
    return outLevels;
  }

  public static void main(String[] args) {
    TreeNode root = TreeNode.construct(0, new Integer[]{1,2,3,4,6,5,7});
    System.out.println(verticalTraversal(root));
  }
}
