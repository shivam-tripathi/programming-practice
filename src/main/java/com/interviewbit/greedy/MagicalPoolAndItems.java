package com.interviewbit.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class MagicalPoolAndItems {
	int MOD = ((int) 1e9 + 7);

	public int solve(ArrayList<ArrayList<Integer>> A) {
		Map<Integer, List<Integer>> items = new HashMap<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b.compareTo(a));

		int ans = 0;

		A.forEach(item -> {
			int quality = item.get(0), quantity = item.get(1);
			List<Integer> list = items.getOrDefault(quality, new ArrayList<>());
			list.add(quantity);
			items.put(quality, list);
			pq.add(item.get(0));
		});

		while (!pq.isEmpty()) {
			int curQuality = pq.peek();
			pq.poll();

			if (items.get(curQuality).size() > 1) {
				int newQuality = curQuality / 2;
				if (newQuality != 0) {
					if (!items.containsKey(newQuality)) {
						pq.offer(newQuality);
						items.put(newQuality, new ArrayList<>());
					}
					items.get(newQuality).add(((curQuality) % MOD * 2) % MOD);
				}
			} else {
				System.out.println("Else:" + items.get(curQuality));
				ans = (int) (ans + items.get(curQuality).get(0)) % MOD;
			}
		}

		return ans;
	}
}
