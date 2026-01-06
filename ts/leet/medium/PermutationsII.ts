function solve(
  nums: number[],
  pos: number,
  visited: Record<number, number>,
  state: number[],
  ans: number[][],
  unique: number[]
): number[][] {
  if (pos == nums.length) {
    ans.push(state.slice());
    return ans;
  }
  const processing = [];
  for (const num of unique) {
    if (visited[num] > 0) {
      processing.push(num);
      visited[num]--;
      state[pos] = num;
      solve(nums, pos + 1, visited, state, ans, unique);
      visited[num]++;
    }
  }
  return ans;
}

function permuteUnique(nums: number[]): number[][] {
  const uniqueSet = new Set<number>();
  const visited = nums.reduce((acc, cur) => {
    acc[cur] = acc[cur] ? acc[cur] + 1 : 1;
    uniqueSet.add(cur);
    return acc;
  }, {} as Record<number, number>);
  const unique = [...uniqueSet];
  return solve(nums, 0, visited, new Array<number>(nums.length), [], unique);
}
