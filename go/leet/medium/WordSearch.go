package main

type WordSearch struct {
	board [][]byte
	word  string
	rows  int
	cols  int
}

func NewWordSearch(board [][]byte, word string) *WordSearch {
	return &WordSearch{
		board,
		word,
		len(board),
		len(board[0]),
	}
}

func (this *WordSearch) solve(row, col, pos int) bool {
	if pos == len(this.word) {
		return true
	}

	if row >= this.rows || col >= this.cols || row < 0 || col < 0 || this.board[row][col] != this.word[pos] {
		return false
	}

	char := this.board[row][col]
	this.board[row][col] = '0'

	var res bool
	res = this.solve(row+1, col, pos+1)
	res = res || this.solve(row-1, col, pos+1)
	res = res || this.solve(row, col+1, pos+1)
	res = res || this.solve(row, col-1, pos+1)

	this.board[row][col] = char
	return res
}

func (this *WordSearch) search() bool {
	for i := 0; i < this.rows; i++ {
		for j := 0; j < this.cols; j++ {
			if this.solve(i, j, 0) {
				return true
			}
		}
	}

	return false
}

func exist(board [][]byte, word string) bool {
	ws := NewWordSearch(board, word)
	return ws.search()
}
