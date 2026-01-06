function eval(grid: number[][], r: number, c: number): number {
	if (r >= grid.length || c >= grid[0].length) {
			return -1;
	}
	const right = eval(grid, r, c+1);
	const left = eval(grid, r+1, c);

	let ans = 0;
	if (left > ans) {
		ans = left;
	}
	if (right > ans) {
		ans = right;
	}

	return ans;
}

function gridGame(grid: number[][]): number {

};
