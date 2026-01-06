package com.designsPatterns.refactoringGuru.creational.prototype.java.shapes;

import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
public class Rectangle extends Shape {
  public int width;
  public int height;

  public Rectangle(@NonNull Rectangle source) {
    super(source);
    this.width = source.width;
    this.height = source.height;
  }

  @Override
  public Rectangle clone() {
    return new Rectangle(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Rectangle)) {
      return false;
    }
    Rectangle that = (Rectangle) obj;
    return that.height == this.height && that.width == this.width;
  }
}
