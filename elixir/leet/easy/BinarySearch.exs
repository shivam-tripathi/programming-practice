defmodule Solution do
  @spec solve(arr :: {integer}, target :: integer, low :: integer, high :: integer) :: integer
  def solve(_arr, _target, low, high) when high < low, do: -1

  def solve(arr, target, low, high) do
    mid = div(low + high, 2)
    val = elem(arr, mid)

    cond do
      val < target ->
        solve(arr, target, mid + 1, high)

      val > target ->
        solve(arr, target, low, mid - 1)

      true ->
        mid
    end
  end

  @spec search(nums :: [integer], target :: integer) :: integer
  def search(nums, target) do
    arr = List.to_tuple(nums)
    solve(arr, target, 0, tuple_size(arr) - 1)
  end
end
