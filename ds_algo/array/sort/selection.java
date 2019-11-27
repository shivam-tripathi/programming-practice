/**
 * Selection sort:
 *      Time complexity: O(n^2)
 *      Space complexity: O(1)
 */

import java.util.*;

class Main {
	static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min_index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min_index] > arr[j]) {
                    min_index = j;
                }
            }
            if (min_index != i) {
                int temp = arr[i];
                arr[i] = arr[min_index];
                arr[min_index] = temp;
            }
        }
    }

	public static void main(String args[]) {
		int[] arr = {64, 25, 12, 22, 11};
        selectionSort(arr);
        for (int i = 0; i < arr.length; i++	) {
            System.out.println(arr[i]);
        }
    }
}
