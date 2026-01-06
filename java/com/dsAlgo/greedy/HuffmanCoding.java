package com.dsAlgo.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonDeserialize
class HuffmanNode {
	public String key;
	public int frequency;
	public HuffmanNode left, right;

	HuffmanNode(String s, int frequency) {
		this.key = s;
		this.frequency = frequency;
	}

	HuffmanNode(String s, int frequency, HuffmanNode left, HuffmanNode right) {
		this.key = s;
		this.frequency = frequency;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}

class HuffmanCoding {
	public static HuffmanNode buildHuffmanTree(Map<Character, Integer> frequency) {
		PriorityQueue<HuffmanNode> pq = new PriorityQueue<>((a, b) -> a.frequency - b.frequency);
		for (Map.Entry<Character, Integer> entry : frequency.entrySet()) {
			pq.add(new HuffmanNode(String.valueOf(entry.getKey()), entry.getValue()));
		}

		while (pq.size() > 1) {
			HuffmanNode left = pq.poll();
			HuffmanNode right = pq.poll();
			HuffmanNode parent = new HuffmanNode(left.key + right.key, left.frequency + right.frequency, left, right);
			pq.add(parent);
		}

		return pq.poll();
	}

	public static void main(String[] args) {
		System.out.println("Enter String");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		scanner.close();
		Map<Character, Integer> frequency = new HashMap<>();
		for (char c : input.toCharArray()) {
			frequency.put(c, frequency.getOrDefault(c, 0) + 1);
		}
		HuffmanNode root = HuffmanCoding.buildHuffmanTree(frequency);
		System.out.println(root.toString());
	}
}
