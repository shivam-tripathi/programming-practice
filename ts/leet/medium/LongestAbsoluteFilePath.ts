
import { assertEquals } from "https://deno.land/std/testing/asserts.ts";

class FilePath {
	private tokens: string[];
	private ptr: number;
	private ans: string[] = [];

	constructor(input: string) {
		this.tokens = input.split('\n');
		this.ptr = 0;
	}

	private getAll(prev = "", offset: string = ""): boolean {
		if (this.ptr >= this.tokens.length) {
			return false;
		}

		if (!this.tokens[this.ptr].startsWith(offset)) {
			return false;
		}

		const token = this.tokens[this.ptr].substring(offset.length);
		const path = prev ? `${prev}/${token}` : token;

		this.ptr++;

		if (token.indexOf(".") !== -1) {
			this.ans.push(path);
			return true;
		}

		while (this.getAll(path, offset + "\t")) { }

		return true;
	}

	getFilePaths(): string[] {
		while (this.getAll()) { }
		return this.ans;
	}
}

function lengthLongestPath(input: string): number {
	const fp = new FilePath(input)
	const paths = fp.getFilePaths();
	// console.log(paths);
	return paths
		.reduce((acc, str) => Math.max(acc, str.length), 0);
}


Deno.test("388. Longest Absolute File Path", () => {
	const input = [
		{ input: "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext", ans: 20 },
		{ input: "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext", ans: 32 },
		{ input: "a", ans: 0 },
		{ input: "file1.txt\nfile2.txt\nlongfile.txt", ans: 12 }
	];

	input.forEach(({ input, ans }) => {
		assertEquals(lengthLongestPath(input), ans);
	});
});