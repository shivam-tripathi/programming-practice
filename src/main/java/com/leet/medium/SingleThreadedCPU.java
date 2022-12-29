package com.leet.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Task {
	int enqueue;
	int time;
	int index;

	Task(int enqueue, int time, int index) {
		this.enqueue = enqueue;
		this.time = time;
		this.index = index;
	}

	@Override
	public String toString() {
		return "{enqueue=" + this.enqueue + ",time=" + this.time + ",index=" + this.index + "}";
	}
}

public class SingleThreadedCPU {
	public int[] getOrder(int[][] inputs) {
		List<Task> tasks = new ArrayList<>();
		for (int i = 0; i < inputs.length; i++) {
			int enqueue = inputs[i][0], time = inputs[i][1];
			tasks.add(new Task(enqueue, time, i));
		}
		tasks.sort((a, b) -> a.enqueue - b.enqueue);
		Queue<Task> pq = new PriorityQueue<>((a, b) -> a.time != b.time ? a.time - b.time : a.index - b.index);
		int timer = 0, counter = 0;
		int done = 0, total = inputs.length;
		int[] ans = new int[total];
		while (done < total) {
			if (pq.size() == 0 && counter < total && timer < tasks.get(counter).enqueue) {
				timer = tasks.get(counter).enqueue;
			}
			while (counter < total && tasks.get(counter).enqueue <= timer) {
				pq.add(tasks.get(counter++));
			}
			Task task = pq.remove();
			ans[done++] = task.index;
			timer += task.time;
		}
		return ans;
	}
}
