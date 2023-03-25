defmodule BSTIterator do
  @spec init_(root :: TreeNode.t() | nil) :: any
  def init_(root) do
    stack = handle_node(root, [])
    {status, _} = Agent.start_link(fn -> stack end, name: :bst_iterator)

    if status != :ok do
      Agent.update(:bst_iterator, fn _ -> stack end)
    end
  end

  defp handle_node(node, stack) do
    if node == nil do
      stack
    else
      handle_node(node.left, [node | stack])
    end
  end

  @spec next() :: integer
  def next() do
    [node | stack] = Agent.get(:bst_iterator, & &1)
    stack = handle_node(node.right, stack)
    Agent.update(:bst_iterator, fn _ -> stack end)
    node.val
  end

  @spec has_next() :: boolean
  def has_next() do
    stack = Agent.get(:bst_iterator, & &1)
    stack != []
  end
end
