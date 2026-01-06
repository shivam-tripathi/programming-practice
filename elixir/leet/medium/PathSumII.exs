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
  def solve(node, path, ans, sum, target_sum) do
    if node == nil do
      ans
    else
      path = [node.val | path]
      sum = sum + node.val

      if node.left == nil and node.right == nil and sum == target_sum do
        [Enum.reverse(path) | ans]
      else
        ans = solve(node.left, path, ans, sum, target_sum)
        solve(node.right, path, ans, sum, target_sum)
      end
    end
  end

  @spec path_sum(root :: TreeNode.t() | nil, target_sum :: integer) :: [[integer]]
  def path_sum(root, target_sum) do
    solve(root, [], [], 0, target_sum)
  end
end
