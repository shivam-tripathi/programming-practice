/**
 * Definition for Node.
 */
class Node {
  val: number;
  neighbors: Node[];
  constructor(val?: number, neighbors?: Node[]) {
    this.val = val === undefined ? 0 : val;
    this.neighbors = neighbors === undefined ? [] : neighbors;
  }
}
function cloneNode(node: Node, clones: Map<Node, Node>): Node {
  if (clones.has(node)) {
    return clones.get(node) as Node;
  }
  const clone = new Node(node.val);
  clones.set(node, clone);
  clone.neighbors = node.neighbors.map((neighbor) =>
    cloneNode(neighbor, clones)
  ) as Node[];
  return clone;
}

function cloneGraph(node: Node | null): Node | null {
  if (!node) return node;
  return cloneNode(node, new Map());
}
