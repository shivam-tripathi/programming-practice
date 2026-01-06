defmodule Solution do
  def solve(map, [cur | trust], n) do
    [a, b] = cur
    map = Map.put(map, a, -1)

    if Map.get(map, b) != -1 do
      map = Map.put(map, b, Map.get(map, b, 0) + 1)
      solve(map, trust, n)
    else
      solve(map, trust, n)
    end
  end

  def solve(map, [], n) do
    Enum.filter(map, fn {k, v} -> v == n - 1 end)
  end

  @spec find_judge(n :: integer, trust :: [[integer]]) :: integer
  def find_judge(n, trust) do
    all = Enum.reduce(1..n, Map.new(), fn x, acc -> Map.put(acc, x, 0) end)

    res = solve(all, trust, n)

    if res == [] do
      -1
    else
      [ans] = res
      elem(ans, 0)
    end
  end
end
