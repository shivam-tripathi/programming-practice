// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode sHead = head;
        ListNode sTail = head;
        ListNode nextNode = head.next;

        while (nextNode != null) {
            ListNode node = sHead;
            ListNode prev = null;

            while (node.val < nextNode.val) {
                prev = node;
                node = node.next;
                if (prev == sTail) {
                    break;
                }
            }

            if (prev == null) { // Insertion at head
                sTail.next = nextNode.next;
                nextNode.next = sHead;
                sHead = nextNode;
                nextNode = sTail.next;
            } else if (prev == sTail) { // Insertion at tail
                sTail = nextNode;
                nextNode = nextNode.next;
            } else { // Insertion in middle
                prev.next = nextNode;
                sTail.next = nextNode.next;
                nextNode.next = node;
                nextNode = sTail.next;
            }
        }

        return sHead;
    }
}