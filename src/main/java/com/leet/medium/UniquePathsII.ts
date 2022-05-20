function dfs(og: number[][], g: number[][], row: number, col: number) {
	// if out of bounds, there is no path
	if (row >= og.length || col >= og[row].length) {
		return 0;
	}

	// if obstacle, there is no path
	if (og[row][col] !== 0) {
		return 0;
	}

	// if destination, it is a new unique path
	if (row == og.length - 1 && col == og[og.length - 1].length - 1) {
		return 1;
	}

	// if already visited, return the value
	if (g[row][col] !== -1) {
		return g[row][col];
	}

	// if not visited, calculate the value
	g[row][col] = dfs(og, g, row + 1, col) + dfs(og, g, row, col + 1);
	return g[row][col];
}


function uniquePathsWithObstacles(og: number[][]): number {
	const g: number[][] = [];
	for (let i = 0; i < og.length; i++) g.push(new Array(og[i].length).fill(-1));
	return dfs(og, g, 0, 0);
};
