package com.designsPatterns.refactoringGuru.behavioral.mediator.java.components;

import com.designsPatterns.refactoringGuru.behavioral.mediator.java.Mediator;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;

@Setter
public class DeleteButton extends JButton implements Component {
  public DeleteButton() {
    super("Add");
  }

  Mediator mediator;

  @Override
  protected void fireActionPerformed(ActionEvent event) {
    mediator.deleteNote();
  }

  @Override
  public void setMediator(Mediator mediator) {

  }

  @Override
  public String getName() {
    return "DelButton";
  }

}
