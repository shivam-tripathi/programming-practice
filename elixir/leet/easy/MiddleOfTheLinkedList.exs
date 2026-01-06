"""
876. Middle of the Linked List
https://leetcode.com/problems/middle-of-the-linked-list/
Easy

Given the head of a singly linked list, return the middle node of the linked list.
If there are two middle nodes, return the second middle node.

Example 1:
Input: head = [1,2,3,4,5]
Output: [3,4,5]
Explanation: The middle node of the list is node 3.

Example 2:
Input: head = [1,2,3,4,5,6]
Output: [4,5,6]
Explanation: Since the list has two middle nodes with values 3 and 4, we return the second one.

Constraints:
    The number of nodes in the list is in the range [1, 100].
    1 <= Node.val <= 100
"""

# Definition for singly-linked list.
defmodule ListNode do
  @type t :: %__MODULE__{
          val: integer,
          next: ListNode.t() | nil
        }
  defstruct val: 0, next: nil
end

defmodule Solution do
  @spec solve(slow :: ListNode.t() | nil, fast :: ListNode.t() | nil) :: ListNode.t() | nil
  def solve(slow, fast) do
    if fast == nil or fast.next == nil do
      slow
    else
      solve(slow.next, fast.next.next)
    end
  end

  @spec middle_node(head :: ListNode.t() | nil) :: ListNode.t() | nil
  def middle_node(head) do
    solve(head, head)
  end
end
