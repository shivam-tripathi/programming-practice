defmodule Solution do
  def build_row(prev_row, prev_val) do
    if prev_row == [] do
      [prev_val]
    else
      [head | tail] = prev_row
      [head + prev_val | build_row(tail, head)]
    end
  end

  def solve(rows, rows_left) do
    if rows_left == 0 do
      rows
    else
      solve([build_row(Enum.at(rows, 0), 0) | rows], rows_left - 1)
    end
  end

  @spec generate(num_rows :: integer) :: [[integer]]
  def generate(num_rows) do
    ans = [[1]]
    Enum.reverse(solve(ans, num_rows - 1))
  end
end
