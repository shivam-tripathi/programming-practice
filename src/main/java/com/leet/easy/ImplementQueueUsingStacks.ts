class MyQueue {
  stack: number[];
  reverse: number[];

  constructor() {
    this.stack = [];
    this.reverse = [];
  }

  push(x: number): void {
    this.stack.push(x);
  }

  pop(): number {
    if (this.reverse.length === 0) {
      this.flush();
    }
    return this.reverse.pop() as number;
  }

  private flush() {
    while (this.stack.length > 0) {
      this.reverse.push(this.stack.pop() as number);
    }
  }

  peek(): number {
    if (this.reverse.length === 0) {
      this.flush();
    }
    return this.reverse[this.reverse.length - 1];
  }

  empty(): boolean {
    return this.reverse.length === 0 && this.stack.length === 0;
  }
}
