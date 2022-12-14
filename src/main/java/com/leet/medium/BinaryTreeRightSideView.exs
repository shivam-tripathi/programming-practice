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
  def right_side_view(node, depth, map) do
    if node == nil do
      map
    else
      map =
        case Map.fetch(map, depth) do
          :error -> Map.put(map, depth, node.val)
          {:ok, _} -> map
        end

      map = right_side_view(node.right, depth + 1, map)
      right_side_view(node.left, depth + 1, map)
    end
  end

  @spec right_side_view(root :: TreeNode.t() | nil) :: [integer]
  def right_side_view(root) do
    right_side_view(root, 0, %{})
    |> Map.to_list()
    |> Enum.sort()
    |> Enum.map(fn {_, v} -> v end)
  end
end
