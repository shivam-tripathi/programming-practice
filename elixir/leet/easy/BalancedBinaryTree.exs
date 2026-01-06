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
  def solve(root) do
    if root == nil do
      {true, 0}
    else
      {status, lh} = solve(root.left)

      if !status do
        {status, lh}
      else
        {status, rh} = solve(root.right)
        {status && abs(rh - lh) <= 1, 1 + max(lh, rh)}
      end
    end
  end

  @spec is_balanced(root :: TreeNode.t() | nil) :: boolean
  def is_balanced(root) do
    {status, _} = solve(root)
    status
  end
end
