package com.leet.hard;

public class ReversePairs {
	static class SegmentTree {
		int[] tree;

		SegmentTree(int[] arr) {
			int size = (int) Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2)) + 1);
			tree = new int[size];
			build(arr, 0, 0, arr.length - 1);
		}

		int build(int[] arr, int pos, int start, int end) {
			if (start > end) {
				return Integer.MIN_VALUE;
			}

			if (start == end) {
				tree[pos] = arr[start];
			} else {
				int mid = start + (end - start) / 2;
				int left = build(arr, 2 * pos + 1, start, mid);
				int right = build(arr, 2 * pos + 2, mid + 1, end);
				tree[pos] = Math.max(left, right);
			}

			return tree[pos];
		}

		int query(int pos, int greater, int l, int r, int ql, int qr) {
			if (l > r || ql > qr) {
				return 0;
			}

			if (l > qr || r < ql) {
				return 0;
			}

			int value = tree[pos] * 2;

			if (value < greater && l <= ql && qr <= r) {
				return r - l + 1;
			}

			int mid = l + (r - l) / 2;
			int left = query(2 * pos + 1, greater, l, mid, ql, qr);
			int right = query(2 * pos + 2, greater, mid + 1, r, ql, qr);

			return left + right;
		}
	}

	int[] merge(int[] arr, int[] ans, int left, int right) {
		if (left >= right) {
			return new int[] { arr[left] };
		}
		int mid = left + (right - left) / 2;
		int[] lres = merge(arr, ans, left, mid);
		int[] rres = merge(arr, ans, mid + 1, right);

		int[] merged = new int[right - left + 1];
		int lpos = left, rpos = mid + 1, pos = 0;
		while (lpos <= mid && rpos <= right) {
			if (lres[lpos - left] < rres[rpos - mid - 1]) {
				merged[pos++] = lres[lpos - left];
				ans[lpos - left] = pos;
				lpos++;
			}
		}
		return merged;
	}

	public int reversePairs(int[] nums) {
		return 0;
	}
}
