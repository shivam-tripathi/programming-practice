package com.dsAlgo.array;

import java.util.*;

class RotateMatrixSolution {
	void rotateMatrix(ArrayList<ArrayList<Integer>> arr) {
		int size = arr.size();
		for (int i = 0; i < size; i++) {
			for (int j = i; j < size - i - 1; j++) {
				int startX = i, startY = j;
				int x = startX, y = startY, item = arr.get(x).get(y);
				do {
					System.out.println(x + " " + y + " <- " + startX + " " + startY);
					int nextX = y, nextY = size - 1 - x;
					int nextItem = arr.get(nextX).get(nextY);
					arr.get(nextX).set(nextY, item);
					x = nextX;
					y = nextY;
					item = nextItem;
					// System.out.println(x + " " + y + " <- " + startX + " " + startY + " " +  (!(x == startX && y == startY)));
				} while (!(x == startX && y == startY));
				System.out.println("Done.");
			}
		}
	}

	void print(ArrayList<ArrayList<Integer>> arr) {
		int size = arr.size();
		System.out.println();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(arr.get(i).get(j) + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}

public class RotateMatrix {
	public static void main(String[] args) {
		RotateMatrixSolution solution = new RotateMatrixSolution();

		int size = 4;
		ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			ArrayList<Integer> internal = new ArrayList<>();
			arr.add(internal);
			for (int j = 0; j < size; j++) {
				arr.get(i).add(size * i + j);
			}
		}

		solution.print(arr);
		solution.rotateMatrix(arr);
		solution.print(arr);
	}
}