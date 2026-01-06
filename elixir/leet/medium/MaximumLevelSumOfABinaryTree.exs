defmodule Solution do
  def bfs(queue, level, ans) do
    if queue == [] do
      ans
    else
      {sum, queue} =
        Enum.reduce(queue, {0, []}, fn node, acc ->
          {cur_sum, cur_q} = acc
          cur_q = if node.left != nil, do: [node.left | cur_q], else: cur_q
          cur_q = if node.right != nil, do: [node.right | cur_q], else: cur_q
          {cur_sum + node.val, cur_q}
        end)

      ans = if sum > elem(ans, 1), do: {level, sum}, else: ans
      bfs(queue, level + 1, ans)
    end
  end

  @spec max_level_sum(root :: TreeNode.t() | nil) :: integer
  def max_level_sum(root) do
    if root == nil do
      0
    else
      {level, _sum} = bfs([root], 1, {1, root.val})
      level
    end
  end
end
