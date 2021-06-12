package com.designsPatterns.refactoringGuru.creational.factory.java.factory;

import com.designsPatterns.refactoringGuru.creational.factory.java.button.Button;

public abstract class Dialog {
  public void renderWindow() {
    Button button = createButton();
    System.out.println("Rendering window");
    button.render();
  }

  public abstract Button createButton();
}
