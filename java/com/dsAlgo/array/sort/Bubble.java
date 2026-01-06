package com.dsAlgo.array.sort; /**
 * Bubble sort:
 *      Time complexity: O(n^2)
 *      Space complexity: O(1)
 */

import java.util.*;

public class Bubble {
	static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
            boolean swapped = false;
			for (int j = 1; j < arr.length - i; j++) {
				if (arr[j-1] > arr[j]) {
					int temp = arr[j];
					arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }
	public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        bubbleSort(arr);
        for (int i = 0; i < arr.length; i++	) {
            System.out.println(arr[i]);
        }
    }
}
