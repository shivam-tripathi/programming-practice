# Definition for singly-linked list.
defmodule ListNode do
  @type t :: %__MODULE__{
          val: integer,
          next: ListNode.t() | nil
        }
  defstruct val: 0, next: nil
end

defmodule Solution do
  @spec insertion_sort(ins :: ListNode.t(), node :: ListNode.t() | nil) :: ListNode.t()
  def insertion_sort(ins, node) do
    if is_nil(node) do
      %ListNode{ins | next: nil}
    else
      if ins.val > node.val do
        %ListNode{node | next: insertion_sort(ins, node.next)}
      else
        %ListNode{ins | next: node}
      end
    end
  end

  @spec insertion_sort_all(sentinel :: ListNode.t(), head :: ListNode.t() | nil) :: ListNode.t()
  def insertion_sort_all(head, ans \\ nil) do
    if is_nil(head) do
      ans
    else
      ans = insertion_sort(head, ans)
      insertion_sort_all(head.next, ans)
    end
  end

  @spec insertion_sort_list(head :: ListNode.t() | nil) :: ListNode.t() | nil
  def insertion_sort_list(head) do
    insertion_sort_all(head)
  end
end
