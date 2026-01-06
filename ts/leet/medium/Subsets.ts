function subsets(nums: number[]): number[][] {
  let prev: number[][] = [[]];
  for (let i = nums.length - 1; i >= 0; i--) {
    const next = prev.reduce((acc, arr) => {
      acc.push(arr);
      acc.push([nums[i], ...arr]);
      return acc;
    }, [] as number[][]);
    prev = next;
  }
  return nums.length == 0 ? [] : prev;
}
