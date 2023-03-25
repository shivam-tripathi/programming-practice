# Definition for singly-linked list.
#
# defmodule ListNode do
#   @type t :: %__MODULE__{
#           val: integer,
#           next: ListNode.t() | nil
#         }
#   defstruct val: 0, next: nil
# end

defmodule Solution do
  def pair_sum_util(node, head) do
    if node == nil do
      [0, head]
    else
      [ans, compl] = pair_sum_util(node.next, head)
      cur = node.val + compl.val
      ans = if ans > cur, do: ans, else: cur
      [ans, compl.next]
    end
  end

  @spec pair_sum(head :: ListNode.t() | nil) :: integer
  def pair_sum(head) do
    [ans, _] = pair_sum_util(head, head)
    ans
  end
end
