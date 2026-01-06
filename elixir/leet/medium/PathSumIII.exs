defmodule Solution do
  def solve(node, target_sum) do
    if node == nil do
      {[], 0}
    else
      {left_arr, left_count} = solve(node.left, target_sum)
      {right_arr, right_count} = solve(node.right, target_sum)
      {arr, lcount} = merge(left_arr, [], node, target_sum)
      {arr, rcount} = merge(right_arr, arr, node, target_sum)
      count = if node.val == target_sum, do: 1 + lcount + rcount, else: lcount + rcount
      {[node.val | arr], count + left_count + right_count}
    end
  end

  @spec merge(arr :: [integer], ans :: [integer], node :: TreeNode.t(), target_sum :: integer) ::
          {[integer], integer}
  def merge(arr, ans, node, target_sum) do
    Enum.reduce(arr, {ans, 0}, fn e, acc ->
      {lis, count} = acc
      count = if e + node.val == target_sum, do: count + 1, else: count
      {[e + node.val | lis], count}
    end)
  end

  @spec path_sum(root :: TreeNode.t() | nil, target_sum :: integer) :: integer
  def path_sum(root, target_sum) do
    {_, count} = solve(root, target_sum)
    count
  end
end
