package com.shouko.snake;

import javax.swing.*;
import java.awt.*;

/**
 * 蛇类
 * @author Shouko
 */
public class Snake {
    //载入蛇的身子和头的图片
    ImageIcon body = new ImageIcon("src/com/shouko/snake/images/body.png");
    ImageIcon up = new ImageIcon("src/com/shouko/snake/images/up.png");
    ImageIcon down = new ImageIcon("src/com/shouko/snake/images/down.png");
    ImageIcon left = new ImageIcon("src/com/shouko/snake/images/left.png");
    ImageIcon right = new ImageIcon("src/com/shouko/snake/images/right.png");

    //蛇的参数，x和y为蛇的对应长度的坐标
    public int length;
    public int[] snakeX = new int[816];
    public int[] snakeY = new int[816];
    public char dir = 'R'; // L, R, U, D
    public boolean life = true;

    /**
     * 画出蛇的图像
     * @param c
     * @param g
     */
    public void draw(Component c, Graphics g){
        drawBody(c, g);
        drawHead(c, g);
    }

    /**
     * 初始化蛇的参数
     */
    public void initialize(){
        length = 3;
        snakeX[0] = 100;
        snakeY[0] = 100;
        snakeX[1] = 75;
        snakeY[1] = 100;
        snakeX[2] = 50;
        snakeY[2] = 100;
    }


    /**
     * 蛇的移动
     */
    public void move(){
        for(int i = length -1; i >=1; i--){
            snakeX[i] = snakeX[i-1];
            snakeY[i] = snakeY[i-1];
        }
        switch (dir){
            case 'U':
                snakeY[0] -= 25;
                break;
            case 'D':
                snakeY[0] += 25;
                break;
            case 'L':
                snakeX[0] -= 25;
                break;
            case 'R':
                snakeX[0] += 25;
                break;
        }
    }

    /**
     * 检查蛇是否还活着
     */
    public void checkAlive(){
        //撞自己身子而死
        for(int i = 1; i < length; i++){
            if(snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                life = false;
            }
        }
        //撞墙而死
        if(snakeX[0] < 25 || snakeX[0] > 875 ){
            life = false;
        }
        if(snakeY[0] < 75 || snakeY[0] > 675 ){
            life = false;
        }else{
            life = true;
        }
    }

    /**
     * 画出蛇的身体
     * @param c
     * @param g
     */
    private void drawBody(Component c, Graphics g){
        for(int i = 1; i < length; i++){
            body.paintIcon(c, g, snakeX[i], snakeY[i]);
        }
    }

    /**
     * 画出蛇的头部
     * @param c
     * @param g
     */
    private void drawHead(Component c, Graphics g){
        switch (dir){
            case 'L':
                left.paintIcon(c, g, snakeX[0], snakeY[0]);
                break;
            case 'R':
                right.paintIcon(c, g, snakeX[0], snakeY[0]);
                break;
            case 'U':
                up.paintIcon(c, g, snakeX[0], snakeY[0]);
                break;
            case 'D':
                down.paintIcon(c, g, snakeX[0], snakeY[0]);
                break;
        }
    }
}
