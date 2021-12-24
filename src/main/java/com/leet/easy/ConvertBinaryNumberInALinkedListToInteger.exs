use Bitwise

defmodule Solution do
  @spec binary_to_decimal(bits :: [integer]) :: integer
  def binary_to_decimal(bits) do
    Enum.reduce(bits, 0, fn bit, acc ->
      (acc <<< 1) + bit
    end)
  end
end

IO.puts(Solution.binary_to_decimal([1, 1, 0, 1]))
