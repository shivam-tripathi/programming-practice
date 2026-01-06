function findTopTwo(arr: number[]) {
	let first = -1, second = -1;
	for (let i = 0; i < arr.length; i++) {
		if (first == -1) {
			if (arr[i] != 0) first = i;
		} else if (arr[first] < arr[i]) {
			second = first;
			first = i;
		} else if (second == -1) {
			if (arr[i] != 0) second = i;
		} else if (arr[second] < arr[i]) {
			second = i;
		}
	}
	return [first, second];
}

function minimumOperations(nums: number[]): number {
	const odds = new Array(10).fill(0);
	const evens = new Array(10).fill(0);

	let totalOdds = 0, totalEvens = 0;

	for (let i = 0; i < nums.length; i++) {
		if (i % 2 === 0) {
			evens[nums[i]]++;
			totalEvens++;
		} else {
			odds[nums[i]]++;
			totalOdds++;
		}
	}

	const topOdds = findTopTwo(odds).filter(x => x != -1);
	const topEvens = findTopTwo(evens).filter(x => x != -1);

	const pairs = topOdds
		.flatMap(x => topEvens.map(y => [x, y]))
		.filter(x => x[0] != x[1])
		.map(e => [odds[e[0]], evens[e[1]]])
		.sort((a, b) => b[1] - a[1]);

	let sum: number;
	if (pairs.length > 0) {
		sum = pairs[0][0] + pairs[0][1];
	} else {
		sum = Math.min(totalOdds, totalEvens);
	}
	// console.log({ sum, pairs });
	return totalOdds + totalEvens - sum;
};


console.log(minimumOperations([3, 1, 3, 2, 4, 3]));
console.log(minimumOperations([1, 2, 2, 2, 2]));
console.log(minimumOperations([2, 2, 2, 2, 2]));