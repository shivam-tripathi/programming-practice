defmodule Solution do
  def solve(left, right) do
    cond do
      left == nil and right == nil -> true
      left == nil or right == nil -> false
      left.val != right.val -> false
      true -> solve(left.left, right.right) and solve(left.right, right.left)
    end
  end

  @spec is_symmetric(root :: TreeNode.t() | nil) :: boolean
  def is_symmetric(root) do
    solve(root.left, root.right)
  end
end
