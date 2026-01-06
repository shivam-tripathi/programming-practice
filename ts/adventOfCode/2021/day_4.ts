class BingoBoard {
  private rowsLeft: number[];
  private colsLeft: number[];
  private set: Set<number> = new Set();
  private map: Record<number, [number, number][]> = {};
  constructor(board: number[][]) {
    const rows = board.length, cols = board.length > 0 ? board[0].length : 0;
    this.rowsLeft = new Array(rows).fill(rows);
    this.colsLeft = new Array(cols).fill(cols);
    for (let i = 0; i < rows; i++) {
      for (let j = 0; j < cols; j++) {
        if (!this.map[board[i][j]]) this.map[board[i][j]] = [];
        this.set.add(board[i][j]);
        this.map[board[i][j]].push([i, j]);
      }
    }
  }

  mark(num: number): number {
    this.set.delete(num);
    for (const [i, j] of this.map[num] ?? []) {
      this.rowsLeft[i]--;
      this.colsLeft[j]--;
      if (this.rowsLeft[i] == 0 || this.colsLeft[j] == 0) {
        return num * [...this.set].reduce((acc, rem) => acc + rem, 0);
      }
    }
    return -1;
  }
}

export function bingo(boards: number[][][], nums: number[]) {
  const bingoBoards = boards.map((board) => new BingoBoard(board));
  for (const num of nums) {
    for (const board of bingoBoards) {
      const score = board.mark(num);
      if (score != -1) {
        return score;
      }
    }
  }
  return 0;
}

export function bingoWinLast(boards: number[][][], nums: number[]) {
  const set: Set<BingoBoard> = new Set(
    boards.map((board) => new BingoBoard(board)),
  );
  let last = 0;
  for (const num of nums) {
    for (const board of set) {
      last = board.mark(num);
      if (last != -1) {
        set.delete(board);
        if (set.size == 0) return last;
      }
    }
  }
  return -1;
}
