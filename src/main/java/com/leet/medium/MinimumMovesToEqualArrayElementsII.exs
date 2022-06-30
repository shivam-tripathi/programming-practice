defmodule Solution do
  @spec min_moves2(nums :: [integer]) :: integer
  def min_moves2([num]) do
    0
  end

  @spec min_moves2(nums :: [integer]) :: integer
  def min_moves2([]) do
    0
  end

  @spec min_moves2(nums :: [integer]) :: integer
  def min_moves2(nums) do
    sorted = Enum.sort(nums)
    size = length(nums)

    median =
      case rem(size, 2) == 0 do
        false ->
          Enum.at(sorted, div(size, 2))

        true ->
          mid_idx = div(size, 2)
          div(Enum.at(sorted, mid_idx) + Enum.at(sorted, mid_idx - 1), 2)
      end

    Enum.sum(Enum.map(sorted, fn e -> abs(e - median) end))
  end
end
