class TrieNode {
  tries: { [key: string]: TrieNode } = {};
  leaf?: string;
}

class Trie {
  root: TrieNode = new TrieNode();

  insert(word: string) {
    let node = this.root;
    for (const ch of word) {
      if (!node.tries[ch]) {
        node.tries[ch] = new TrieNode();
      }
      node = node.tries[ch];
    }
    node.leaf = word;
  }

  private match_internal(
    node: TrieNode,
    word: string,
    index: number,
  ): string | null {
    let cur = node;
    for (let i = index; i < word.length; i++) {
      if (!cur.tries[word[i]]) {
        return null;
      }
      cur = cur.tries[word[i]];
    }
    return cur.leaf || null;
  }

  match_edit_distance(word: string, target: string) {
    let node = this.root;
    const moves: string[] = [];
    for (let index = 0; node && index < word.length - 1; index++) {
      for (let variance = 0; variance < 26; variance++) {
        const base = String.fromCharCode(97 + variance);
        if (base !== word[index] && node.tries[base]) {
          const match = this.match_internal(
            node.tries[base],
            word,
            index + 1,
          );
          if (match) {
            moves.push(match);
          }
          // early success
          if (match === target) {
            return moves;
          }
        }
      }
      node = node.tries[word[index]];
    }
    return moves;
  }
}

function findLadders(
  beginWord: string,
  endWord: string,
  wordList: string[],
): string[][] {
  const trie = new Trie();
  wordList.forEach((word) => trie.insert(word));

  let paths = [[beginWord]];
  const seen = new Set<string>();

  while (true) {
    for (const path of paths) {
      const last_seen = path[path.length - 1];
      if (last_seen === endWord) {
        return path;
      }
      const moves = trie.match_edit_distance(word, endWord);
      // if (moves[moves.length - 1] === endWord) {
      // }
    }
  }
  return [];
}
