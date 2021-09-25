package main

func rotateLayer(mat [][]int, offset int) {
	size := len(mat) - 2*offset
	v := func(val int) int {
		return val + offset
	}

	for i := 0; i < size-1; i++ {
		mat[v(0)][v(i)], mat[v(i)][v(size-1)], mat[v(size-1)][v(size-1-i)], mat[v(size-1-i)][v(0)] =
			mat[v(size-1-i)][v(0)], mat[v(0)][v(i)], mat[v(i)][v(size-1)], mat[v(size-1)][v(size-1-i)]
	}
}

func rotate(mat [][]int) {
	for offset := 0; offset < len(mat)/2; offset++ {
		rotateLayer(mat, offset)
	}
}
