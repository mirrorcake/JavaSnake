package com.shouko.snake;
import javax.swing.*;

public class SnakeGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setBounds(100,100,900,720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("贪吃蛇");
        frame.add(new SnakePanel());
        frame.setVisible(true);



    }
}
