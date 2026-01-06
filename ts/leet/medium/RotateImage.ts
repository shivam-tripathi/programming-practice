/**
 Do not return anything, modify matrix in-place instead.

 [[1,2,3],
  [4,5,6],
  [7,8,9]]

  0,0 -> 0,2 -> 2,2 -> 2,0
  0,1 -> 1,2 -> 2,1 -> 1,0

 */

function processLayer(matrix: number[][], offset: number): void {
  const size = matrix.length - 2 * offset;
  const row = r => r + offset;
  const col = c => c + offset;
  for (let i = 0; i < size - 1; i++) {
    // first row, given col
    const t1 = matrix[row(0)][col(i)];
    // last col, given row
    const t2 = matrix[row(i)][col(size - 1)];
    // last row, reverse given col
    const t3 = matrix[row(size - 1)][col(size - 1 - i)];
    // first col, reverse given row
    const t4 = matrix[row(size - 1 - i)][col(0)];

    matrix[row(0)][col(i)] = t4;
    matrix[row(i)][col(size - 1)] = t1;
    matrix[row(size - 1)][col(size - 1 - i)] = t2;
    matrix[row(size - 1 - i)][col(0)] = t3;
  }
}

function rotate(matrix: number[][]): void {
  for (let offset = 0; offset < Math.floor(matrix.length / 2); offset++) {
    processLayer(matrix, offset);
  }
}

