defmodule Solution do
  def solve([root | tail]) do
    if root == "#" do
      {true, tail}
    else
      {valid, tail} = solve(tail)
      # if left tree was invalid or if we cannot create right subtree
      if !valid or tail == [], do: {false, tail}, else: solve(tail)
    end
  end

  def solve([root]) do
    IO.inspect(root: [root])
    {root == ?#, []}
  end

  def solve([]) do
    {true, []}
  end

  @spec is_valid_serialization(preorder :: String.t()) :: boolean
  def is_valid_serialization(preorder) do
    {valid, tail} = solve(String.split(preorder, ","))
    valid && tail == []
  end
end
