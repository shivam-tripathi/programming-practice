function render(board: number[]): string[] {
	return board.map(row => {
		return ".".repeat(row) + "Q" + ".".repeat(board.length - row - 1);
	});
}

function isValid(board: number[], row: number, col: number): boolean {
	for (let i = 0; i < row; i++) {
		if (board[i] === col || (row - i) === Math.abs(col - board[i])) {
			return false;
		}
	}
	return true;
}

function solve(size: number, row: number, board: number[], ans: string[][]) {
	if (row >= size) {
		ans.push(render(board));
	} else {
		for (let col = 0; col < size; col++) {
			if (isValid(board, row, col)) {
				board[row] = col;
				solve(size, row + 1, board, ans);
			}
		}
	}
}

function solveNQueens(n: number): string[][] {
	const ans: string[][] = [];
	const board: number[] = [];
	solve(n, 0, board, ans);
	return ans;
};