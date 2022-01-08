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
  @spec trim_bst(root :: TreeNode.t() | nil, low :: integer, high :: integer) ::
          TreeNode.t() | nil
  def trim_bst(root, low, high) do
    if root == nil do
      nil
    else
      cond do
        root.val < low ->
          trim_bst(root.right, low, high)

        root.val > high ->
          trim_bst(root.left, low, high)

        true ->
          %TreeNode{
            val: root.val,
            left: trim_bst(root.left, low, high),
            right: trim_bst(root.right, low, high)
          }
      end
    end
  end
end
