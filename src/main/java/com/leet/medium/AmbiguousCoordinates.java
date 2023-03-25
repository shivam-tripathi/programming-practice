package com.leet.medium;

import java.util.*;

public class AmbiguousCoordinates {
  public static List<String> addDecimal(String s) {
    if (s.length() == 1) {
      return List.of(s);
    }
    var lis = new ArrayList<String>();
    for (int i = 1; i < s.length(); i++) {
      String before = s.substring(0, i);
      String after = s.substring(i);
      if ((before.length() == 1 || before.charAt(0) != '0') &&
              Integer.parseInt(after) != 0 &&
              after.charAt(after.length() - 1) != '0'
      ) {
        lis.add(before + '.' + after);
      }
    }
    if (s.charAt(0) != '0') lis.add(s);
    return lis;
  }

  public static List<String> ambiguousCoordinates(String s) {
    var ans = new ArrayList<String>();
    String inp = s.substring(1, s.length() - 1);
    for (int i = 1; i < inp.length(); i++) {
      String a = inp.substring(0, i);
      String b = inp.substring(i);
      var lisA = addDecimal(a);
      var lisB = addDecimal(b);
      for (String s1 : lisA) {
        for (String s2 : lisB) {
          ans.add("(" + s1 + ", " + s2 + ")");
        }
      }
    }
    return ans;
  }

  public static void test() {
    System.out.println(ambiguousCoordinates("(1011)"));
    System.out.println(ambiguousCoordinates("(123)"));
    System.out.println(ambiguousCoordinates("(0123)"));
    System.out.println(ambiguousCoordinates("(00011)"));
    System.out.println(ambiguousCoordinates("(100)"));
  }

  public static void main(String[] args) {
    test();
  }
}
