package com.leet.medium;

import java.util.*;

class DecodeStringSolution {
	public String decodeString(String s) {
		Stack<StringBuilder> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			// System.out.println("char="+c+";stack="+stack);
			if (c != ']') {
				stack.push(new StringBuilder().append(c));
			} else {
				StringBuilder sub = new StringBuilder(); // substring
				StringBuilder repeat = new StringBuilder(); // digit
				while (stack.size() != 0) {
					StringBuilder top = stack.pop();
					if (!"[".equals(top.toString())) {
						// get substring in braces
						sub.append(top);
					} else {
						// get Digit
						while (stack.size() != 0) {
							int dig = stack.peek().charAt(0) - '0';
							if (dig < 0 || dig > 9) {
								break;
							}
							repeat.append(dig);
							stack.pop();
						}
						break;
					}
				}
				int count = Integer.parseInt(repeat.reverse().toString()); // reverse and integer parse
				StringBuilder repeatedString = new StringBuilder();
				while (count > 0) {
					repeatedString.append(sub);
					count--;
				}
				stack.push(repeatedString);
			}
		}
		StringBuilder res = new StringBuilder();
		while (stack.size() != 0) {
			res.append(stack.pop());
		}
		return res.reverse().toString();
	}
}

public class DecodeString {
	public static void main(String[] args) {
		System.out.println(new DecodeStringSolution().decodeString("2[abc]3[cd]ef"));
	}
}