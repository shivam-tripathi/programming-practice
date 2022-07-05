/**
 Do not return anything, modify nums in-place instead.
 */
const RED = 0;
const WHITE = 1;
const BLUE = 2;

function sortColors(nums: number[]): void {
  let red = 0, blue = nums.length - 1, ptr = 0;

  const swap = (x: number, y: number) => {
    const tmp = nums[x];
    nums[x] = nums[y];
    nums[y] = tmp;
  };

  while (ptr <= blue) {
    if (nums[ptr] === BLUE) {
      swap(ptr, blue--);
    } else if (nums[ptr] === WHITE) {
      ptr++;
    } else if (nums[ptr] == RED) {
      swap(ptr++, red++);
    }
  }
}
