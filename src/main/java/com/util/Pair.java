package com.util;

public class Pair <T1 extends Comparable<T1>, T2 extends Comparable<T2>> implements Comparable<Pair<T1, T2>> {
  public T1 first;
  public T2 second;
  public Pair(T1 f, T2 s) {
    first = f;
    second = s;
  }

  public T1 getFirst() {
    return first;
  }

  public T2 getSecond() {
    return second;
  }

  public void setFirst(T1 first) {
    this.first = first;
  }

  public void setSecond(T2 second) {
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

  @Override
  public String toString() {
    return "(" + this.first + ", " + this.second + ")";
  }
}
