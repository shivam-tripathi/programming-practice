function numPairsDivisibleBy60(time: number[]): number {
  const mapping = new Array(60).fill(0);
  const nC2 = (x: number) => Math.floor(x * (x - 1) / 2);
  time.forEach((t) => mapping[t % 60]++);
  let ans = nC2(mapping[30]) + nC2(mapping[0]);
  for (let i = 1; i < 30; i++) {
    ans += mapping[i] * mapping[60 - i];
  }
  return ans;
}
