function solve(nums: number[], pos: number, dp: Map<number, number>): number {
  if (pos >= nums.length) return 0;
  if (dp.has(pos)) return dp.get(pos) as number;
  const willRob = nums[pos] + solve(nums, pos + 2, dp);
  const willNotRob = solve(nums, pos + 1, dp);
  dp.set(pos, Math.max(willRob, willNotRob));
  return dp.get(pos) as number;
}

function rob(nums: number[]): number {
  return solve(nums, 0, new Map<number, number>());
}
