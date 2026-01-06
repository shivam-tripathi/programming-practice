defmodule Trie do
  @spec init_() :: any
  def init_() do
    Agent.start_link(fn -> %{} end, name: __MODULE__)
    Agent.update(__MODULE__, fn _ -> %{} end)
  end

  @spec insert(word :: String.t()) :: any
  def insert(word) do
    Agent.update(__MODULE__, fn trie ->
      Trie.handle_insert(trie, String.to_charlist(word))
    end)
  end

  def handle_insert(trie, chars) do
    if chars == [] do
      Map.put(trie, "leaf", true)
    else
      [key | tail] = chars
      next = Map.get(trie, key, %{})
      Map.put(trie, key, Trie.handle_insert(next, tail))
    end
  end

  @spec search(word :: String.t()) :: boolean
  def search(word) do
    Agent.get(__MODULE__, fn trie ->
      Trie.handle_search(trie, String.to_charlist(word))
    end)
  end

  def handle_search(trie, word) do
    if word == [] do
      Map.get(trie, "leaf", false)
    else
      [key | tail] = word

      Map.has_key?(trie, key) &&
        Trie.handle_search(Map.get(trie, key), tail)
    end
  end

  @spec starts_with(prefix :: String.t()) :: boolean
  def starts_with(prefix) do
    Agent.get(__MODULE__, fn trie ->
      Trie.handle_starts_with(trie, String.to_charlist(prefix))
    end)
  end

  def handle_starts_with(trie, prefix) do
    if prefix == [] do
      true
    else
      [key | tail] = prefix

      Map.has_key?(trie, key) &&
        Trie.handle_starts_with(Map.get(trie, key), tail)
    end
  end
end

# Your functions will be called as such:
# Trie.init_()
# Trie.insert(word)
# param_2 = Trie.search(word)
# param_3 = Trie.starts_with(prefix)

# Trie.init_ will be called before every test case, in which you can do some necessary initializations.
