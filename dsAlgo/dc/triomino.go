package main

import (
	"fmt"
	"math"
)

type Cell struct {
	x int
	y int
}

type SubMatrix struct {
	matrix [][] int
	center Cell
}

var count int = 1

func printMatrix(matrix [][]int) {
	colors := [...] string {"\x1b[41m", "\x1b[42m", "\x1b[43m", "\x1b[44m", "\x1b[45m", "\x1b[46m", "\x1b[47m"}
	for i:= 0; i < len(matrix); i++ {
		for j:=0; j < len(matrix[i]); j++ {
			value := matrix[i][j]
			index := value % len(colors)
			color := "\x1b[40m"
			if (index >= 0) {
				color = colors[index]
			}
			fmt.Printf("%s\x1b[30m %3d \x1b[0m", color, value)
		}
		fmt.Println()
	}
}


func fillAndSolve(
	solveMatrix SubMatrix,
	fillMatrix1 SubMatrix,
	fillMatrix2 SubMatrix,
	fillMatrix3 SubMatrix,
	missingCell Cell,
) {
	// Fill the center of all matrices which do not have missing cell
	fillMatrix1.matrix[fillMatrix1.center.x][fillMatrix1.center.y] = count
	fillMatrix2.matrix[fillMatrix2.center.x][fillMatrix2.center.y] = count
	fillMatrix3.matrix[fillMatrix3.center.x][fillMatrix3.center.y] = count
	count++

	solveSubmatrix := func(matrix [][]int, cell Cell) {
		if (len(solveMatrix.matrix) == 2) {
			for i := 0; i < len(matrix); i++ {
				for j := 0; j < len(matrix[i]); j++ {
					if i != cell.x || j != cell.y {
						matrix[i][j] = count
					}
				}
			}
			count++
		} else {
			solve(matrix, cell)
		}
	}

	solveSubmatrix(solveMatrix.matrix, missingCell)
	solveSubmatrix(fillMatrix1.matrix, fillMatrix1.center)
	solveSubmatrix(fillMatrix2.matrix, fillMatrix2.center)
	solveSubmatrix(fillMatrix3.matrix, fillMatrix3.center)
}

func solve(matrix [][]int, missingCell Cell) {
	if (len(matrix) == 0) {
		return
	}

	rows, columns := len(matrix), len(matrix[0])

	getSubMatrix := func(center Cell) SubMatrix {
		return SubMatrix{ matrix: make([][] int, rows/2), center: center }
	}

	topLeftSub := getSubMatrix(Cell{rows/2-1, columns/2-1})
	topRightSub := getSubMatrix(Cell{rows/2-1, 0})
	bottomLeftSub := getSubMatrix(Cell{0, columns/2-1})
	bottomRightSub := getSubMatrix(Cell{0, 0})

	for i := 0; i< rows; i++ {
		if i < rows/2 {
			topLeftSub.matrix[i] = matrix[i][0:columns/2]
			topRightSub.matrix[i] = matrix[i][columns/2:columns]
		} else {
			bottomLeftSub.matrix[i - rows/2] = matrix[i][0:columns/2]
			bottomRightSub.matrix[i - rows/2] = matrix[i][columns/2:columns]
		}
	}

	top := false
	if missingCell.x < rows/2 { top = true }
	left := false
	if missingCell.y < columns/2 { left = true }
	/* Determine position of missing cell, translate it to match index of submatrix */
	switch {
	case top && left: // TOPLEFT
		fillAndSolve(topLeftSub, topRightSub, bottomLeftSub, bottomRightSub, missingCell)
	case top && !left: // TOPRIGHT
		missingCell.y = missingCell.y - columns/2
		fillAndSolve(topRightSub, topLeftSub, bottomLeftSub, bottomRightSub, missingCell)
	case !top && left: // BOTTOMLEFT
		missingCell.x = missingCell.x - rows/2
		fillAndSolve(bottomLeftSub, topLeftSub, topRightSub, bottomRightSub, missingCell)
	case !top && !left: // BOTTOMRIGHT
		missingCell.x = missingCell.x - rows/2
		missingCell.y = missingCell.y - columns/2
		fillAndSolve(bottomRightSub, topLeftSub, topRightSub, bottomLeftSub, missingCell)
	}
}


func main() {
	var size, x, y int

	fmt.Scanln(&size)
	dimension := int(math.Pow(2, float64(size)))
	matrix := make([][]int, dimension)
	for i := 0; i < len(matrix); i++ {
		matrix[i] = make([] int, dimension)
	}

	fmt.Scanln(&x, &y)
	missingCell := Cell{x: x, y: y}
	matrix[missingCell.x][missingCell.y] = -1

	solve(matrix, missingCell)
	printMatrix(matrix)
}
