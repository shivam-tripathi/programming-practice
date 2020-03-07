package com.patterns.observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingObservableExample {
    JFrame frame;
    public void go() {
        frame = new JFrame();
        frame.setTitle("Swing Observable Example");
        JButton button = new JButton("Should I do it?");
        button.addActionListener(new AngelListener());
        button.addActionListener(new DevilListener());

        frame.add(button);

        frame.setSize(300, 200);
        frame.setLocation(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    class AngelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.printf("Don't do it, you might regret it!%n");
        }
    }

    class DevilListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.printf("Come on, do it!%n");
        }
    }
}