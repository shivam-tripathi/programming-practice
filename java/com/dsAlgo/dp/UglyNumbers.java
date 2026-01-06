package com.dsAlgo.dp;

/*
Ugly numbers are numbers whose only prime factors are 2, 3 or 5.
The sequence 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15... shows the first 11 ugly numbers. By convention, 1 is included.
Given a number n, the task is to find n’th Ugly number.
 */

import java.util.*;

public class UglyNumbers {
	List<Integer> list = new ArrayList<>(Collections.singletonList(1));
	Map<Integer, Integer> indexMap = new HashMap<>();

	int getNumber(int n) {
		if (n < list.size()) {
			return list.get(n - 1);
		}


		while (list.size() < n) {
			List<Integer> multiples = Arrays.asList(
							2 * list.get(indexMap.getOrDefault(2, 0)),
							3 * list.get(indexMap.getOrDefault(3, 0)),
							5 * list.get(indexMap.getOrDefault(5, 0))
			);
			Integer toAdd = Integer.MAX_VALUE;
			int index = -1;
			for (int i = 0; i < multiples.size(); i++) {
				if (Integer.compare(multiples.get(i), toAdd) == -1) {
					toAdd = multiples.get(i);
					index = i == 0 ? 2 : (i == 1 ? 3 : 5);
				}
			}

			indexMap.merge(index, 1, Integer::sum);
			if (!list.get(list.size() - 1).equals(toAdd)) {
				list.add(toAdd);
			}
		}

		System.out.println(list);
		return list.get(n - 1);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int limit = sc.nextInt();
		System.out.println(">> " + new UglyNumbers().getNumber(limit));
	}
}
