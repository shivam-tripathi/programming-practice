package com.dsAlgo.array.order; /**
 * Given an array, print the Next Greater Element (NGE) for every element.
 * The Next greater Element for an element x is the first greater element
 * on the right side of x in array. Elements for which no greater element
 * exist, consider next greater element as -1.
 * <p>
 * Very similar problem: Find the nearest smaller numbers on left side in
 * an array: Given an array of integers, find the nearest smaller number
 * for every element such that the smaller element is on left side.
 */

import com.util.Pair;

import java.util.*;

class NGESolution {
  // Next greatest element using stack in O(n) time. In order using Pair class
  ArrayList<Integer> ngeStack(ArrayList<Integer> arr) {
    Stack<Pair<Integer, Integer>> stack = new Stack<>();
    int size = arr.size();
    ArrayList<Integer> nge = new ArrayList<>(arr);
    for (int i = 0; i < arr.size(); i++) {
      if (stack.empty()) {
        stack.push(new Pair<>(i, arr.get(i)));
      } else {
        while (!stack.empty()) {
          Pair<Integer, Integer> top = stack.peek();
          if (Integer.compare(top.second, arr.get(i)) >= 0) {
            break;
          } else {
            stack.pop();
            nge.set(top.first, arr.get(i));
          }
        }
        stack.push(new Pair<>(i, arr.get(i)));
      }
    }

    while (!stack.empty()) {
      Pair<Integer, Integer> item = stack.pop();
      nge.set(item.first, -1);
    }
    return nge;
  }

  // NGE using stack in O(n) without using Pair class
  // Process it in reverse order - instead of adding we start popping.
  // Very similar to above idea as in stack contains numbers in increasing
  // order. Because we are doing it in reverse order - we always find ans
  // before we insert the element.
  ArrayList<Integer> ngeStackInOrder(ArrayList<Integer> arr) {
    Stack<Integer> stack = new Stack<>();
    Integer[] nge = new Integer[arr.size()];

    for (int i = arr.size() - 1; i >= 0; i--) {
      int item = arr.get(i);
      int ngeItem = -1;
      while (!stack.empty()) {
        Integer top = stack.peek();
        if (Integer.compare(item, top) >= 0) {
          stack.pop();
        } else {
          ngeItem = top;
          break;
        }
      }
      nge[i] = ngeItem;
      stack.push(item);
    }

    return new ArrayList<Integer>(Arrays.asList(nge));
  }
}

public class NGE {
  public static void main(String[] args) {
    ArrayList<Integer> arr = new ArrayList<>();
    Random rand = new Random();
    int size = 10;

    for (int i = 0; i < size; i++) {
      arr.add(rand.nextInt());
      if (i > 0) {
        arr.add(arr.get(rand.nextInt(i)));
        i++;
      }
    }

    NGESolution sol = new NGESolution();
    for (Integer item : arr) System.out.print(item + " ");
    System.out.println();

    ArrayList<Integer> nge = sol.ngeStack(arr);
    ArrayList<Integer> ngeOrdered = sol.ngeStackInOrder(arr);

    for (int i = 0; i < arr.size(); i++) {
      System.out.println(arr.get(i) + " -> " + nge.get(i) + " " + ngeOrdered.get(i) + " " + nge.get(i).equals(ngeOrdered.get(i)));
    }
  }
}
