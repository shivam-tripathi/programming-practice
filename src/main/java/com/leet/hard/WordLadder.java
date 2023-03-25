package com.leet.hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadder {
	// Apply bfs - skip visited words and transform character by character and
	// move when
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> set = new HashSet<>();
		set.addAll(wordList);

		if (!set.contains(endWord) || beginWord.length() != endWord.length()) {
			return 0;
		}

		List<String> cur = new ArrayList<>();
		Set<String> visited = new HashSet<>();

		cur.add(beginWord);
		visited.add(beginWord);
		int count = 1;

		while (cur.size() > 0) {
			List<String> next = new ArrayList<>();
			for (String word : cur) {
				if (word.equals(endWord)) {
					return count;
				}

				char[] chars = word.toCharArray();
				for (int i = 0; i < chars.length; i++) {
					var original = chars[i];
					for (char ch = 'a'; ch <= 'z'; ch++) {
						chars[i] = ch;
						String nextWord = new String(chars);
						if (set.contains(nextWord) && !visited.contains(nextWord)) {
							next.add(nextWord);
							visited.add(nextWord);
						}
					}
					chars[i] = original;
				}
			}
			count++;
			cur = next;
		}

		return 0;
	}

}
