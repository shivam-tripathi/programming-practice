package com.designsPatterns.refactoringGuru.creational.prototype.java.shapes;

import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
public class Circle extends Shape {
  public int radius;

  public Circle(@NonNull Circle source) {
    super(source);
    this.radius = source.radius;
  }

  @Override
  public Circle clone() {
    return new Circle(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Circle)) {
      return false;
    }

    Circle that = (Circle) obj;
    return this.radius == that.radius;
  }
}
