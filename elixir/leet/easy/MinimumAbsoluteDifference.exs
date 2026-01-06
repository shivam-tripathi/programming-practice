defmodule Solution do
  @spec minimum_abs_difference(arr :: [integer]) :: [[integer]]
  def minimum_abs_difference(arr) do
    ans = %{min: -1, pairs: []}

    for n <- arr do
      for k <- arr do
        cond do
          n == k ->
            ans

          n - k < ans.min or ans.min == -1 ->
            ans = %{min: n - k, pairs: [[n, k]]}

          n - k == ans.min ->
            ans = Map.put(ans, "pairs", [[n, k] | ans.pairs])

          true ->
            ans
        end
      end
    end
  end
end
