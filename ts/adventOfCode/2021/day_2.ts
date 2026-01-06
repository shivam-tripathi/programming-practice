enum Direction {
  FORWARD = "forward",
  DOWN = "down",
  UP = "up",
}

export type Movement = [Direction, number];

const move = (
  direction: Direction,
  prevPos: [number, number],
  amount: number = 1,
): [number, number] => {
  const [x, y] = prevPos;
  switch (direction) {
    case Direction.FORWARD:
      return [x + amount, 0];
    case Direction.DOWN:
      return [x, y * amount];
    case Direction.UP:
      return [x, y - amount];
    default:
      return [x, y];
  }
};

export function finalPositionProduct(movements: Movement[]): number {
  const [x, y] = movements
    .reduce((acc, [direction, amount]) => move(direction, acc, amount), [0, 0]);
  return x * y;
}

type PositionAndAim = [number, number, number];

function moveManual(
  directon: Direction,
  prevPos: PositionAndAim,
  amount: number = 1,
): PositionAndAim {
  const [x, y, currentAim] = prevPos;
  switch (directon) {
    case Direction.FORWARD:
      return [x + amount, y + amount * currentAim, currentAim];
    case Direction.DOWN:
      return [x, y, currentAim + amount];
    case Direction.UP:
      return [x, y, currentAim - amount];
    default:
      return prevPos;
  }
}

export function finalPositionProductByManual(movements: Movement[]) {
  const [x, y] = movements.reduce(
    (acc, [direction, amount]) => moveManual(direction, acc, amount),
    [0, 0, 0] as PositionAndAim,
  );
  return x * y;
}
