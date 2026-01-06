defmodule Solution do
  def solve(queue, arr, visited, len) do
    if :queue.len(queue) == 0 do
      false
    else
      {{_, head}, queue} = :queue.out(queue)

      if MapSet.member?(visited, head) or head < 0 or head >= len do
        solve(queue, arr, visited, len)
      else
        e = :array.get(head, arr)

        if e == 0 do
          true
        else
          visited = MapSet.put(visited, head)
          queue = :queue.in(head + e, queue)
          queue = :queue.in(head - e, queue)
          solve(queue, arr, visited, len)
        end
      end
    end
  end

  @spec can_reach(arr :: [integer], start :: integer) :: boolean
  def can_reach(arr, start) do
    res = solve(:queue.from_list([start]), :array.from_list(arr), MapSet.new(), length(arr))
    res
  end
end
