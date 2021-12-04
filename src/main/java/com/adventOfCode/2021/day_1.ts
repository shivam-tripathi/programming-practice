// deno run --allow-read=$(pwd) day_1_sonar_sweep.ts

export async function sonarSweep(nums: number[]) {
  return nums.reduce((acc, depth, index, depths) => {
    return (index != 0 && depth > depths[index - 1]) ? acc + 1 : acc;
  }, 0);
}
