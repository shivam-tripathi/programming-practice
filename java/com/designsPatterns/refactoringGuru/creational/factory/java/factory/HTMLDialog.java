package com.designsPatterns.refactoringGuru.creational.factory.java.factory;

import com.designsPatterns.refactoringGuru.creational.factory.java.button.Button;
import com.designsPatterns.refactoringGuru.creational.factory.java.button.HTMLButton;

public class HTMLDialog extends Dialog {
  @Override
  public Button createButton() {
    return new HTMLButton();
  }
}
