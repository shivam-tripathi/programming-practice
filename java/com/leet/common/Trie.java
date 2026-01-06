package com.leet.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
	public static class TrieNode {
		boolean leaf;
		int count;
		Map<Character, TrieNode> sub = new HashMap<>();

		public void setLeaf(boolean leaf) {
			this.leaf = leaf;
		}

		public boolean getLeaf() {
			return leaf;
		}

		public Map<Character, TrieNode> getSub() {
			return this.sub;
		}
	}

	private TrieNode root = new TrieNode();

	public TrieNode getRoot() {
		return root;
	}

	public void addWord(String word) {
		TrieNode node = root;
		for (var ch : word.toCharArray()) {
			if (node.sub.get(ch) == null) {
				node.sub.put(ch, new TrieNode());
			}
			node = node.sub.get(ch);
		}
		node.setLeaf(true);
	}

	public void addWordList(List<String> words) {
		words.forEach(word -> this.addWord(word));
	}

	public void addWords(String[] words) {
		for (String word : words) {
			this.addWord(word);
		}
	}
}
