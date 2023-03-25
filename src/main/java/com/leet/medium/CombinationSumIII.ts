function solve(
	target: number,
	allowedLength: number,
	sum: number,
	start: number,
	cur: number[],
	size: number,
	ans: number[][]
): number[][] {
	if (size == allowedLength) {
		if (sum == target) ans.push([...cur]);
		return ans;
	}

	for (let i = start; i < 10; i++) {
		cur[size] = i;
		solve(target, allowedLength, sum + i, i + 1, cur, size + 1, ans);
	}

	return ans;
}

function combinationSum3(k: number, n: number): number[][] {
	return solve(n, k, 0, 1, new Array(k), 0, []);
};
