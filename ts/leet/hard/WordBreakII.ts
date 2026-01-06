/** A node in the Trie */
class TrieNode {
	leaf: boolean = false;
	nodes: { [val: string]: TrieNode } = {};
}

/** Trie data structure */
class Trie {
	root: TrieNode;
	dp: { [pos: number]: number[] };

	constructor() {
		this.root = new TrieNode();
		this.dp = {};
	}

	/** Add a single word to trie */
	public addWord(word: string) {
		let node = this.root;
		for (const ch of word) {
			if (!node.nodes[ch]) {
				node.nodes[ch] = new TrieNode();
			}
			node = node.nodes[ch];
		}
		node.leaf = true;
	}

	/** Add a bunch of words */
	addAllWords(words: string[]) {
		words.forEach((word) => this.addWord(word));
	}

	/** We start matching the sentence at a given position */
	private matchAtPosition(sentence: string, pos: number): number[] {
		if (this.dp[pos]) {
			return this.dp[pos];
		}

		const ans: number[] = [];

		/** If already beyond sentence size, we abort */
		if (pos > sentence.length) {
			return ans;
		}

		let node = this.root;
		for (let i = pos; i < sentence.length; i++) {
			const ch = sentence[i];
			node = node.nodes[ch];
			if (!node) {
				break;
			}
			/** If current node is leaf, we can mark this position as end of a sentence */
			if (node?.leaf) {
				ans.push(i);
			}
		}

		return ans;
	}

	/**
	 * Match a sentence
	 * - We save all answers in ans
	 * - wordBreaks hold all the breaks till this point (points in the sentence where a word exists)
	 * - pos is the current position in the sentence
	 * - size is the active size of the wordBreaks array (optimisation hack - we use same array for all iterations)
	 **/
	match(sentence: string, ans: string[] = [], wordBreaks: number[] = [], pos = 0, size = 1): string[] {
		/** if a complete match, we break the sentence into words and join them with spaces */
		if (pos === sentence.length) {
			const collect = [];
			for (let i = 1; i < size; i++) {
				let cur = wordBreaks[i], prev = wordBreaks[i - 1];
				collect.push(sentence.slice(prev, cur));
			}
			ans.push(collect.join(' '));
			return ans;
		}

		/** Optimisation: As we use the same wordBreaks array, it may be a fresh one or an old one */
		if (wordBreaks.length < size) {
			wordBreaks.push(pos); // inserts at `size` index, a placeholder value
		}

		/** Get all matching word breaks for this position */
		const matches = this.matchAtPosition(sentence, pos);
		for (const match of matches) {
			// replace the placeholder value
			wordBreaks[size] = match + 1;
			// add this as break point, and match the sentence onwards
			this.match(sentence, ans, wordBreaks, match + 1, size + 1);
		}

		return ans;
	}
}

function wordBreak(s: string, wordDict: string[]): string[] {
	const trie = new Trie();
	trie.addAllWords(wordDict);
	return trie.match(s);
};

function main() {
	[
		{ s: "pineapplepenapple", d: ["apple", "pen", "applepen", "pine", "pineapple"] },
		{ s: "catsandog", d: ["cats", "dog", "sand", "and", "cat"] }
	].forEach(({ s: sentence, d: wordDict }) => {
		console.log({ sentence, wordDict, ans: wordBreak(sentence, wordDict) });
	});
}

main()