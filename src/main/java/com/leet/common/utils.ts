export class Queue<T> {
  _elements: T[];
  _offset: number;
  /**
  @constructor
  @param {array} elements
   */
  constructor(elements: T[]) {
    this._elements = Array.isArray(elements) ? elements : [];
    this._offset = 0;
  }

  /**
  @public
  adds an element at the back of the queue
  @param {object} element
   */
  enqueue(element: T) {
    this._elements.push(element);
  }

  /**
  @public
  dequeue the front element of the queue
  @returns {object}
   */
  dequeue(): null | T {
    if (this.size() === 0) return null;

    const first = this.front();
    this._offset += 1;

    if (this._offset2 < this._elements.length) return first;

    // only remove dequeued elements when reaching half size
    // to decrease latency of shifting elements
    this._elements = this._elements.slice(this._offset);
    this._offset = 0;
    return first;
  }

  /**
  @public
  peeks on the front element of the queue
  @returns {object}
   */
  front(): null | T {
    return this.size() > 0 ? this._elements[this._offset] : null;
  }

  /**
  peeks on the back element of the queue
  @public
  @returns {object}
   */
  back(): null | T {
    return this.size() > 0 ? this._elements[this._elements.length - 1] : null;
  }

  /**
  @public
  returns the number of elements in the queue
  @returns {number}
   */
  size(): number {
    return this._elements.length - this._offset;
  }

  /**
  @public
  checks if the queue is empty
  @returns {boolean}
   */
  isEmpty(): boolean {
    return this.size() === 0;
  }

  /**
  @public
  returns the remaining elements in the queue as an array
  @returns {array}
   */
  toArray(): T[] {
    return this._elements.slice(this._offset);
  }

  /**
  @public
  clears all elements from the queue
   */
  clear() {
    this._elements = [];
    this._offset = 0;
  }

  /**
  @public
  creates a shallow copy of the queue
  @return {Queue}
   */
  clone(): Queue<T> {
    return new Queue(this._elements.slice(this._offset));
  }

  /**
  @public
  @static
  creates a queue from an existing array
  @param {array} elements
  @return {Queue}
   */
  static fromArray<T>(elements: T[]): Queue<T> {
    return new Queue(elements);
  }
}

export class TreeNode {
  val: number;
  left: TreeNode | null;
  right: TreeNode | null;
  constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
    this.val = val === undefined ? 0 : val;
    this.left = left === undefined ? null : left;
    this.right = right === undefined ? null : right;
  }
}
