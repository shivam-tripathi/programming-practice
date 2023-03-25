class CombinationIterator {
  private chars: string;
  private len: number;

  private val: string = null;

  constructor(characters: string, combinationLength: number) {
  }

  private *_next(): Generator<string, string> {
    return 0;
  }

  *next(): string {
    if (this.val == null) {
      return yield this._next();
    }
  }

  hasNext(): boolean {
  }
}
