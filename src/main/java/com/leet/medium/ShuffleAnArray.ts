class Solution {
  nums: number[];
  constructor(nums: number[]) {
    this.nums = nums;
  }

  reset(): number[] {
    return this.nums;
  }

  shuffle(): number[] {
    const cpy = this.nums.map((i) => i);

    for (let i = 0; i < this.nums.length; i++) {
      const rpos = i + Math.floor(Math.random() * (cpy.length - i));
      const tmp = cpy[i];
      cpy[i] = cpy[rpos];
      cpy[rpos] = tmp;
    }

    return cpy;
  }
}

/**
 * Your Solution object will be instantiated and called as such:
 * var obj = new Solution(nums)
 * var param_1 = obj.reset()
 * var param_2 = obj.shuffle()
 */
