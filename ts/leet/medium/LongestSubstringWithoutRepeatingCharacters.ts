
function lengthOfLongestSubstring(s: string): number {
	if (s.length === 0) return 0;

	const chars: { [_: string]: boolean } = { [s[0]]: true };
	const occurrences: { [_: string]: { cur: number; positions: number[] } } = {
		[s[0]]: { cur: 0, positions: [0] },
	};

	let size = 1, ans = 1, start = 0;

	for (let i = 1; i < s.length; i++) {
		const ch = s[i];
		occurrences[ch] = occurrences[ch] || { cur: 0, positions: [] };
		occurrences[ch].positions.push(i);

		if (!chars[ch]) {
			chars[ch] = true;
			size++;
		} else {
			ans = Math.max(ans, size);
			const jump = occurrences[s[start]].positions[occurrences[s[start]].cur++];
			// while (chars[ch] && start < i) {
			// 	chars[s[start]] = false;
			// 	start++;
			// 	size--;
			// }
			size -= jump - start;
			start = jump + 1;
			chars[ch] = true;
			// size++;
		}
	}

	ans = Math.max(ans, size);

	return ans;
};
