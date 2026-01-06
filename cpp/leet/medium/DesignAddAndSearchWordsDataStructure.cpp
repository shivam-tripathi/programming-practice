#include <unordered_map>
#include <string>
using namespace std;

class TrieNode
{
public:
	TrieNode *sub[26] = {nullptr};
	bool leaf = false;
};

class WordDictionary
{
	unordered_map<int, TrieNode *> roots;

public:
	WordDictionary() {}

	TrieNode *get_root(int idx)
	{
		if (this->roots.find(idx) == this->roots.end())
		{
			this->roots[idx] = new TrieNode();
		}
		return this->roots[idx];
	}

	void addWord(string word)
	{
		TrieNode *node = this->get_root(word.size());
		for (auto ch : word)
		{
			if (node->sub[ch - 'a'] == nullptr)
			{
				node->sub[ch - 'a'] = new TrieNode();
			}
			node = node->sub[ch - 'a'];
		}
		node->leaf = true;
	}

	bool match(TrieNode *node, string word, int pos)
	{
		if (pos >= word.size())
		{
			if (node != nullptr)
			{
				return node->leaf;
			}
			return false;
		}
		if (node == nullptr)
		{
			return false;
		}
		char ch = word[pos];
		if (ch != '.')
		{
			return match(node->sub[ch - 'a'], word, pos + 1);
		}
		for (auto subnode : node->sub)
		{
			if (subnode != nullptr && match(subnode, word, pos + 1))
			{
				return true;
			}
		}
		return false;
	}

	bool search(string word)
	{
		return this->match(this->get_root(word.size()), word, 0);
	}
};

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary* obj = new WordDictionary();
 * obj->addWord(word);
 * bool param_2 = obj->search(word);
 */