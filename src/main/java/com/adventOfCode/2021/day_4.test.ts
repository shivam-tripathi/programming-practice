import { bingo, bingoWinLast } from "./day_4.ts";
import { assertEquals } from "https://deno.land/std/testing/asserts.ts";

const NUMS = [
  7,
  4,
  9,
  5,
  11,
  17,
  23,
  2,
  0,
  14,
  21,
  24,
  10,
  16,
  13,
  6,
  15,
  25,
  12,
  22,
  18,
  20,
  8,
  19,
  3,
  26,
  1,
];
const BOARDS = [[
  [22, 13, 17, 11, 0],
  [8, 2, 23, 4, 24],
  [21, 9, 14, 16, 7],
  [6, 10, 3, 18, 5],
  [1, 12, 20, 15, 19],
], [
  [3, 15, 0, 2, 22],
  [9, 18, 13, 17, 5],
  [19, 8, 7, 25, 23],
  [20, 11, 10, 24, 4],
  [14, 21, 16, 12, 6],
], [
  [14, 21, 17, 24, 4],
  [10, 16, 15, 9, 19],
  [18, 8, 23, 26, 20],
  [22, 11, 13, 6, 5],
  [2, 0, 12, 3, 7],
]];

const file = await Deno.readTextFile(
  new URL("./input/day_4.txt", import.meta.url),
);

const [selects, ...boardsRaw] = file.split("\n\n");
const nums = selects.split(",").map((elem) => parseInt(elem, 10));
const boards = boardsRaw.map((boardRaw) =>
  boardRaw.split("\n").map((row) =>
    row.trim().split(/\s+/).map((elem) => parseInt(elem.trim(), 10))
  )
);

Deno.test({
  name: "# Day 4: Bingo",
  fn: () => {
    const ans = bingo(BOARDS, NUMS);
    console.log({ ans });
    assertEquals(ans, 4512);
  },
});

Deno.test({
  name: "# Day 4: Bingo {{ Original Input }}",
  fn: () => {
    const ans = bingo(boards, nums);
    console.log({ ans });
  },
});

Deno.test({
  name: "# Day 4: Bingo Win Last",
  fn: () => {
    const ans = bingoWinLast(BOARDS, NUMS);
    console.log({ ans });
    assertEquals(ans, 1924);
  },
});

Deno.test({
  name: "# Day 4: Bingo Win Last {{ Original Input }}",
  fn: () => {
    const ans = bingoWinLast(boards, nums);
    console.log({ ans });
  },
});
