package com.leet.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NQueens {
  Set<Integer> invalidRow = new HashSet<>();
  Set<Integer> invalidCol = new HashSet<>();
  Set<Integer> invalidDiagonal = new HashSet<>();
  Set<Integer> invalidReverseDiagonal = new HashSet<>();
  int size;

  void solve(int row, List<List<String>> board, List<List<String>> ans, int count) {
    if (row < 0 || row >= size) {
      return;
    }

    for (int col = 0; col < size; col++) {
      boolean invalid = invalidRow.contains(row) || invalidCol.contains(col) || invalidDiagonal.contains(row - col)
          || invalidReverseDiagonal.contains(row + col);
      if (!invalid) {
        board.get(row).set(col, "Q");
        if (count + 1 == size) {
          ans.add(board.stream().map(r -> String.join("", r)).collect(Collectors.toList()));
          board.get(row).set(col, ".");
        } else {
          invalidRow.add(row);
          invalidCol.add(col);
          invalidDiagonal.add(row - col);
          invalidReverseDiagonal.add(row + col);
          solve(row + 1, board, ans, count + 1);
          invalidRow.remove(row);
          invalidCol.remove(col);
          invalidDiagonal.remove(row - col);
          invalidReverseDiagonal.remove(row + col);
        }
        board.get(row).set(col, ".");
      }
    }
    solve(row + 1, board, ans, count);
  }

  public List<List<String>> solveNQueens(int n) {
    if (n == 0)
      return List.of();
    size = n;
    List<List<String>> board = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      String[] row = new String[n];
      Arrays.fill(row, ".");
      board.add(Arrays.asList(row));
    }

    List<List<String>> ans = new ArrayList<>();
    solve(0, board, ans, 0);
    return ans;
  }

  public static void main(String[] args) {
    var o = new NQueens();
    IntStream.range(1, 10).forEach(size -> {
      var ans = o.solveNQueens(size);
      System.out.println(ans);
    });
  }
}
