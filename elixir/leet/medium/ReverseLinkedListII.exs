# Definition for singly-linked list.
#
# defmodule ListNode do
#   @type t :: %__MODULE__{
#           val: integer,
#           next: ListNode.t() | nil
#         }
#   defstruct val: 0, next: nil
# end


f = fn (node) ->
  if node == nil do
    []
  else
    val = node.val
    [val | f.(node.next)]
  end
end

defmodule Solution do
  def sll_to_list(node) do
    if node == nil do
      []
    else
      val = node.val
      [val | sll_to_list(node.next)]
    end
  end

  def reverse_list(node, collect, last) do
    case list do
      [] ->
        [collect, []]

      _ ->
        remaining = node.next
        collect = [node | collect]

        if cur == last do
          [collect, remaining]
        else
          reverse_list(remaining, collect, last)
        end
    end
  end

  def to_sll(list) do
    if list == [] do
      nil
    else
      [cur | remaining] = list
      %ListNode{val: cur.val, next: to_sll(remaining)}
    end
  end

  @spec reverse_between(head :: ListNode.t() | nil, left :: integer, right :: integer) ::
          ListNode.t() | nil
  def reverse_between(head, left, right) do
    list = sll_to_list(head)
  end
end
