package com.shouko.snake;
import javax.swing.*;
import java.awt.*;

public class SnakePanel extends JPanel {
    public SnakePanel() {
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
    }
}
