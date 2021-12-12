defmodule Solution do
  def solve([head | tail]) do
    cond do
      is_number(head) ->
        {ans, height} = solve(tail)
        height = max(1, height)
        {ans + height * head, height}

      is_list(head) ->
        {ansHead, heightHead} = solve(head)
        {ansTail, heightTail} = solve(tail)
        {ansHead + ansTail, max(heightHead + 1, heightTail)}

      true ->
        {0, 0}
    end
  end

  def solve([]) do
    {0, 0}
  end

  def nestedListWeightSum2(elems) do
    {ans, _} = solve(elems)
    ans
  end
end

IO.puts(Solution.nestedListWeightSum2([[1, 1], 2, [1, 1]]))
IO.puts(Solution.nestedListWeightSum2([1, [4, [6]]]))
