defmodule Solution do
  def solve(nums, start, stop, jumps) do
    if stop >= :array.size(nums) - 1 do
      jumps
    else
      farthest = max_jump(nums, start, stop, 0)
      solve(nums, stop + 1, farthest, jumps + 1)
    end
  end

  def max_jump(nums, start, stop, ans) do
    if start > stop do
      ans
    else
      ans = max(ans, start + :array.get(start, nums))
      max_jump(nums, start + 1, stop, ans)
    end
  end

  @spec jump(nums :: [integer]) :: integer
  def jump(nums) do
    arr = :array.from_list(nums)

    if :array.size(arr) <= 1 do
      0
    else
      solve(arr, 1, :array.get(0, arr), 1)
    end
  end
end

IO.puts(Solution.jump([]))
IO.puts(Solution.jump([2]))
IO.puts(Solution.jump([2, 3, 1, 1, 4]))
IO.puts(Solution.jump([2, 3, 0, 1, 4]))
