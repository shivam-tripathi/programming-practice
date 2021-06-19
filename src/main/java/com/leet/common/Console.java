package com.leet.common;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Console {
  static void log(Object... objects) {
    System.out.println(Arrays.stream(objects).map(Objects::toString).collect(Collectors.joining(" ")));
  }
}
