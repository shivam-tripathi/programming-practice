package com.designsPatterns.refactoringGuru.creational.prototype.java.shapes;

import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
public abstract class Shape {
  public int x;
  public int y;
  public String color;

  public Shape(Shape source) {
    if (source != null) {
      this.x = source.x;
      this.y = source.y;
      this.color = source.color;
    }
  }

  public abstract Shape clone();

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Shape)) {
      return false;
    }
    Shape that = (Shape) obj;
    return this.x == that.x && this.y == that.y && Objects.equals(this.color, that.color);
  }
}
