class SortTheJumbledNumbers {
	private cache: Map<number, number> = new Map();
	constructor(private readonly mapping: number[]) { }

	private conv(num: number) {
		let ans = 0, offset = 1, copy = num;
		if (num === 0) {
			return this.mapping[0];
		}
		if (this.cache.has(num)) {
			return this.cache.get(num);
		}
		while (copy > 0) {
			const digit = copy % 10;
			copy = Math.floor(copy / 10);
			// offset is multiplied with digit
			ans = ans + offset * this.mapping[digit];
			offset = offset * 10;
		}
		this.cache.set(num, ans);
		return ans;
	}

	sort(nums: number[]) {
		return nums.sort((a, b) => {
			return this.conv(a) - this.conv(b);
		});
	}
}

function sortJumbled(mapping: number[], nums: number[]): number[] {
	const solution = new SortTheJumbledNumbers(mapping);
	return solution.sort(nums);
};