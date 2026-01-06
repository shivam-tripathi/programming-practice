class SkipNode<T> {
  // the value or key stored in the node.
  public value: T;
  // level for the current node
  public level: number;
  // An array of pointers to next nodes at different levels.
  public forward: SkipNode<T>[];

  constructor(value: T, level: number) {
    this.value = value;
    this.level = level;
    this.forward = new Array(level + 1).fill(null);
  }
}

class SkipList<T> {
  // maxLevel levels keeps track of the maximum level in the skip list. Initially, it's set to 0.
  private maxLevel = 0;

  // head node serves as a starting point for all levels.
  // It doesn't contain any value but helps with navigation.
  private head!: SkipNode<T>;
  constructor() {
    this.head = new SkipNode<T>(null as T, 0);
  }

  // The level of a node is chosen probabilistically, typically wiht p = 0.5. Here we use random
  // number generator. Higher levels have fewer nodes, so they're chosen less frequently.
  private selectLevelForNode() {
    let level = 0;
    while (Math.random() < 0.5 && level < this.maxLevel) {
      level += 1;
    }
    return level;
  }

  public insert(value: T) {
    const maxLevelNode = this.selectLevelForNode();
    const newNode = new SkipNode(value, maxLevelNode);
    let current = this.head;
    for (let level = this.maxLevel; level >= 0; level -= 1) {
      while (
        current.forward[level] && current.forward[level].value < newNode.value
      ) {
        current = current.forward[level];
      }
      newNode.forward[level] = current.forward[level];
      current.forward[level] = newNode;
    }

    if (newNode.level > this.maxLevel) {
      this.maxLevel = newNode.level;
    }
  }

  print() {
    const collected: { [level: number]: T[] } = {};
    for (let level = this.maxLevel; level >= 0; level -= 1) {
      let current = this.head;
      collected[level] = [];
      while (current.forward[level]) {
        collected[level].push(current.forward[level].value);
        current = current.forward[level];
      }
    }
    console.log(JSON.stringify(collected, null, 2));
  }
}

Deno.test({
  name: "inserts",
  fn: () => {
    const skipList = new SkipList<number>();
    for (let i = 0; i < 25; i++) {
      skipList.insert(Math.ceil(Math.random() * 100));
    }
    skipList.print();
  },
});
