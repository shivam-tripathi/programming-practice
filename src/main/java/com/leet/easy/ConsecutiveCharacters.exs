defmodule Solution do
  def solve(str, prev, count) do
    if str == "" do
      count
    else
      {head, tail} = String.next_grapheme(str)

      if head == prev do
        solve(tail, head, count + 1)
      else
        max(count, solve(tail, head, 1))
      end
    end
  end

  @spec max_power(s :: String.t()) :: integer
  def max_power(s) do
    solve(s, "", 0)
  end
end
