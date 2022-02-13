function solve(builder: string[], pos: number, open: number, close: number, ans: string[]) {
	if (pos >= builder.length) {
		ans.push(builder.join(''));
		return ans;
	}

	// if there any unclosed brackets
	if (close < open) {
		builder[pos] = ')';
		solve(builder, pos + 1, open, close + 1, ans);
	}

	// If we can still open a new pair (total number of pairs = half the size of array)
	if (open < (builder.length / 2)) {
		builder[pos] = '(';
		solve(builder, pos + 1, open + 1, close, ans);
	}
	return ans;
}

function generateParenthesis(n: number): string[] {
	return solve(new Array(n * 2), 0, 0, 0, []);
};