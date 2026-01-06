package com.lowLevelDesign.snakeAndLadder.models;

import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class Cell {
  private final int x;
  private final int y;
  private final List<Component> components;
  Board board;

  private Cell(@NonNull Board board, int x, int y, Component[] components) {
    if (x < 0 || y < 0 || x >= board.getNumRows() || y >= board.getNumCols()) {
      throw new RuntimeException("Invalid cell co-ordinates");
    }
    this.board = board;
    this.x = x;
    this.y = y;
    this.components = Arrays.asList(components);
  }

  static Cell from(Board board, int x, int y, Component ...components) {
    return new Cell(board, x, y, components);
  }

  @Override
  public int hashCode() {
    return this.x * this.board.getNumCols() + this.y;
  }
}
