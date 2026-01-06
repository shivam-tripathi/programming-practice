class Solution:
    def solve(self, moves, lane, blocked):
        # if the current position is blocked or if it is impossible to jump to prev
        # position in the same lane
        if lane == blocked or moves[lane] == None:
            return None
        ans = []
        for i in range(1, 4):
            # if we can be on this lane previously
            if moves[i] != None:
                # if it's the same lane - cost to move is 0 else we have to jump to this lane
                # and then move forward
                ans.append(moves[i] + (1 if i != lane else 0))

        return min(moves) if len(ans) != 0 else None

    def minSideJumps(self, obstacles: List[int]) -> int:
        moves: dict[int, int] = {1: 0, 2: 0, 3: 0}
        for obstacle in obstacles:
            moves = {
                1: self.solve(moves, 1, obstacle),
                2: self.solve(moves, 2, obstacle),
                3: self.solve(moves, 3, obstacle),
            }

        return min(filter(lambda x: x is not None, moves.values()))
