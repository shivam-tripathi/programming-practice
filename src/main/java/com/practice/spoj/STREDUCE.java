package com.practice.spoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class STREDUCE {
  static Map<String, Integer> dp = new HashMap<>();
  static int solve(String inp) {
    if (inp.length() <= 2) return inp.length();
    if (dp.containsKey(inp)) return dp.get(inp);
    int ans = inp.length();

    for (int i = 0; i < inp.length() - 2; i++) {
      if (inp.charAt(i) == inp.charAt(i+2)) {
        ans = Math.min(ans, solve(inp.substring(0, i) + inp.charAt(i+1) + inp.substring(i+2)));
      }
    }
    dp.put(inp, ans);
    return ans;
  }

  public static void main(String[] args) throws IOException {
    var obj = new BufferedReader(new InputStreamReader(System.in));
    String inp = obj.readLine().trim();
    System.out.println(solve(inp));
  }
}
