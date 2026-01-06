class DoublyLinkedList {
  constructor(
    public start: number,
    public size: number,
    public prev?: DoublyLinkedList,
    public next?: DoublyLinkedList,
  ) {}
}

class Allocator {
  head: DoublyLinkedList;
  allocated: Record<number, DoublyLinkedList[]> = {};

  constructor(n: number) {
    this.head = new DoublyLinkedList(0, n);
  }

  allocate(size: number, mID: number): number {
    let iter = this.head;
    let node: DoublyLinkedList | null = null;
    while (iter) {
      if (iter.size >= size) {
        node = iter;
        break;
      }
      iter = iter.next as DoublyLinkedList;
    }
    if (node) {
      if (node.size == size) {
        if (node.prev) node.prev.next = node.next;
        if (node.next) node.next.prev = node.prev;
      } else {
        const nnode = new DoublyLinkedList(
          node.start + size,
          node.size - size,
          node.prev,
          node.next,
        );
        if (node.prev) node.prev.next = nnode;
        if (node.next) node.next.prev = nnode;
      }
      this.allocated = this.allocated ?? [];
      this.allocated.push();
    }
    return -1;
  }

  free(mID: number): number {
  }
}

/**
 * Your Allocator object will be instantiated and called as such:
 * var obj = new Allocator(n)
 * var param_1 = obj.allocate(size,mID)
 * var param_2 = obj.free(mID)
 */
