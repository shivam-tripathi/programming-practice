package com.leet.medium;

import java.util.List;

/**
 * 841. Keys and Rooms
 * https://leetcode.com/problems/keys-and-rooms/
 * Medium
 *
 * There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room.
 *
 * Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.
 *
 * Initially, all the rooms start locked (except for room 0).
 *
 * You can walk back and forth between rooms freely.
 *
 * Return true if and only if you can enter every room.
 *
 * Example 1:
 *
 * Input: [[1],[2],[3],[]]
 * Output: true
 * Explanation:
 * We start in room 0, and pick up key 1.
 * We then go to room 1, and pick up key 2.
 * We then go to room 2, and pick up key 3.
 * We then go to room 3.  Since we were able to go to every room, we return true.
 *
 * Example 2:
 *
 * Input: [[1,3],[3,0,1],[2],[0]]
 * Output: false
 * Explanation: We can't enter the room with number 2.
 *
 * Note:
 *
 *     1 <= rooms.length <= 1000
 *     0 <= rooms[i].length <= 1000
 *     The number of keys in all rooms combined is at most 3000
 */

public class KeysAndRooms {
  int dfs(int room, boolean[] visited, List<List<Integer>> rooms, int done) {
    for (int i = 0; i < rooms.get(room).size(); i++) {
      int nextRoom = rooms.get(room).get(i);
      if (!visited[nextRoom]) {
        visited[nextRoom] = true;
        done++;
        done = dfs(nextRoom, visited, rooms, done);
      }
    }
    return done;
  }
  public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    if (rooms.size() == 0) return true;
    int size = rooms.size();
    boolean[] visited = new boolean[size];
    visited[0] = true;
    return dfs(0, visited, rooms, 1) == size;
  }
}
