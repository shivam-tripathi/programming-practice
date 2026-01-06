package com.dsAlgo.strings;

import java.util.List;

/**
 * Knuth-Morris-Pratt
 */
public class KMP {
  List<Integer> match(String text, String pattern) {
    char[] txt = text.toCharArray();
    char[] pat = pattern.toCharArray();
    int[] prep = new int[pat.length];
    int ptr = 0;
    for (int i = 1; i < pat.length; i++) {
      if (pat[ptr] == pat[i]) {
        prep[i] = ptr + 1;
        ptr++;
      } else {
        prep[i] = prep[ptr];
      }
    }

    int j = 0;
    for (int i = 0; i < txt.length; i++) {
      if (txt[i] == pat[j]) {
        j++;
      } else {
        j = prep[j-1];
      }
    }

    return List.of();
  }

  public static void main(String[] args) {
    var kmp = new KMP();
    kmp.match("", "");
  }
}
