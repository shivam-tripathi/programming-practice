defmodule Solution do
  @spec max_sub_array(nums :: [integer]) :: integer
  def max_sub_array(nums) do
    [head | tail] = nums

    {_, ans} =
      Enum.reduce(tail, {head, head}, fn elem, {sum, ans} ->
        if sum + elem < elem, do: {elem, max(ans, elem)}, else: {elem + sum, max(ans, elem + sum)}
      end)

    ans
  end
end
