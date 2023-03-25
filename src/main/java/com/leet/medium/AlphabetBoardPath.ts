const map: { [ch: string]: number } = {
  "a": 0,
  "b": 1,
  "c": 2,
  "d": 3,
  "e": 4,
  "f": 5,
  "g": 6,
  "h": 7,
  "i": 8,
  "j": 9,
  "k": 10,
  "l": 11,
  "m": 12,
  "n": 13,
  "o": 14,
  "p": 15,
  "q": 16,
  "r": 17,
  "s": 18,
  "t": 19,
  "u": 20,
  "v": 21,
  "w": 22,
  "x": 23,
  "y": 24,
  "z": 25,
};

export function alphabetBoardPath(target: string): string {
  let curX = 0, curY = 0, cur = "a";
  let idx = 0;
  let ans = "";
  while (idx < target.length) {
    const next = target[idx];

    const nextX = Math.floor(map[next] / 5);
    const nextY = map[next] % 5;

    const diffX = nextX - curX;
    const diffY = nextY - curY;

    if (cur == "z") {
      ans += diffX < 0 ? "U".repeat(-diffX) : "D".repeat(diffX);
      ans += diffY < 0 ? "L".repeat(-diffY) : "R".repeat(diffY);
    } else {
      ans += diffY < 0 ? "L".repeat(-diffY) : "R".repeat(diffY);
      ans += diffX < 0 ? "U".repeat(-diffX) : "D".repeat(diffX);
    }

    ans += "!";

    curX = nextX;
    curY = nextY;
    cur = next;
    idx += 1;
  }

  return ans;
}
