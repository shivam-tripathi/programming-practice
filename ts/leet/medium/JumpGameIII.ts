function canReach(arr: number[], start: number): boolean {
  const queue: number[] = [start];
  const set: Set<number> = new Set();
  while (queue.length != 0) {
    const idx: number = queue.shift() ?? -1;
    if (0 <= idx && idx < arr.length) {
      if (arr[idx] == 0) {
        return true;
      }
      if (!set.has(idx)) {
        set.add(idx);
        queue.push(idx - arr[idx]);
        queue.push(idx + arr[idx]);
      }
    }
  }
  return false;
}
