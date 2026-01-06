package com.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Pair <T1 extends Comparable<T1>, T2 extends Comparable<T2>> implements Comparable<Pair<T1, T2>> {
  public T1 first;
  public T2 second;
  public Pair(T1 first, T2 second) {
    this.first = first;
    this.second = second;
  }

  @Override
  public int compareTo(Pair<T1, T2> that) {
    int cmp = this.getFirst().compareTo(that.getFirst());
    if (cmp == 0) {
      cmp = this.getSecond().compareTo(that.getSecond());
    }
    return cmp;
  }
}
