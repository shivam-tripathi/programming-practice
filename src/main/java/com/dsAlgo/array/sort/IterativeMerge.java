package com.dsAlgo.array.sort;
/**
 * Merge sort iterative. Stack store state: left, right, step (beginning, left done, right done)
 */

import java.util.*;

class IterativeMergeSolution {
    void iterativeMergeSort(ArrayList<Integer> arr) {
        int size = arr.size();
        class StackTrace {
            int begin; int end; int step;
            StackTrace(int f, int s) { this.begin = f; this.end = s; this.step = 0; }
        }

        Stack<StackTrace> stack = new Stack<>();
        stack.push(new StackTrace(0, size - 1));

        while(!stack.empty()) {
            StackTrace state = stack.peek();

            if (state.begin >= state.end) {
                stack.pop();
            } else if (state.step == 0) {
                state.step++;
                stack.push(new StackTrace(state.begin, (state.end + state.begin) / 2));
            } else if (state.step == 1) {
                state.step++;
                stack.push(new StackTrace((state.end + state.begin) / 2 + 1, state.end));
            } else {
                int lbegin = state.begin;
                int lend = (state.begin + state.end) / 2;

                int rbegin = lend + 1;
                int rend = state.end;

                ArrayList<Integer> temp = new ArrayList<>();
                while (lbegin <= lend && rbegin <= rend) {
                    if (arr.get(lbegin) < arr.get(rbegin)) {
                        temp.add(arr.get(lbegin++));
                    } else {
                        temp.add(arr.get(rbegin++));
                    }
                }

                while (lbegin <= lend) temp.add(arr.get(lbegin++));
                while (rbegin <= rend) temp.add(arr.get(rbegin++));

                for (int i = 0; i < temp.size(); i++) {
                    arr.set(i + state.begin, temp.get(i));
                }

                stack.pop();
            }
        }
    }
}


public class IterativeMerge {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();
        int size = 15;
        for (int i = 0; i < size; i++) {
            arr.add(random.nextInt(100) * (random.nextInt() < 0 ? -1 : 1));
        }

        for (Integer item: arr) System.out.print(item + " "); System.out.println();
        (new IterativeMergeSolution()).iterativeMergeSort(arr);
        for (Integer item: arr) System.out.print(item + " "); System.out.println();
    }
}