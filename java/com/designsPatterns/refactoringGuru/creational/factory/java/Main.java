package com.designsPatterns.refactoringGuru.creational.factory.java;

import com.designsPatterns.refactoringGuru.creational.factory.java.factory.DesktopDialog;
import com.designsPatterns.refactoringGuru.creational.factory.java.factory.Dialog;
import com.designsPatterns.refactoringGuru.creational.factory.java.factory.HTMLDialog;

public class Main {
  private static Dialog dialog;

  static void configure() {
    if (System.getProperty("os.name") != null) {
      dialog = new DesktopDialog();
    } else {
      dialog = new HTMLDialog();
    }
  }

  static void runBusinessLogic() {
    dialog.renderWindow();
  }

  public static void main(String[] args) {
    configure();
    runBusinessLogic();
  }
}
