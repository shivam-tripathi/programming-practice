/**
 * Positive negative rearragement (one side and alternate) with O(1) space and ordered.
 */

import java.util.*;

class Solution {
    // Use insertion sort based approach but keep the order intact O(n^2)
    void insertionBased(ArrayList<Integer> arr) {
        int size = arr.size(), i, j;
        for (i = 1; i < size; i++) {
            int item = arr.get(i);
            if (item < 0) {
                for (j = i - 1; j >= 0 && arr.get(j) >= 0; j--) {
                    arr.set(j + 1, arr.get(j));
                }
                arr.set(j + 1, item);
            }
            if (item == 0) {
                for (j = i - 1; j >= 0 && arr.get(j) > 0; j--) {
                    arr.set(j + 1, arr.get(j));
                }
                arr.set(j + 1, item);
            }
        }
    }

    // Iterative merge (just for practice)
    void mergeBased(ArrayList<Integer> arr) {
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
                    if (arr.get(lbegin) < 0) {
                        temp.add(arr.get(lbegin++));
                    } else if (arr.get(rbegin) < 0) {
                        temp.add(arr.get(rbegin++));
                    } else if (arr.get(lbegin) == 0) {
                        temp.add(arr.get(lbegin++));
                    } else if (arr.get(rbegin) == 0) {
                        temp.add(arr.get(rbegin++));
                    } else {
                        temp.add(arr.get(lbegin++));
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

class Main {
    public static void main(String[] args) {
        // int[] a = {0, -38, -17, -58, -48, -92, -69, 71, 96, 95, -12};
        ArrayList<Integer> arr = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 12; i++) {
            arr.add(random.nextInt(100) * (random.nextBoolean() ? -1 : 1) * (random.nextInt(50)/10 == 0 ? 0 : 1));
        }

        for (Integer item: arr) System.out.print(item + " "); System.out.println();

        Solution solution = new Solution();
        ArrayList<Integer> insertionArr = new ArrayList<>(arr);
        solution.insertionBased(insertionArr);
        for (Integer item: insertionArr) System.out.print(item + " "); System.out.println();
        ArrayList<Integer> mergeArr = new ArrayList<>(arr);
        solution.mergeBased(mergeArr);
        for (Integer item: mergeArr) System.out.print(item + " "); System.out.println();
    }
}