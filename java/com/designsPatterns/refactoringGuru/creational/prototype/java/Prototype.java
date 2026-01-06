package com.designsPatterns.refactoringGuru.creational.prototype.java;

import com.designsPatterns.refactoringGuru.creational.prototype.java.shapes.Circle;
import com.designsPatterns.refactoringGuru.creational.prototype.java.shapes.Rectangle;
import com.designsPatterns.refactoringGuru.creational.prototype.java.shapes.Shape;

import java.util.ArrayList;
import java.util.List;

public class Prototype {
  public static void main(String[] args) {
    List<Shape> shapes = new ArrayList<>();
    List<Shape> shapesCopy = new ArrayList<>();

    Circle circle = new Circle();
    circle.x = 10;
    circle.y = 20;
    circle.radius = 5;
    circle.color = "red";
    shapes.add(circle);

    Circle anotherCircle = circle.clone();
    shapes.add(anotherCircle);

    Rectangle rectangle = new Rectangle();
    rectangle.height = 10;
    rectangle.width = 20;
    rectangle.x = 23;
    rectangle.y = 45;
    rectangle.color = "blue";
    shapes.add(rectangle);

    for (Shape shape : shapes) {
      shapesCopy.add(shape.clone());
    }

    for (int i = 0; i < shapes.size(); i++) {
      if (shapes.get(i) != shapesCopy.get(i)) {
        System.out.println("✓ Copies are not same object!");
        if (shapes.get(i).equals(shapesCopy.get(i))) {
          System.out.println("✓ They are identical!");
        } else {
          System.out.println("✗ They are not indentical!");
        }
      } else {
        System.out.println("✗ They are same object!");
      }
    }
  }
}
