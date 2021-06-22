/**
 * https://leetcode.com/problems/triangle/
 **/

function minimumTotal(triangle: number[][]): number {
  let paths = triangle[0];
  for (let i = 1; i < triangle.length; i++) {
    const size = triangle[i].length;
    // Next set of paths has size equal to current row
    const next: number[] = new Array(size);
    // Fill all the values, except first and last element
    for (let j = 1; j < size - 1; j++) {
      next[j] = triangle[i][j] + Math.min(paths[j], paths[j - 1]);
    }
    // Handle first and last values separately
    next[0] = triangle[i][0] + paths[0];
    next[size - 1] = triangle[i][size - 1] + paths[size - 2];
		// Update paths
    paths = next;
  }
	// Return minimum value
  return paths.reduce((acc, path) => Math.min(acc, path));
}
