# Definition for a binary tree node.
#
# defmodule TreeNode do
#   @type t :: %__MODULE__{
#           val: integer,
#           left: TreeNode.t() | nil,
#           right: TreeNode.t() | nil
#         }
#   defstruct val: 0, left: nil, right: nil
# end

defmodule Solution do
  def solve(node, depth, ans) do
    cond do
      node == nil ->
        ans

      node.left == nil && node.right == nil ->
        {ans_depth, ans_sum} = ans

        cond do
          depth > ans_depth -> {depth, node.val}
          depth == ans_depth -> {ans_depth, ans_sum + node.val}
          true -> ans
        end

      true ->
        ans = solve(node.left, depth + 1, ans)
        solve(node.right, depth + 1, ans)
    end
  end

  @spec deepest_leaves_sum(root :: TreeNode.t() | nil) :: integer
  def deepest_leaves_sum(root) do
    {_, sum} = solve(root, 0, {0, 0})
    sum
  end
end
