package com.practice.spoj;

/**
 * https://www.spoj.com/problems/HERDING/
 * HERDING - Herding
 * #bfs
 *
 * Oh no! A number of stray cats have been let loose in the city, and as the City Cat Catcher, you have been assigned
 * the vital task of retrieving all of the cats. This is an ideal opportunity to test your latest invention, a cat trap
 * which is guaranteed to retrieve every cat which walks into a square-shaped subsection of the city.
 *
 * Fortunately, you have the assistance of one of the world's foremost cat psychologists, who has the amazing ability of
 * predicting, given a square subsection of the city, exactly which of the four cardinal directions (north, east, south
 * or west) the cat will head. While this information is handy, you still don't know where all the cats currently are.
 *
 * In order to prove the cost-effectiveness of your method to the City it would, of course, be important to minimize the
 * number of traps used.
 *
 * Input
 *
 * The input will begin with a line consisting of two numbers n and m, separated by a space (1 ≤ n, m ≤ 1000). The city
 * will be an n x m grid of square subsections. The next n lines will each consist of a string of length m, consisting
 * of the letters 'N', 'E', 'S', or 'W', representing north, east, south and west, respectively. (The first character of
 * the first line will be the northwesternmost point.) The direction in the square is the direction which cats will head
 * if they are in that square. The cat psychologist assures you that cats have no interest in leaving the city.
 * Output
 *
 * Output the minimum number of traps needed.
 * Example
 *
 * Input:
 * 3 4
 * SWWW
 * SEWN
 * EEEN
 *
 * Output:
 * 2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HERDING {
  static int rows;
  static int cols;
  static List<String[]> matrix;
  static boolean[] visited;
  static int[][] colors;
  static int color;

  static void floodFill(int row, int col, String expected) {
    if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row * cols + col]) return;
    String curVal = matrix.get(row)[col];
    if (!expected.equals("any") && !curVal.equals(expected)) {
      return;
    }
    visited[row * cols + col] = true;
    colors[row][col] = color;
    floodFill(row - 1, col, "S");
    floodFill(row + 1, col, "N");
    floodFill(row, col - 1, "E");
    floodFill(row, col + 1, "W");
    int childRow = row + (curVal.equals("S") ? 1 : curVal.equals("N") ? -1 : 0);
    int childCol = col + (curVal.equals("E") ? 1 : curVal.equals("W") ? -1 : 0);
    floodFill(childRow, childCol, "any");
  }

  static int solve(List<String[]> matrix) {
//    matrix.forEach(m -> System.out.println(Arrays.stream(m).reduce("", (a, v) -> a + "-" + v)));
    HERDING.matrix = matrix;
    rows = matrix.size();
    cols = matrix.get(0).length;
    visited = new boolean[rows * cols];
    colors = new int[rows][cols];
    color = 0;
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        if (!visited[row * cols + col]) {
          color++;
          floodFill(row, col, "any");
        }
      }
    }
//    Arrays.stream(colors).forEach(colorRow -> System.out.println(Arrays.toString(colorRow)));
    return color;
  }

  public static void test() {
    var matrix = List.of(
            "SWWW".split(""),
            "SEWW".split(""),
            "EEEN".split("")
    );
    System.out.println(solve(matrix));
    matrix = List.of(
            "SWWW".split(""),
            "SEEW".split(""),
            "SSWS".split(""),
            "NNWN".split("")
    );
    System.out.println(solve(matrix));

    List<String[]> matrix2 = new ArrayList<>();
    matrix2.add(new String[]{"S"});
    System.out.println(solve(matrix2));

    matrix = List.of(
            "SWWW".split(""),
            "SEES".split(""),
            "SNWS".split(""),
            "ENWN".split("")
    );
    System.out.println(solve(matrix));
  }

  public static void main(String[] args) throws IOException {
//    test();
    var obj = new BufferedReader(new InputStreamReader(System.in));
    String[] inp = obj.readLine().split(" ");
    int n = Integer.parseInt(inp[0]), m = Integer.parseInt(inp[1]);
    List<String[]> matrix = new ArrayList<String[]>();
    for (int i = 0; i < n; i++) {
      inp = obj.readLine().split("");
      matrix.add(inp);
    }
    System.out.println(solve(matrix));
  }
}
