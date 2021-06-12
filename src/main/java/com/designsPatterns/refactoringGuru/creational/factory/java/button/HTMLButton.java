package com.designsPatterns.refactoringGuru.creational.factory.java.button;

public class HTMLButton implements Button {
  @Override
  public void render() {
    System.out.println("<button>Test Button</button>");
    onClick();
  }

  @Override
  public void onClick() {
    System.out.println("HTMLButton::onClick");
  }
}
