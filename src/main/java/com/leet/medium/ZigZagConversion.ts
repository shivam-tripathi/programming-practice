export function convert(s: string, numRows: number): string {
	if (numRows <= 1) {
			return s;
	}
	const ans = new Array(numRows).fill('');

	let row = 0;
	let down = true;
	for (let i = 0; i < s.length; i++) {
			ans[row] += s[i];
			if (row === 0) {
					down = true;
			} else if (row === (numRows-1)) {
					down = false;
			}
			row += down ? 1 : -1;
	}
	return ans.join('');
}
