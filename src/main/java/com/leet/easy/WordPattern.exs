defmodule Solution do
  def solve(words, patterns, map) do
    cond do
      words == [] && patterns == [] ->
        true

      words != [] && patterns == [] ->
        false

      words == [] && patterns != [] ->
        false

      true ->
        [word | words] = words
        [pattern | patterns] = patterns

        if Map.has_key?(map, pattern) && Map.get(map, pattern) != word do
          false
        else
          map = Map.put(map, pattern, word)
          solve(words, patterns, map)
        end
    end
  end

  @spec word_pattern(pattern :: String.t(), s :: String.t()) :: boolean
  def word_pattern(pattern, s) do
    solve(String.split(s, " "), pattern, Map.new())
  end
end
