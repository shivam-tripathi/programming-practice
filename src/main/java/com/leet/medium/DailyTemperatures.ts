function dailyTemperatures(temperatures: number[]): number[] {
  const stack: number[] = [];
  const ans: number[] = new Array(temperatures.length);
  for (let i = 0; i < temperatures.length; i++) {
    const temperature = temperatures[i];
    while (
      stack.length > 0 && temperatures[stack[stack.length - 1]] < temperature
    ) {
      ans[stack[stack.length - 1]] = i - stack[stack.length - 1];
      stack.pop();
    }
    stack.push(i);
  }

  for (let i = 0; i < stack.length; i++) {
    ans[stack[i]] = 0;
  }

  return ans;
}
