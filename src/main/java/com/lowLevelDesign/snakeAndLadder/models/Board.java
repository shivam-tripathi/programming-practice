package com.lowLevelDesign.snakeAndLadder.models;

import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Board {
  private final int rows;
  private final int cols;
  private final Position startPosition;
  private final Position endPosition;
  private final List<List<Cell>> cells = new ArrayList<>();

  protected Board(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.startPosition = Position.from(0, 0);
    this.endPosition = Position.from(this.rows-1, this.cols-1);
  }

  public int getNumRows() {
    return this.rows;
  }

  public int getNumCols() {
    return this.cols;
  }

  abstract public String getType();

  public void setComponents(Component ...components) {
    setComponents(Arrays.asList(components));
  }
  abstract public void setComponents(List<Component> components);
}
