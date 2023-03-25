package com.lowLevelDesign.snakeAndLadder.models;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@Getter
@Setter
public class Position implements Comparable<Position> {
  private int x;
  private int y;

  static Map<Integer, Position> map;

  private Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public static Position from(int x, int y) {
    return new Position(x, y);
  }

  /**
   * Reflexive, symmetric, transitive, consistent
   * Always update hashCode as well when overriding this method
   */
  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Position)) {
      return false;
    }
    Position that = (Position) obj;
    return this.x == that.x && this.y == that.y;
  }

  @Override
  public int hashCode() {
    return this.x * 10 + this.y; // all numbers can be represented from 0 to 99 uniquely
  }

  @Override
  public int compareTo(@NotNull Position that) {
    return this.hashCode() - that.hashCode();
  }
}
