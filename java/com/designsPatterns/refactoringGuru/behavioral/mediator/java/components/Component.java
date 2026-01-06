package com.designsPatterns.refactoringGuru.behavioral.mediator.java.components;


import com.designsPatterns.refactoringGuru.behavioral.mediator.java.Mediator;

public interface Component {
  void setMediator(Mediator mediator);

  String getName();
}
