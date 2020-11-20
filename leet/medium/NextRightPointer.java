import java.util.*;

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

class NextRightPointer {
    public Node connect(Node root) {
        class QueueItem {
            Node node;
            int level;
            QueueItem(Node node, int level) {
                this.node = node;
                this.level = level;
            }
        }

        Queue<QueueItem> queue = new LinkedList<>();
        queue.add(new QueueItem(root, 1));
        QueueItem prevItem = null;

        while (queue.size() != 0) {
            QueueItem curItem = queue.peek();
            if (prevItem != null && prevItem.level == curItem.level) {
                prevItem.node.next = curItem.node;
            }
            queue.add(new QueueItem(curItem.node.left, curItem.level+1));
            queue.add(new QueueItem(curItem.node.right, curItem.level+1));
            queue.remove();
            prevItem = curItem;
        }
        return root;
    }
}

class Main {
    public static void main(String[] args) {
        NextRightPointer sol = new NextRightPointer();
        sol.connect(new Node());
    }
}