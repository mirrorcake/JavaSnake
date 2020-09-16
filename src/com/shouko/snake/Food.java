package com.shouko.snake;


import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * 食物类
 * @author Shouko
 */
public class Food {

    //随机数产生器
    Random random = new Random();

    //定义初始食物坐标
    private int x = 25 + random.nextInt(34)*25;
    private int y = 75 + random.nextInt(24)*25;


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //载入食物图片
    ImageIcon food = new ImageIcon(this.getClass().getResource("images/food.png"));

    /**
     * 画出食物
     *
     * @param c
     * @param g
     */
    public void draw(Component c, Graphics g){
        food.paintIcon(c, g, x, y);
    }

    /**
     * 刷新食物
     */
    public void changeLocation(){
        x = 25 + random.nextInt(34) * 25;
        y = 75 + random.nextInt(24) * 25;
    }

}
