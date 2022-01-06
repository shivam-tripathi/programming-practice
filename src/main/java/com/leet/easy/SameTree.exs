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
  @spec is_same_tree(p :: TreeNode.t() | nil, q :: TreeNode.t() | nil) :: boolean
  def is_same_tree(p, q) do
    cond do
      p == nil && q == nil -> true
      p == nil || q == nil -> false
      p.val != q.val -> false
      true -> is_same_tree(p.left, q.left) && is_same_tree(p.right, q.right)
    end
  end
end
