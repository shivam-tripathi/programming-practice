function solve(
  str: string,
  words: string[],
  pos: number,
  failed: Set<number>
): boolean {
  if (pos >= str.length) return true;

  if (failed.has(pos)) {
    return false;
  }

  let ans = false;

  for (let word of words) {
    const sub = str.substring(pos, pos + word.length);
    if (sub === word) {
      ans = solve(str, words, pos + word.length, failed);
    }
    if (ans) return ans;
  }

  failed.add(pos);

  return false;
}

function wordBreak(s: string, words: string[]): boolean {
  return solve(s, words, 0, new Set());
}
