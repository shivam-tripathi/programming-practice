defmodule Solution do
  def solve_node(adj, ans, path, node, dst) do
    if node == dst do
      [Enum.reverse([node | path]) | ans]
    else
      Map.get(adj, node)
      |> Enum.reduce(ans, fn sub, acc ->
        solve_node(adj, acc, [node | path], sub, dst)
      end)
    end
  end

  def solve_node_dp(adj, node, dp, dst) do
    cond do
      node == dst ->
        [[node]]

      Map.has_key?(dp, node) ->
        Map.get(dp)

      true ->
        res =
          Map.get(adj, node)
          |> Enum.reduce([], fn sub, acc ->
            solve_node(adj, sub, dp, dst)
            |> Enum.reduce(acc, fn lis, coll -> [[node | lis] | coll] end)
          end)

        Map.put(dp, node, res)
        res
    end
  end

  @spec all_paths_source_target(graph :: [[integer]]) :: [[integer]]
  def all_paths_source_target(graph) do
    {adj, size} =
      Enum.reduce(
        graph,
        {Map.new(), 0},
        fn sub, {adj, idx} -> {Map.put(adj, idx, sub), idx + 1} end
      )

    solve_node(adj, [], [], 0, size - 1)
  end
end
