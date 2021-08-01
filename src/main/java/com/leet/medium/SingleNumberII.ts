function singleNumber(nums: number[]): number {
  const digits: number[] = new Array(32).fill(0);
  nums.forEach((num) => {
    let pos = 0;
    while (num) {
      digits[pos++] += num & 1;
      num >>>= 1;
    }
  });

  return digits.reduce((acc: number, binary: number, index: number) => {
    return acc | (binary % 3 << index);
  }, 0);
}
