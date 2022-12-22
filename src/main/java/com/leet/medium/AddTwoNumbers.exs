# Definition for singly-linked list.

defmodule ListNode do
  @type t :: %__MODULE__{
          val: integer,
          next: ListNode.t() | nil
        }
  defstruct val: 0, next: nil
end

defmodule Solution do
  def add(l1, l2, carry) do
    cond do
      l1 == nil and l2 == nil and carry > 0 ->
        %ListNode{val: carry}

      l1 == nil and l2 == nil ->
        nil

      l1 == nil ->
        sum = l2.val + carry
        %ListNode{val: rem(sum, 10), next: add(l2.next, nil, div(sum, 10))}

      l2 == nil ->
        sum = l1.val + carry
        %ListNode{val: rem(sum, 10), next: add(l1.next, nil, div(sum, 10))}

      true ->
        sum = l1.val + l2.val + carry
        %ListNode{val: rem(sum, 10), next: add(l1.next, l2.next, div(sum, 10))}
    end
  end

  @spec add_two_numbers(l1 :: ListNode.t() | nil, l2 :: ListNode.t() | nil) :: ListNode.t() | nil
  def add_two_numbers(l1, l2) do
    add(l1, l2, 0)
  end
end
