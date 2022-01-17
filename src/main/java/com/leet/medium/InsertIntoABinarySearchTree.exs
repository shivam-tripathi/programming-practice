# Definition for a binary tree node.
#
defmodule TreeNode do
  @type t :: %__MODULE__{
          val: integer,
          left: TreeNode.t() | nil,
          right: TreeNode.t() | nil
        }
  defstruct val: 0, left: nil, right: nil
end

defmodule Solution do
  @docmodule """
  Weird thing about immutable trees: Change in one node will propagate upto the root tree
  """
  @spec insert_into_bst(root :: TreeNode.t() | nil, val :: integer) :: TreeNode.t() | nil
  def insert_into_bst(root, val) do
    if root == nil do
      %TreeNode{val: val, left: nil, right: nil}
    else
      if root.val < val do
        %TreeNode{val: root.val, left: root.left, right: insert_into_bst(root.right, val)}
      else
        %TreeNode{val: root.val, left: insert_into_bst(root.left, val), right: root.right}
      end
    end
  end
end
