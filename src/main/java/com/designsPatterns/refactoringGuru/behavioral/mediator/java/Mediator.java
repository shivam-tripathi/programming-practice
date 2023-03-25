package com.designsPatterns.refactoringGuru.behavioral.mediator.java;

import com.designsPatterns.refactoringGuru.behavioral.mediator.java.components.Component;

import javax.swing.*;

/**
 * https://refactoring.guru/design-patterns/mediator
 * Helps reduce chaotic dependencies between objects. It restricts direct communication between object and forces them
 * to communicate only via a mediator.
 * Most popular usage of mediator in java code is facilitating communications between GUI
 * components of an app. The synonym of mediator pattern is controller in MVC. Some examples in core Java libraries:
 * 1. java.util.Timer (all scheduleXXX() methods)
 * 2. java.util.concurrent.Executor (#execute method)
 * 3. java.util.concurrent.ExecutorService (all invokeXXX and submit methods)
 * 4. java.util.cocurrent.ScheduledExecutorService (all scheduleXXX methods)
 * 5. java.lang.reflect.Method#invoke
 */

public interface Mediator {
  void addNewNote(Note node);
  void deleteNote();
  void getInfoFromList(Note note);
  void saveChanges();
  void makeNote();
  void clear();
  void sendToFilter(ListModel<Note> list);
  void setElementsList(ListModel<Note> list);
  void registerComponent(Component component);
  void hideElements(boolean flag);
  void createGUI();
}
