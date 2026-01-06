defmodule Solution do
  def solve(nums, size, cur \\ [], done \\ %{}, done_count \\ 0, ans \\ []) do
    if done_count == size do
      [cur | ans]
    else
      Enum.reduce(nums, ans, fn num, acc ->
        if !Map.get(done, num, false) do
          solve(nums, size, [num | cur], Map.put(done, num, true), done_count + 1, acc)
        else
          acc
        end
      end)
    end
  end

  @spec permute(nums :: [integer]) :: [[integer]]
  def permute(nums) do
    solve(nums, length(nums))
  end
end

IO.inspect(Solution.permute([1, 2, 3, 4, 5, 6]))
