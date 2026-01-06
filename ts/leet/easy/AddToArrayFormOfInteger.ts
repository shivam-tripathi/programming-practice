function addToArrayForm(num: number[], k: number): number[] {
  let carry = 0;
  let idx = num.length - 1;
  const ans = [];
  while ((idx >= 0) || carry || k) {
    let sum = carry + (k % 10) + (num[idx--] ?? 0);
    if (sum >= 10) {
      carry = 1;
      sum = sum % 10;
    } else {
      carry = 0;
    }
    ans.push(sum);
    k = Math.floor(k / 10);
  }
  return ans.reverse();
}
