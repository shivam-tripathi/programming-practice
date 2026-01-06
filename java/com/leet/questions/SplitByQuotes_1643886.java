package com.leet.questions;

import java.util.ArrayList;
import java.util.List;

public class SplitByQuotes_1643886 {
	List<String> split(String str) {
		List<String> result = new ArrayList<>();
		int begin = 0, end = str.length() - 1;
		boolean hasQuote = false;
		char quote = '\'';
		StringBuilder sb = new StringBuilder();
		while (begin <= end) {
			if (str.charAt(begin) == ' ') {
				if (!hasQuote) {
					result.add(sb.toString());
					sb = new StringBuilder();
				}
			} else if (str.charAt(begin) == '\'') {
				hasQuote = !hasQuote;
				quote = '\'';
			} else if (str.charAt(begin) == '"') {
				hasQuote = !hasQuote;
			} else {
				sb.append(str.charAt(begin));
			}
		}
		return result;

	}

	public static void main(String[] args) {

	}
}
