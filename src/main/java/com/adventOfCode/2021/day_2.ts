enum Direction {
  FORWARD = "forward",
  DOWN = "down",
  UP = "up",
}

export type Movement = [Direction, number];

const move = (direction: Direction, amount: number = 1) => {
  switch (direction) {
    case Direction.FORWARD:
      return [1 * amount, 0];
    case Direction.DOWN:
      return [0, 1 * amount];
    case Direction.UP:
      return [0, -1 * amount];
    default:
      return [0, 0];
  }
};

export function finalPositionProduct(movements: Movement[]): number {
  const [x, y] = movements
    .map(([direction, amount]) => move(direction, amount))
    .reduce((acc, [moveX, moveY]) => [acc[0] + moveX, acc[1] + moveY], [0, 0]);
  return x * y;
}
