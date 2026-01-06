package com.dsAlgo.strings;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class AllBinaryStringsSolution {
  public void recursionBasedUtil(String s, int sizeLimit) {
    if (s.length() == sizeLimit) {
      System.out.println(s);
      return;
    }

    recursionBasedUtil(s + '0', sizeLimit);
    recursionBasedUtil(s + '1', sizeLimit);
  }

  public void recursionBased(int wildCount) {
    recursionBasedUtil("", wildCount);
  }

  public void queueBased(int wildCount) {
    Queue<String> queue = new LinkedList<String>();
    queue.add("");

    while (!queue.isEmpty()) {
      String sb = queue.remove();
      if (sb.length() == wildCount) {
        System.out.println(sb);
        continue;
      }

      queue.add(sb + "0");
      queue.add(sb + "1");
    }
  }

  public void stackBased(int wildCount) {
    class Item {
      String str;
      int action;

      Item(String str, int action) {
        this.str = str;
        this.action = action;
      }
    }

    Stack<Item> stack = new Stack<>();
    stack.push(new Item("", 0));

    while (!stack.isEmpty()) {
      Item item = stack.pop();
      if (item.str.length() == wildCount) {
        System.out.println(item.str);
        continue;
      }

      if (item.action == 0) {
        item.action = 1;
        stack.push(item);
        stack.push(new Item(item.str + '0', 0));
      } else if (item.action == 1) {
        item.action = 2;
        stack.push(item);
        stack.push(new Item(item.str + '1', 0));
      }
    }

  }
}

public class AllBinaryStrings {
  public static void main(String[] args) {
    AllBinaryStringsSolution solution = new AllBinaryStringsSolution();
    solution.recursionBased(4);
    System.out.println("---");
    solution.queueBased(4);
    System.out.println("---");
    solution.stackBased(4);
  }
}