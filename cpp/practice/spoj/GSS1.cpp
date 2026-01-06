#include <bits/stdc++.h>
#define negInfinity -1e15
using namespace std;

struct Node {
    long long int max, left, right, total;
};

class SegmentTree {
    public:
    Node *nodes;
    int *arr;
    int size;
    SegmentTree(int* arr, int size) {
		this->arr = arr;
		this->nodes = new Node[4 * size + 10];
        this->size = size;
		build(0, this->size - 1, 0);
    }

    void build(int left, int right, int index) {
        Node node;
		if (left == right) {
			node.left = node.right = node.max = node.total = arr[left];
		} else {
			int mid = (left + right) / 2;
            int leftIndex = 2 * index + 1;
            int rightIndex = 2 * index + 2;

			build(left, mid, leftIndex);
			build(mid + 1, right, rightIndex);

			Node leftNode = nodes[leftIndex];
			Node rightNode = nodes[rightIndex];

			node.left = max(leftNode.left, leftNode.total + rightNode.left);
			node.right = max(rightNode.right, rightNode.total + leftNode.right);
			node.total = leftNode.total + rightNode.total;
			node.max = max(rightNode.max, leftNode.max);
			node.max = max(node.max, leftNode.right + rightNode.left);
		}

		nodes[index] = node;
    }


    Node query(int begin, int end, int l, int r, int index) {
        Node node;
		if (begin > r || end < l) {
			node.left = node.right = node.max = negInfinity;
            node.total = 0;
            return node;
		}

		if ((l == r) || (begin <= l && r <= end)) {
            node = nodes[index];
            return node;
		}

		int mid = (l + r) / 2;
        Node leftNode = query(begin, end, l, mid, index * 2 + 1);
        Node rightNode = query(begin, end, mid + 1, r, index * 2 + 2);

        node.left = max(leftNode.left, leftNode.total + rightNode.left);
        node.right = max(rightNode.right, leftNode.right + rightNode.total);
        node.total = leftNode.total + rightNode.total;
        node.max = max(leftNode.right + rightNode.left, max(leftNode.max, rightNode.max));

		return node;
    }

    long long int query(int begin, int end) {
		Node node = query(begin, end, 0, this->size - 1, 0);
        return node.max;
    }
};

int main(int argc, char const *argv[]) {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int n, m, l, r;
    cin >> n;
    int *arr = new int[n];
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }
    SegmentTree tree(arr, n);
    cin >> m;
    while (m--) {
        cin >> l >> r;
        cout << tree.query(l - 1, r - 1) << endl;
    }
    return 0;
}
