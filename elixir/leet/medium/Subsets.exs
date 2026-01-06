defmodule Solution do
  def powerSets([head | tail]) do
    concat(powerSets(tail), head)
  end

  def powerSets([]) do
    [[]]
  end

  '''
  Either the number will be prepended to the the head, or it will be not.
  These two will be prepended to the remaining part of the answer
  '''

  def concat([head | tail], num) do
    [[num | head] | [head | concat(tail, num)]]
  end

  def concat([], _num) do
    []
  end

  def word_subsets(nums) do
    powerSets(nums)
  end
end

IO.inspect(Solution.concat([[1, 2, 3], [3, 4, 5], [6, 7, 8]], 10))
IO.inspect(Solution.word_subsets([1, 2, 3]))
