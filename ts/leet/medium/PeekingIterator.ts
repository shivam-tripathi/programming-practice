
interface IteratorItem {
	hasNext(): boolean;

	next(): number;
}

class PeekingIterator {
	top: number | null = null;

	constructor(private iter: IteratorItem) {
		this.top = this.iter.next() ?? null;
	}

	peek(): number | null {
		return this.top;
	}

	next(): number | null {
		const elem = this.top;
		if (this.iter.hasNext()) {
			this.top = this.iter.next();
		} else {
			this.top = null;
		}
		return elem;
	}

	hasNext(): boolean {
		return this.top !== null;
	}
}
