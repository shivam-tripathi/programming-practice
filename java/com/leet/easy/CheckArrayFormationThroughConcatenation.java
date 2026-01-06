package com.leet.easy;

/**
 * 1640. Check Array Formation Through Concatenation
 * https://leetcode.com/problems/check-array-formation-through-concatenation/
 * Easy
 * You are given an array of distinct integers arr and an array of integer arrays pieces, where the integers in pieces
 * are distinct. Your goal is to form arr by concatenating the arrays in pieces in any order. However, you are not
 * allowed to reorder the integers in each array pieces[i].
 * Return true if it is possible to form the array arr from pieces. Otherwise, return false.
 * Example 1:
 * Input: arr = [85], pieces = [[85]]
 * Output: true
 * Example 2:
 * Input: arr = [15,88], pieces = [[88],[15]]
 * Output: true
 * Explanation: Concatenate [15] then [88]
 * Example 3:
 * Input: arr = [49,18,16], pieces = [[16,18,49]]
 * Output: false
 * Explanation: Even though the numbers match, we cannot reorder pieces[0].
 * Example 4:
 * Input: arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
 * Output: true
 * Explanation: Concatenate [91] then [4,64] then [78]
 * Example 5:
 * Input: arr = [1,3,5,7], pieces = [[2,4,6,8]]
 * Output: false
 * Constraints:
 *     1 <= pieces.length <= arr.length <= 100
 *     sum(pieces[i].length) == arr.length
 *     1 <= pieces[i].length <= arr.length
 *     1 <= arr[i], pieces[i][j] <= 100
 *     The integers in arr are distinct.
 *     The integers in pieces are distinct (i.e., If we flatten pieces in a 1D array, all the integers in this array are distinct).
 */

public class CheckArrayFormationThroughConcatenation {
  public int matchArrays(int[] inp, int[] against, int startIdx) {
    int i;
    for (i = 0; i < inp.length && (i + startIdx) < against.length; i++) {
      if (inp[i] != against[i + startIdx]) break;
    }
    return i;
  }

  public boolean concatArrays(int[] arr, int matchFrom, int[][] pieces, int pieceIdx, boolean[] visited) {
    if (pieces[pieceIdx].length > arr.length) return false; // if current piece is bigger than arr, ignore
    int matchedTillIdx = matchArrays(pieces[pieceIdx], arr, matchFrom); // get matchedTill
    if (matchedTillIdx != pieces[pieceIdx].length) return false; // didn't get completely matched
    if ((matchFrom + matchedTillIdx) == arr.length) return true; //
    visited[pieceIdx] = true;
    for (int j = 0; j < pieces.length; j++) {
      if (!visited[j] && pieces[j].length <= (arr.length - matchedTillIdx)) {
        if (concatArrays(arr, matchFrom + matchedTillIdx, pieces, j, visited)) {
          return true;
        }
      }
    }
    visited[pieceIdx] = false;
    return false;
  }

  public boolean canFormArray(int[] arr, int[][] pieces) {
    boolean[] visited = new boolean[pieces.length];
    for (int i = 0; i < pieces.length; i++) {
      if (concatArrays(arr, 0, pieces, i, visited)) return true;
    }
    return false;
  }

  public static void main(String[] args) {
    CheckArrayFormationThroughConcatenation obj = new CheckArrayFormationThroughConcatenation();
    int[] arr = new int[]{91, 4, 64, 78};
    int[][] pieces = new int[][]{new int[]{78}, new int[]{4, 64}, new int[]{91}};
    boolean ans = obj.canFormArray(arr, pieces);
    System.out.println(ans);
  }
}
