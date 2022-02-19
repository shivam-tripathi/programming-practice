defmodule Solution do
  @spec sum_of_three(num :: integer) :: [integer]
  def sum_of_three(num) do
    e = Kernel.trunc(num / 3)
    if rem(num, 3) == 0, do: [e - 1, e, e + 1], else: []
  end
end
