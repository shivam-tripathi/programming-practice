/**
 * 79.
 * Word Search
 * https://leetcode.com/problems/word-search/
 * Medium
 *
 * Given an m x n grid of characters board and a string word, return true if
 * word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring. The same
 * letter cell may not be used more than once.
 *
 *
 *
 * Example 1:
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word
 * = "ABCCED" Output: true
 *
 * Example 2:
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word
 * = "SEE" Output: true
 *
 * Example 3:
 *
 * Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word
 * = "ABCB" Output: false
 *
 *
 *
 * Constraints:
 *
 * m == board.length n = board[i].length 1 <= m, n <= 6 1 <= word.length <= 15
 * board and word consists of only lowercase and uppercase English letters.
 *
 *
 *
 * Follow up: Could you use search pruning to make your solution faster with a
 * larger board?
 *
 */

class WordSearch {
  private rows: number;
  private cols: number;

  constructor(private board: string[][], private word: string) {
    this.rows = board.length;
    this.cols = board[0].length;
  }

  private solve(row: number, col: number, pos: number): boolean {
    if (pos >= this.word.length) {
      return true;
    }
    if (row < 0 || col < 0 || row >= this.rows || col >= this.cols) {
      return false;
    }
    if (this.board[row][col] != this.word[pos]) {
      return false;
    }
    const char = this.board[row][col];
    this.board[row][col] = "0";

    const res =
      this.solve(row + 1, col, pos + 1) ||
      this.solve(row - 1, col, pos + 1) ||
      this.solve(row, col + 1, pos + 1) ||
      this.solve(row, col - 1, pos + 1);

    this.board[row][col] = char;

    return res;
  }

  search(): boolean {
    for (let i = 0; i < this.rows; i++) {
      for (let j = 0; j < this.cols; j++) {
        if (this.solve(i, j, 0)) return true;
      }
    }
    return false;
  }
}

function exist(board: string[][], word: string): boolean {
  /**
   * Pruning
   * 1. If word characters are more than cells in the board, return false
   * 2. If character count in word is more than character count in the board, return false
   */
  if (word.length > board.length * board[0].length) {
    return false;
  }

  const boardCharCount = board.reduce((acc, row) => {
    row.forEach((char) => (acc[char] = acc[char] ? acc[char] + 1 : 1));
    return acc;
  }, {} as Record<string, number>);

  const wordCharCount = [...word].reduce((acc, char) => {
    acc[char] = acc[char] ? acc[char] + 1 : 1;
    return acc;
  }, {} as Record<string, number>);

  for (const [char, count] of Object.entries(wordCharCount)) {
    if (count > boardCharCount[char]) {
      return false;
    }
  }

  /** backtracking */
  const ws = new WordSearch(board, word);
  return ws.search();
}
