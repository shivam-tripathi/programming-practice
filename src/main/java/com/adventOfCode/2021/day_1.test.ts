import { sonarSweep, sonarSweepSlidingWindow } from "./day_1.ts";
import { assertEquals } from "https://deno.land/std/testing/asserts.ts";

const SONAR_DEPTHS = [
  199,
  200,
  208,
  210,
  200,
  207,
  240,
  269,
  260,
  263,
];

const text = await Deno.readTextFile(
  new URL("./input/day_1.txt", import.meta.url),
);
const depths = text.split("\n").map((s) => parseInt(s, 10));

Deno.test({
  name: "# Day 1 Part 1: Sonar Sweep",
  fn: async () => {
    const result = sonarSweep(SONAR_DEPTHS);
    assertEquals(result, 7);
  },
});

Deno.test({
  name: "# Day 1 Part 1: Sonar Sweep {{Original Input}}",
  fn: async () => {
    const ans = sonarSweep(depths);
    console.log({ ans });
  },
});

Deno.test({
  name: "# Day 1 Part 2: Sonar Sweep Sliding Window",
  fn: () => {
    const ans = sonarSweepSlidingWindow(SONAR_DEPTHS);
    console.log(ans);
    assertEquals(ans, 5);
  },
});

Deno.test({
  name: "# Day 1 Part 2: Sonar Sweep Sliding Window {{Original Input}}",
  fn: async () => {
    const ans = sonarSweepSlidingWindow(depths);
    console.log({ ans });
  },
});
