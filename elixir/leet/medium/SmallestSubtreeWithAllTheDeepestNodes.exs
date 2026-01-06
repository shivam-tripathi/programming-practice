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
  def solve(node, depth) do
    if node == nil do
      {depth - 1, nil}
    else
      {ld, la} = solve(node.left, depth + 1)
      {rd, ra} = solve(node.right, depth + 1)

      if ld == rd do
        {ld, node}
      else
        if ld > rd, do: {ld, la}, else: {rd, ra}
      end
    end
  end

  @spec subtree_with_all_deepest(root :: TreeNode.t() | nil) :: TreeNode.t() | nil
  def subtree_with_all_deepest(root) do
    {_, ans} = solve(root, 0)
    ans
  end
end
