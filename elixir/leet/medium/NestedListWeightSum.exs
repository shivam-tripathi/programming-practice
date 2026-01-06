defmodule Solution do
  def solve([head | tail], depth) do
    cond do
      is_number(head) ->
        depth * head + solve(tail, depth)

      is_list(head) ->
        solve(head, depth + 1) + solve(tail, depth)

      true ->
        0
    end
  end

  def solve([], _depth) do
    0
  end

  def nestedListWeightSum(nums) do
    solve(nums, 1)
  end
end

IO.puts(Solution.nestedListWeightSum([[1, 1], 2, [1, 1]]))
IO.puts(Solution.nestedListWeightSum([1, [4, [6]]]))
