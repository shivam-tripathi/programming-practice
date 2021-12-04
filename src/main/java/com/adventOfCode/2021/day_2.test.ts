import { finalPositionProduct, Movement } from "./day_2.ts";
import { assertEquals } from "https://deno.land/std/testing/asserts.ts";

const MOVEMENTS = [["forward", 5], ["down", 5], ["forward", 8], ["up", 3], [
  "down",
  8,
], ["forward", 2]] as Movement[];

const text = await Deno.readTextFile(
  new URL("./input/day_2.txt", import.meta.url),
);
const movements = text.split("\n").map((s) => s.split(" ")).map(([
  direction,
  amount,
]) => [direction, parseInt(amount, 10)]) as Movement[];

Deno.test({
  name: "# Day 2 Part 1: Find the position product",
  fn: () => {
    const ans = finalPositionProduct(MOVEMENTS);
    assertEquals(ans, 150);
  },
});

Deno.test({
  name: "# Day 2 Part 1: Find the position product {{ Original Input }}",
  fn: () => {
    const ans = finalPositionProduct(movements);
    console.log({ ans });
  },
});
