import { assertEquals } from "https://deno.land/std/testing/asserts.ts";
import { binaryDiagnostic, lifeSupportRating } from "./day_3.ts";

const DATA = [
  "00100",
  "11110",
  "10110",
  "10111",
  "10101",
  "01111",
  "00111",
  "11100",
  "10000",
  "11001",
  "00010",
  "01010",
];
const text = await Deno.readTextFile(
  new URL("./input/day_3.txt", import.meta.url),
);
const data = text.split("\n");

Deno.test({
  name: "# Day 3 Part 1: Diagnose",
  fn: () => {
    const ans = binaryDiagnostic(DATA);
    assertEquals(ans, 198);
  },
});

Deno.test({
  name: "# Day 3 Part 1: Diagnose {{ Original Input }}",
  fn: () => {
    const ans = binaryDiagnostic(data);
    assertEquals(ans, 1071734);
  },
});

Deno.test({
  name: "# Day 3 Part 2: Life Support",
  fn: () => {
    const ans = lifeSupportRating(DATA);
    assertEquals(ans, 230);
  },
});

Deno.test({
  name: "# Day 3 Part 2: Life Support {{ Original Input }}",
  fn: () => {
    const ans = lifeSupportRating(data);
    console.log({ ans });
  },
});
