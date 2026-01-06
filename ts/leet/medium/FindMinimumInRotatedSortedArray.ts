function findMin(nums: number[]): number {
  const size = nums.length;
  if (size == 0) {
    return -1;
  }

  const n = nums[size - 1];

  let low = 0,
    high = size - 1;
  while (low < high) {
    const mid = low + Math.floor((high - low) / 2);
    const elem = nums[mid];
    if (elem > n) {
      low = mid + 1;
    } else {
      if (
        (mid == 0 || elem < nums[mid - 1]) &&
        (mid == size - 1 || elem < nums[mid + 1])
      ) {
        return elem;
      } else {
        high = mid - 1;
      }
    }
  }

  return nums[low];
}
