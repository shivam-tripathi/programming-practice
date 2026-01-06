package com.leet.medium;

import java.util.Arrays;

/**
 * 1828. Queries on Number of Points Inside a Circle
 * https://leetcode.com/problems/queries-on-number-of-points-inside-a-circle/
 * Medium
 *
 * You are given an array points where points[i] = [xi, yi] is the coordinates of the ith point on a 2D plane. Multiple points can have the same coordinates.
 *
 * You are also given an array queries where queries[j] = [xj, yj, rj] describes a circle centered at (xj, yj) with a radius of rj.
 *
 * For each query queries[j], compute the number of points inside the jth circle. Points on the border of the circle are considered inside.
 *
 * Return an array answer, where answer[j] is the answer to the jth query.
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,3],[3,3],[5,3],[2,2]], queries = [[2,3,1],[4,3,1],[1,1,2]]
 * Output: [3,2,2]
 * Explanation: The points and circles are shown above.
 * queries[0] is the green circle, queries[1] is the red circle, and queries[2] is the blue circle.
 *
 * Example 2:
 *
 * Input: points = [[1,1],[2,2],[3,3],[4,4],[5,5]], queries = [[1,2,2],[2,2,2],[4,3,2],[4,3,3]]
 * Output: [2,3,2,4]
 * Explanation: The points and circles are shown above.
 * queries[0] is green, queries[1] is red, queries[2] is blue, and queries[3] is purple.
 *
 *
 *
 * Constraints:
 *
 *     1 <= points.length <= 500
 *     points[i].length == 2
 *     0 <= xi, yi <= 500
 *     1 <= queries.length <= 500
 *     queries[j].length == 3
 *     0 <= xj, yj <= 500
 *     1 <= rj <= 500
 *     All coordinates are integers.
 *
 *
 *
 * Follow up: Could you find the answer for each query in better complexity than O(n)?
 *
 * Ans: Use K-D tree, average case O(log n). NOT IMPLEMENTED HERE.
 */

public class QueriesOnNumberOfPointsInsideACircle {
  public int[] countPoints(int[][] points, int[][] queries) {
    int[] ans = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      var query = queries[i];
      int x = query[0], y = query[1];
      double r = Math.pow(query[2], 2);
      for (int[] point : points) {
        double dist = Math.pow(point[0] - x, 2) + Math.pow(point[1] - y, 2);
        if (dist <= r) {
          ans[i]++;
        }
      }
    }
    return ans;
  }

  public int[] countPointsLogn(int[][] points, int[][] queries) {
    int[] ans = new int[queries.length];

    return ans;
  }

  public static void main(String[] args) {
    var points = new int[][]{new int[]{1, 3}, new int[]{3, 3}, new int[]{5, 3}, new int[]{2, 2}};
    var queries = new int[][]{new int[]{2, 3, 1}, new int[]{4, 3, 1}, new int[]{1, 1, 2}};
    System.out.println(Arrays.toString(new QueriesOnNumberOfPointsInsideACircle().countPoints(points, queries)));
    points = new int[][]{new int[]{1, 1}, new int[]{2, 2}, new int[]{3, 3}, new int[]{4, 4}, new int[]{5, 5}};
    queries = new int[][]{
            new int[]{1, 2, 2}, new int[]{2, 2, 2}, new int[]{4, 3, 2}, new int[]{4, 3, 3}
    };
    System.out.println(Arrays.toString(new QueriesOnNumberOfPointsInsideACircle().countPoints(points, queries)));
  }
}
