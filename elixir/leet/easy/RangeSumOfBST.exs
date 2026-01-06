# Definition for a binary tree node.
defmodule TreeNode do
  @type t :: %__MODULE__{
          val: integer,
          left: TreeNode.t() | nil,
          right: TreeNode.t() | nil
        }
  defstruct val: 0, left: nil, right: nil
end

defmodule Solution do
  @spec range_sum_bst(root :: TreeNode.t() | nil, low :: integer, high :: integer) :: integer
  def range_sum_bst(root, low, high, sum \\ 0) do
    cond do
      is_nil(root) ->
        sum

      root.val >= low and root.val <= high ->
        sum = range_sum_bst(root.left, low, high, sum + root.val)
        range_sum_bst(root.right, low, high, sum)

      root.val < low ->
        range_sum_bst(root.right, low, high, sum)

      root.val > high ->
        range_sum_bst(root.left, low, high, sum)
    end
  end
end
