package com.designsPatterns.refactoringGuru.creational.factory.java.button;

import javax.swing.*;
import java.awt.*;

public class DesktopButton implements Button {
  JPanel jPanel = new JPanel();
  JFrame jFrame = new JFrame();
  JButton jButton;

  @Override
  public void render() {
    jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
    JLabel label = new JLabel("LinuxButton");
    label.setOpaque(true);
    label.setBackground(new Color(235, 233, 126));
    label.setFont(new Font("Dialog", Font.BOLD, 44));
    label.setHorizontalAlignment(SwingConstants.CENTER);
    jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    jFrame.getContentPane().add(jPanel);
    jPanel.add(label);
    jButton = new JButton("Exit");
    onClick(); // attach event listener
    jPanel.add(jButton);
    jFrame.setSize(320, 200);
    jFrame.setVisible(true);
    onClick();
  }

  @Override
  public void onClick() {
    jButton.addActionListener((e) -> {
      jFrame.setVisible(false);
      System.exit(0);
    });
  }
}
