function check(
  s: string,
  prev: null | number,
  start: number,
  cache: Map<number, Map<number, number>>,
  collect: number[],
) {
  if (start >= s.length) {
    return true;
  }
  for (const [end, num] of cache.get(start)!) {
    if (start !== 0 && prev !== null && num !== (prev + 1)) {
      return false;
    }
    if (
      check(s, num, end, cache, [...collect, num]) &&
      !(start === 0 && end === s.length - 1)
    ) {
      console.log(">>", [...collect, num]);
      return true;
    }
  }
  return false;
}

function splitString(s: string): boolean {
  const cache = new Map<number, Map<number, number>>();
  for (let i = 0; i < s.length; i++) {
    for (let j = i; j < s.length; j++) {
      const num = parseInt(s.slice(i, j + 1));
      if (!cache.has(i)) {
        cache.set(i, new Map());
      }
      cache.get(i)?.set(j + 1, num);
    }
  }
  console.log(s, cache);
  return check(s, null, 0, cache, []);
}

for (const input of ["1234", "050043", "9080701"]) {
  console.log(splitString(input));
}
