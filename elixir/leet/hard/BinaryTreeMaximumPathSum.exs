defmodule Solution do
  def sum_of(list) do
    Enum.reduce(list, nil, fn e, acc ->
      if acc == nil or e == nil do
        if acc == nil, do: e, else: acc
      else
        acc + e
      end
    end)
  end

  def max_of(list) do
    Enum.reduce(list, nil, fn e, acc ->
      if acc == nil or e == nil do
        if acc == nil, do: e, else: acc
      else
        max(acc, e)
      end
    end)
  end

  def solve(root) do
    if root == nil do
      {nil, nil}
    else
      {left, left_ans} = solve(root.left)
      {right, right_ans} = solve(root.right)
      sum_total = sum_of([left, root.val, right])
      max_running = root.val + max_of([0, left, right])
      ans = max_of([left_ans, right_ans, sum_total, max_running])
      {max_running, ans}
      {0, 0}
    end
  end

  def max_path_sum(root) do
    {_, ans} = solve(root)
    ans
  end
end
