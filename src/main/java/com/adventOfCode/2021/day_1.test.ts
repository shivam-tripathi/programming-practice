import { sonarSweep } from "./day_1.ts";
import { assertEquals } from "https://deno.land/std/testing/asserts.ts";

Deno.test({
  name: "# Day 1: Sonar Sweep Test Input",
  fn: async () => {
    const result = await sonarSweep([
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
    ]);
    assertEquals(result, 7);
  },
});

Deno.test({
  name: "# Day 1: Sonar Sweep Original Input",
  fn: async () => {
    const ans = await sonarSweep(
      (await Deno.readTextFile(
        new URL("./input/sonar_sweep.txt", import.meta.url),
      ))
        .split("\n")
        .map((s) => parseInt(s, 10)),
    );
    console.log({ ans });
  },
});
