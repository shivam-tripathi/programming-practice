defmodule Solution do
  def solve([head | tail], stack) do
    parse = Integer.parse(head)

    cond do
      parse == :error ->
        [b, a | rem] = stack

        cond do
          head == "+" -> solve(tail, [a + b | rem])
          head == "-" -> solve(tail, [a - b | rem])
          head == "*" -> solve(tail, [a * b | rem])
          head == "/" -> solve(tail, [div(a, b) | rem])
        end

      true ->
        {num, _} = parse
        solve(tail, [num | stack])
    end
  end

  def solve([], [ans]) do
    ans
  end

  @spec eval_rpn(tokens :: [String.t()]) :: integer
  def eval_rpn(tokens) do
    solve(tokens, [])
  end
end
