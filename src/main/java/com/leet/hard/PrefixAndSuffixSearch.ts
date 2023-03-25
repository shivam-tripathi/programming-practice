class WordFilter {
	private prefix: Record<string, [number, number][]> = {};
	private suffix: Record<string, [number, number][]> = {};
	constructor(private words: string[]) {
		const list = words.reduce((acc, word, idx) => {
			acc[word] = idx;
			return acc;
		}, {} as Record<string, number>);
		Object.keys(list).forEach(word => {
			const idx = list[word];
			for (let i = 1; i <= word.length; i++) {
				const pre = word.substring(0, i);
				if (!this.prefix[pre]) this.prefix[pre] = [];
				this.prefix[pre].push([idx, word.length]);
				const suf = word.substring(word.length - i);
				if (!this.suffix[suf]) this.suffix[suf] = [];
				this.suffix[suf].push([idx, word.length]);
			}
		});
	}

	f(prefix: string, suffix: string): number {
		const pre = new Set<number>(this.prefix[prefix]?.map(e => e[0]) ?? []);
		const filtered = this.suffix[suffix]?.filter(idx => pre.has(idx[0])) ?? [];
		// console.log({
		// 	prefix, suffix, filtered: filtered.map(e => {
		// 		return [this.words[e[0]], e[1], e[0]];
		// 	})
		// });
		return filtered.sort((a, b) => b[0] - a[0])?.[0]?.[0] ?? -1;
	}
}



// Input: ["WordFilter","f"]
// [[["abbba","abba"]],["ab","ba"]]
// Output: [null,0]
// Expected: [null,1]

