package com.designsPatterns.refactoringGuru.behavioral.mediator.java.components;

import com.designsPatterns.refactoringGuru.behavioral.mediator.java.Mediator;
import com.designsPatterns.refactoringGuru.behavioral.mediator.java.Note;
import lombok.Setter;

import javax.swing.*;
import java.awt.event.ActionEvent;

@Setter
public class AddButton extends JButton implements Component {
  public AddButton() {
    super("Add");
  }

  Mediator mediator;

  @Override
  protected void fireActionPerformed(ActionEvent event) {
    mediator.addNewNote(new Note());
  }

  @Override
  public String getName() {
    return "AddButton";
  }
}
