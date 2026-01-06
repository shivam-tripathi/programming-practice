package com.leet.medium;

import java.util.function.Function;
import java.util.stream.Collectors;

public class SortCharactersByFrequency {
	public String frequencySort(String s) {
		return s.chars().mapToObj(ch -> (char) ch)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.sorted((a, b) -> b.getValue().compareTo(a.getValue()))
				.map(a -> a.getKey().toString().repeat(Math.toIntExact(a.getValue()))).collect(Collectors.joining());
	}
}
