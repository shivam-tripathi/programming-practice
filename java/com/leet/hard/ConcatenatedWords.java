package com.leet.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.leet.common.Trie;
import com.leet.common.Trie.TrieNode;

public class ConcatenatedWords {
	Trie trie = new Trie();
	Map<Integer, Map<String, Integer>> dp = new HashMap<>();

	public int matchword(String word, int pos) {
		if (pos >= word.length()) {
			return 1;
		}
		if (dp.containsKey(pos) && dp.get(pos).containsKey(word)) {
			return dp.get(pos).get(word);
		}
		if (!dp.containsKey(pos)) {
			dp.put(pos, new HashMap<>());
		}
		TrieNode node = this.trie.getRoot();
		int ans = 1;
		for (int i = pos; i < word.length() - 1; i++) {
			char ch = word.charAt(i);
			if (!node.getSub().containsKey(ch)) {
				break;
			}
			node = node.getSub().get(ch);
			if (node.getLeaf()) {
				var match = matchword(word, i + 1);
				ans += match;
			}
			if (ans >= 1) {
				break;
			}
		}
		dp.get(pos).put(word, ans);
		return ans;
	}

	public List<String> findAllConcatenatedWordsInADict(String[] words) {
		this.trie = new Trie();

		trie.addWords(words);

		Arrays.sort(words, (a, b) -> {
			return Integer.compare(a.length(), b.length());
		});

		List<String> ans = new ArrayList<>();

		for (String word : words) {
			int subwords = matchword(word, 0);
			if (subwords > 1) {
				ans.add(word);
			}
		}

		return ans;
	}
}
