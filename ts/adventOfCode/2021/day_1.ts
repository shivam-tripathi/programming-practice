// deno run --allow-read=$(pwd) day_1.ts

export function sonarSweep(depths: number[]) {
  return depths.reduce((acc, depth, index, depths) => {
    return (index != 0 && depth > depths[index - 1]) ? acc + 1 : acc;
  }, 0);
}

export function sonarSweepSlidingWindow(
  depths: number[],
  windowSize = 3,
): number {
  const windows: Array<number> = [];

  let running = 0;
  if (windowSize <= depths.length) {
    for (let i = 0; i < windowSize - 1; i++) {
      running += depths[i];
    }
  }

  for (let i = 2; i < depths.length; i++) {
    running += depths[i];
    windows.push(running);
    running -= depths[i - 2];
  }

  return sonarSweep(windows);
}
