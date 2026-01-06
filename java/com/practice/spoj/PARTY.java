package com.practice.spoj;

/**
 * PARTY - Party Schedule
 * https://www.spoj.com/problems/PARTY/
 * #dynamic-programming
 *
 * You just received another bill which you cannot pay because you lack the money.
 * Unfortunately, this is not the first time to happen, and now you decide to investigate the cause of your constant
 * monetary shortness. The reason is quite obvious: the lion's share of your money routinely disappears at the entrance
 * of party localities.
 *
 * You make up your mind to solve the problem where it arises, namely at the parties themselves. You introduce a limit
 * for your party budget and try to have the most possible fun with regard to this limit.
 *
 * You inquire beforehand about the entrance fee to each party and estimate how much fun you might have there. The list
 * is readily compiled, but how do you actually pick the parties that give you the most fun and do not exceed your
 * budget?
 *
 * Write a program which finds this optimal set of parties that offer the most fun. Keep in mind that your budget need
 * not necessarily be reached exactly. Achieve the highest possible fun level, and do not spend more money than is
 * absolutely necessary.
 * Input
 *
 * The first line of the input specifies your party budget and the number n of parties.
 *
 * The following n lines contain two numbers each. The first number indicates the entrance fee of each party. Parties
 * cost between 5 and 25 francs. The second number indicates the amount of fun of each party, given as an integer number
 * ranging from 0 to 10.
 *
 * The budget will not exceed 500 and there will be at most 100 parties. All numbers are separated by a single space.
 *
 * There are many test cases. Input ends with 0 0.
 * Output
 *
 * For each test case your program must output the sum of the entrance fees and the sum of all fun values of an optimal
 * solution. Both numbers must be separated by a single space.
 * Example
 *
 * Sample input:
 * 50 10
 * 12 3
 * 15 8
 * 16 9
 * 16 6
 * 10 2
 * 21 9
 * 18 4
 * 12 4
 * 17 8
 * 18 9
 *
 * 50 10
 * 13 8
 * 19 10
 * 16 8
 * 12 9
 * 10 2
 * 12 8
 * 13 5
 * 15 5
 * 11 7
 * 16 2
 *
 * 0 0
 *
 * Sample output:
 * 49 26
 * 48 32
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PARTY {

  public static int[] solve(int budget, List<List<Integer>> parties, int index, int[][][] dp) {
    if (budget <= 0 || index >= parties.size()) return new int[2];
    if (dp[budget][index] != null) return dp[budget][index];
    int[] with = new int[2];
    int cost = parties.get(index).get(0), fun = parties.get(index).get(1);
    if (budget >= cost) {
      var _with = solve(budget - cost, parties, index + 1, dp); // arrays are passed and returned by reference
      with[0] = cost + _with[0];
      with[1] = fun + _with[1];
    }
    int[] without = solve(budget, parties, index + 1, dp);
    int[] ans = (with[1] == without[1] && with[0] < without[0]) || (with[1] > without[1]) ? with : without;
    dp[budget][index] = ans;
    return ans;
  }

  public static int[] solve(int budget, List<List<Integer>> parties) {
    int[][][] dp = new int[budget + 1][parties.size()][2];
    for (int[][] ints : dp) {
      Arrays.fill(ints, null);
    }
    return solve(budget, parties, 0, dp);
  }

  public static void test() {
    int budget = 50;
    var parties = List.of(
            List.of(12, 3),
            List.of(15, 8),
            List.of(16, 9),
            List.of(16, 6),
            List.of(10, 2),
            List.of(21, 9),
            List.of(18, 4),
            List.of(12, 4),
            List.of(17, 8),
            List.of(18, 9)
    );
    int[] solved = solve(budget, parties);
    System.out.println(Arrays.toString(solved));
    parties = List.of(
            List.of(13, 8),
            List.of(19, 10),
            List.of(16, 8),
            List.of(12, 9),
            List.of(10, 2),
            List.of(12, 8),
            List.of(13, 5),
            List.of(15, 5),
            List.of(11, 7),
            List.of(16, 2)
    );
    solved = solve(budget, parties);
    System.out.println(Arrays.toString(solved));
  }

  public static List<Integer> readIntegers(BufferedReader obj) throws IOException {
    String line;
    do {
      line = obj.readLine().trim();
    } while (line.length() == 0);
    return Arrays.stream(line.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
  }

  public static void run() throws IOException {
    var obj = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      var inp = readIntegers(obj);
      int budget = inp.get(0), size = inp.get(1);
      if (budget == 0 && size == 0) {
        return;
      }
      var parties = new ArrayList<List<Integer>>();
      for (int i = 0; i < size; i++) {
        inp = readIntegers(obj);
        parties.add(inp);
      }
      int[] solved = solve(budget, parties);
      System.out.println(solved[0] + " " + solved[1]);
    }
  }

  public static void main(String[] args) throws IOException {
//    test();
    run();
  }
}
