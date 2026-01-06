const mapping = new Set<string>();
for (let i = 1; i <= 26; i++) {
  mapping.add(i.toString());
}

function solve(s: string, idx: number, dp: Map<number, number>): number {
  if (idx === s.length) {
    return 1;
  }
  if (dp.has(idx)) {
    return dp.get(idx) as number;
  }
  let ans = 0;
  if (mapping.has(s[idx])) {
    ans += solve(s, idx + 1, dp);
  }
  if ((idx + 1) < s.length && mapping.has(s[idx] + s[idx + 1])) {
    ans += solve(s, idx + 2, dp);
  }
  dp.set(idx, ans);
  return ans;
}

export function numDecodings(s: string): number {
  if (s.length === 0) {
    return 0;
  }
  return solve(s, 0, new Map());
}
