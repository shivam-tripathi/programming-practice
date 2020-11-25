/**
 * Definition for singly-linked list.
 */

class ListNode {
	int val;
    ListNode next;
    ListNode() {}
	ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public int getDecimalValue(ListNode head) {
        int sol = 0;
        ListNode node = head;
        while (node != null) {
            sol = sol << 1;
            sol = sol | node.val;
            node = node.next;
        }
        return sol;
    }
}

class Main {
	public static void main(String[] args) {
		ListNode head = null;

		int[] items = new int[] {1,0,0,1,0,0,1,1,1,0,0,0,0,0,0};
		ListNode node = null;
		for (Integer item : items) {
			ListNode temp = new ListNode(item);
			if (node != null) {
				node.next = temp;
			}
			if (head == null) {
				head = temp;
			}
			node = temp;
		}

		Solution solution = new Solution();
		int ans = solution.getDecimalValue(head);
		System.out.println(ans);
	}
}
