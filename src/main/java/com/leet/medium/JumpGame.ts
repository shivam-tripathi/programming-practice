export function canJump(nums: number[]): boolean {
  const size = nums.length;
  let farthest = 0;
  let idx = 0;
  while (idx <= farthest && idx < size && farthest < (size - 1)) {
    farthest = Math.max(farthest, nums[idx] + idx);
    idx += 1;
  }
  return farthest >= (size - 1);
}
