package com.leet.medium;

import java.util.*;

class SearchRotatedArrayII {
	private int rotatePoint = -1;
	int[] arr;

	private void setRotatePoint() {
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < arr[i-1]) {
				rotatePoint = i;
				return;
			}
		}
		rotatePoint = 0;
	}

	private int getIndex(int i) {
		return (i + rotatePoint) % arr.length;
	}

	private int getItem(int i) {
		return arr[getIndex(i)];
	}

    public boolean search(int[] nums, int target) {
    	arr = nums;
        setRotatePoint();
        int low = 0, high = nums.length - 1;
        while (low <= high) {
        	int mid = low + (high - low) / 2;
        	int item = getItem(mid);
        	if (item == target) {
        		return true;
        	} else if (item < target) {
        		low = mid + 1;
        	} else {
        		high = mid - 1;
        	}
        }
        return false;
    }
}

class Main {
	public static void main(String[] args) {
		int[] nums = new int[] {2,5,7,0,0,1,2};
		int[] clone = nums.clone();
		
		Arrays.sort(clone);
		for (Integer i: clone) System.out.print(i + " "); System.out.println();
		for (Integer i: nums) System.out.print(i + " "); System.out.println();

		SearchRotatedArrayII sol =  new SearchRotatedArrayII();
		for (int i = -1; i <= clone.length + 1; i++) {
			System.out.printf("%2d -> %s\n", i, sol.search(nums, i));	
		}
	}
}
