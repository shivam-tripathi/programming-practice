package com.leet.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RemoveBoxes {
	private Map<String, Integer> dp = new HashMap<>();

	private int solve(BitSet bits, int pos, int[] boxes) {
		if (pos >= boxes.length) {
			return 0;
		}

		System.out.println(pos + " " + Arrays.toString(boxes));

		// not valid index
		if (boxes[pos] == -1) {
			return solve(bits, pos + 1, boxes);
		}

		String key = bits.toString();

		if (dp.containsKey(key)) {
			return dp.get(key);
		}

		// do not remove this type
		int without = solve(bits, pos + 1, boxes);

		// remove this box
		int color = boxes[pos];
		int start = pos;
		List<Integer> converted = new ArrayList<>();
		int withOffset = 0;
		while (start >= 0 && (boxes[start] == color || boxes[start] == -1)) {
			if (boxes[start] == color) {
				withOffset += 1;
				boxes[start] = -1;
				converted.add(start);
				bits.set(start);
			}
			start--;
		}
		int end = pos;
		while (end < boxes.length && (boxes[end] == color || boxes[end] == -1)) {
			if (boxes[end] == color) {
				withOffset += 1;
				boxes[end] = -1;
				converted.add(end);
				bits.set(end);
			}
			end++;
		}

		int with = solve(bits, end, boxes);
		dp.put(key, Math.max(with + withOffset * withOffset, without));

		// reset
		for (int index : converted) {
			boxes[index] = color;
			bits.clear(index);
		}

		return dp.get(key);
	}

	public int removeBoxes(int[] boxes) {
		return solve(new BitSet(110), 0, boxes);
	}

	public static void main(String[] args) {
		int[] boxes = new int[] { 1, 2, 2, 1, 1, 1, 2, 1, 1, 2, 1, 2, 1, 1, 2, 2, 1, 1, 2, 2, 1, 1, 1, 2, 2, 2, 2, 1, 2, 1,
				1, 2, 2, 1, 2, 1, 2, 2, 2, 2, 2, 1, 2, 1, 2, 2, 1, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 1, 1, 1, 2, 2, 2, 2, 2, 1,
				2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 2, 1 };
		System.out.println(new RemoveBoxes().removeBoxes(boxes));
	}
}

// [1,2,2,1,1,1,2,1,1,2,1,2,1,1,2,2,1,1,2,2,1,1,1,2,2,2,2,1,2,1,1,2,2,1,2,1,2,2,2,2,2,1,2,1,2,2,1,1,1,2,2,1,2,1,2,2,1,2,1,1,1,2,2,2,2,2,1,2,2,2,2,2,1,1,1,1,1,2,2,2,2,2,1,1,1,1,2,2,1,1,1,1,1,1,1,2,1,2,2,1]
