export function longestCommonSubsequence(text1: string, text2: string): number {
  let prev = new Array(text2.length + 1).fill(0);
  let cur = new Array(text2.length + 1).fill(0);

  for (let i = 1; i < text1.length + 1; i++) {
    for (let j = 1; j < text2.length + 1; j++) {
      const match = text1[i - 1] === text2[j - 1] ? 1 : 0;
      cur[j] = Math.max(prev[j - 1] + match, Math.max(cur[j - 1], prev[j]));
    }
    const tmp = prev;
    prev = cur;
    cur = tmp;
  }

  return prev[text2.length];
}
