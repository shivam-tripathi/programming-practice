defmodule Solution do
  def solve(str, mapping, [head | tail]) do
    if str == [] do
      [head | tail]
    else
      [code | remaining] = str
      value = Map.get(mapping, code)

      if value > head do
        solve(remaining, mapping, [value - head | tail])
      else
        solve(remaining, mapping, [value | [head | tail]])
      end
    end
  end

  def solve(str, mapping, []) do
    [code | remaining] = str
    solve(remaining, mapping, [Map.get(mapping, code)])
  end

  @spec roman_to_int(s :: String.t()) :: integer
  def roman_to_int(s) do
    mapping = %{"M" => 1000, "D" => 500, "C" => 100, "L" => 50, "X" => 10, "V" => 5, "I" => 1}
    res = solve(String.codepoints(s), mapping, [])
    Enum.sum(res)
  end
end
