package com.leet.medium;

import java.util.PriorityQueue;

/**
 * 1642.
 *
 * Furthest Building You Can Reach Medium
 *
 * https://leetcode.com/problems/furthest-building-you-can-reach/
 *
 * You are given an integer array heights representing the heights of buildings,
 * some bricks, and some ladders.
 *
 * You start your journey from building 0 and move to the next building by
 * possibly using bricks or ladders.
 *
 * While moving from building i to building i+1 (0-indexed),
 *
 * If the current building's height is greater than or equal to the next
 * building's height, you do not need a ladder or bricks. If the current
 * building's height is less than the next building's height, you can either use
 * one ladder or (h[i+1] - h[i]) bricks.
 *
 * Return the furthest building index (0-indexed) you can reach if you use the
 * given ladders and bricks optimally.
 *
 * Example 1:
 *
 * Input: heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1 Output: 4
 * Explanation: Starting at building 0, you can follow these steps: - Go to
 * building 1 without using ladders nor bricks since 4 >= 2. - Go to building 2
 * using 5 bricks. You must use either bricks or ladders because 2 < 7. - Go to
 * building 3 without using ladders nor bricks since 7 >= 6. - Go to building 4
 * using your only ladder. You must use either bricks or ladders because 6 < 9.
 * It is impossible to go beyond building 4 because you do not have any more
 * bricks or ladders.
 *
 * Example 2: Input: heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
 * Output: 7
 *
 * Example 3: Input: heights = [14,3,19,3], bricks = 17, ladders = 0 Output: 3
 *
 * Constraints: 1 <= heights.length <= 105 1 <= heights[i] <= 106 0 <= bricks <=
 * 109 0 <= ladders <= heights.length
 */

public class FurthestBuildingYouCanReach {
	static class Step {
		int index;
		int diff;

		Step(int index, int diff) {
			this.index = index;
			this.diff = diff;
		}

		public String toString() {
			return "{index=" + this.index + ",diff=" + this.diff + "}";
		}
	}

	// Ideally we want to use ladders only for buildings with maximum height
	// difference. If we encounter any building with a difference greater than
	// what we have already encountered, we will move ladder to the current
	// building and instead try to use bricks for the previous building. In case
	// the current building is with shorter difference than all the previous ones
	// then we try to use bricks with the current building. In case we do not have
	// enough bricks, we abort.
	public int furthestBuilding(int[] heights, int bricks, int ladders) {
		PriorityQueue<Step> pq = new PriorityQueue<>((a, b) -> {
			return a.diff - b.diff;
		});

		int idx = 1;
		while (idx < heights.length && ladders > 0) {
			int diff = heights[idx] - heights[idx - 1];
			if (diff > 0) {
				pq.add(new Step(idx, diff));
				ladders--;
			}
			idx++;
		}

		while (idx < heights.length) {
			int diff = heights[idx] - heights[idx - 1];
			if (diff > 0) {
				int toDiff = pq.size() > 0 ? Math.min(diff, pq.peek().diff) : diff;
				if (bricks < toDiff)
					break;
				bricks -= toDiff;
				if (pq.size() > 0 && toDiff == pq.peek().diff) {
					pq.remove();
					pq.add(new Step(idx, diff));
				}
			}
			idx++;
		}

		return idx - 1;
	}
}
