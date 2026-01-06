/**
Bomb Enemy
https://leetcode.com/problems/bomb-enemy/

Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero),
return the maximum enemies you can kill using one bomb.

The bomb kills all the enemies in the same row and column from the planted point
	until it hits the wall since the wall is too strong to be destroyed.

Note that you can only put the bomb at an empty cell.

Example:
For the given grid

 0 E 0 0
 E 0 W E
 0 E 0 0

 return 3. (Placing a bomb at (1,1) kills 3 enemies)
 */

class BombEnemy {
	private agg: number[][] = [];
	private rows: number;
	private cols: number;

	constructor(private map: string[][]) {
		for (let i = 0; i < map.length; i++) {
			this.agg[i] = new Array(map[i].length).fill(0);
		}
		this.rows = map.length;
		this.cols = this.rows > 0 ? map[0].length : 0;
	}

	solve() {
		// If it's an enemy - we cannot place a bomb and contribution is 1
		// If it's a wall - we cannot place a bomb and contribution is 0
		// If it's a free spot - we can place a bomb and contribution is 0

		for (let i = 0; i < this.rows; i++) {
			let countLR = 0, countRL = 0;
			for (let j = 0; j < this.cols; j++) {
				if (this.map[i][j] === 'E') {
					this.agg[i][j] = 0;
					countLR++;
				} else if (this.map[i][j] == 'W') {
					countLR = 0;
					this.agg[i][j] = 0;
				} else {
					this.agg[i][j] += countLR;
				}

				if (this.map[i][this.cols - j - 1] === 'E') {
					this.agg[i][this.cols - j - 1] = 0;
					countRL++;
				} else if (this.map[i][this.cols - j - 1] == 'W') {
					countRL = 0;
					this.agg[i][this.cols - j - 1] = 0;
				} else {
					this.agg[i][this.cols - j - 1] += countRL;
				}
			}
		}

		// this.agg.forEach(row => console.log(row.join(' ')));

		for (let i = 0; i < this.cols; i++) {
			let countUD = 0, countDU = 0;
			for (let j = 0; j < this.rows; j++) {
				if (this.map[j][i] === 'E') {
					this.agg[j][i] = 0;
					countUD++;
				} else if (this.map[j][i] == 'W') {
					this.agg[j][i] = 0;
					countUD = 0;
				} else {
					this.agg[j][i] += countUD;
				}

				if (this.map[this.rows - j - 1][i] === 'E') {
					this.agg[this.rows - j - 1][i] = 0;
					countDU++;
				} else if (this.map[this.rows - j - 1][i] == 'W') {
					countDU = 0;
					this.agg[this.rows - j - 1][i] = 0;
				} else {
					this.agg[this.rows - j - 1][i] += countDU;
				}
			}
		}

		// Calculate answer
		let ans = Number.MIN_SAFE_INTEGER;
		let row = -1, col = -1;
		for (let i = 0; i < this.rows; i++) {
			for (let j = 0; j < this.cols; j++) {
				if (ans < this.agg[i][j]) {
					ans = this.agg[i][j];
					row = i;
					col = j;
				}
			}
		}

		return ans;
	}
}

function main() {
	const map = `
	0 E 0 0
	E 0 W E
	0 E 0 0`.split('\n').filter(x => x).map(x => x.trim().split(' '));
	const bombEnemy = new BombEnemy(map);
	console.log(bombEnemy.solve());
}

main()