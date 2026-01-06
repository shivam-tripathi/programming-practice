package com.designsPatterns.refactoringGuru.creational.factory.java.factory;

import com.designsPatterns.refactoringGuru.creational.factory.java.button.Button;
import com.designsPatterns.refactoringGuru.creational.factory.java.button.DesktopButton;

public class DesktopDialog extends Dialog {
  @Override
  public Button createButton() {
    return new DesktopButton();
  }
}
