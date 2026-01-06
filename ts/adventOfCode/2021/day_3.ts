function bitCount(data: string[]): number[][] {
  const count: number[][] = [];
  data.forEach((datum) => {
    datum.split("").forEach((bit, idx) => {
      if (!count[idx]) count[idx] = [0, 0] as number[];
      count[idx][bit == "0" ? 0 : 1]++;
    });
  });
  return count;
}

export function binaryDiagnostic(data: string[]) {
  const count = bitCount(data);
  let base = 1;
  let gamma = 0, epsilon = 0;
  for (let i = count.length - 1; i >= 0; i--) {
    const [major, minor] = count[i][0] > count[i][1] ? [0, 1] : [1, 0];
    gamma += base * major;
    epsilon += base * minor;
    base *= 2;
  }

  return gamma * epsilon;
}

export function lifeSupportRating(data: string[]) {
  const bits = data.length > 0 ? data[0].length : 0;
  const o2Expected = (count: [number, number][]) =>
    count.map((c) => c[0] > c[1] ? "0" : "1");
  const co2Expected = (count: [number, number][]) =>
    count.map((c) => c[1] < c[0] ? "1" : "0");

  const [o2, co2] = [o2Expected, co2Expected].map((expectedfn) => {
    let stack = data, idx = 0;
    const count = bitCount(stack) as [number, number][];
    while (stack.length > 1 && idx < bits) {
      const expected = expectedfn(count);
      stack = stack.filter((datum) => {
        if (datum[idx] !== expected[idx]) {
          datum.split("").forEach((ch, idx) => {
            count[idx][ch == "0" ? 0 : 1]--;
          });
          return false;
        }
        return true;
      });
      idx++;
    }
    return stack[0];
  });

  return parseInt(o2, 2) * parseInt(co2, 2);
}
