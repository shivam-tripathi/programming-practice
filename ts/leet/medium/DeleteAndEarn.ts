function solve(
  arr: number[],
  pos: number,
  count: Record<number, number>,
  dp: Record<number, number>,
): number {
  if (pos >= arr.length) return 0;
  const num = arr[pos];
  if (dp[num] !== undefined) {
    return dp[num];
  }
  let ans = num * count[num];
  if (pos < arr.length - 1) {
    const nextPos = arr[pos + 1] == num + 1 ? pos + 2 : pos + 1;
    ans += solve(arr, nextPos, count, dp);
  }
  const without = solve(arr, pos + 1, count, dp);
  dp[num] = Math.max(ans, without);
  return dp[num];
}

function deleteAndEarn(nums: number[]): number {
  const count: Record<number, number> = {};
  const sorted: number[] = [];
  nums.sort((a, b) => a - b);
  for (let num of nums) {
    if (!count[num]) {
      count[num] = 0;
      sorted.push(num);
    }
    count[num]++;
  }
  let ans = 0;
  let dp: Record<number, number> = {};
  for (let i = 0; i < sorted.length; i++) {
    ans = Math.max(solve(sorted, i, count, dp), ans);
  }
  return ans;
}
