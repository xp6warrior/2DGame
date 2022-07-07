package main;

import javax.swing.*;

public class Main {
    static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setTitle("2D Game by xp6warrior!");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.add(new GamePanel());
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
