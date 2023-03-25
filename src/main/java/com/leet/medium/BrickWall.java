package com.leet.medium;

import java.util.HashMap;
import java.util.List;

public class BrickWall {
  public int leastBricks(List<List<Integer>> wall) {
    var edges = new HashMap<Integer, Integer>();
    int end = 0;
    for (var bricks : wall) {
      int pos = 0;
      for (var brickWidth : bricks) {
        pos += brickWidth;
        edges.merge(pos, 1, Integer::sum);
      }
      end = pos;
    }

    int ans = 0;
    for (var entry : edges.entrySet()) {
      if (entry.getKey() == end) continue;
      ans = Math.max(ans, entry.getValue());
    }
    return wall.size() - ans;
  }
}
