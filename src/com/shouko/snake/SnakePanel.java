package com.shouko.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 贪吃蛇游戏的主界面
 * 包括有对于按键逻辑的处理
 * @author Shouko
 */

public class SnakePanel extends JPanel implements KeyListener, ActionListener {

    //载入标题图片
    ImageIcon title = new ImageIcon("src/com/shouko/snake/images/title.png");

    //生成食物
    Food food = new Food();
    Snake snake = new Snake();

    //得分和游戏速度
    private int score = 0;
    private int gameSpeed = 250;

    //游戏是否正在运行
    boolean gameStarted = false;


    //
    Timer timer = new Timer(gameSpeed,this);


    /**
     * 构造函数，初始化蛇，添加按键监听器，timer开始
     */
    public SnakePanel() {
        snake.initialize();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }


    /**
     * 绘制出游戏界面、蛇、食物
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //设置背景颜色和标题栏
        this.setBackground(Color.WHITE);

        title.paintIcon(this,g,25,10);

        //游戏区域
        g.fillRect(25,75,850,600);

        //画出食物
        food.draw(this, g);
        snake.draw(this, g);


        //在右上角显示得分
        g.setColor(Color.BLACK);
        g.setFont(new Font("微软雅黑",0,20));
        g.drawString("当前得分："+ score,750,40);

        //开始游戏提示
        if(!gameStarted){
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑",0,60));
            g.drawString("按空格键开始游戏",225,350);
        }

        //显示死亡提示
        if(!snake.life){
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑",0,80));
            g.drawString("你蛇死了！",225,200);
            gameStarted = false;
            score = 0;
        }

    }



    @Override
    public void keyTyped(KeyEvent e) {

    }



    /**
     * 监听哪一个按键被按下
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE){
            gameStarted = !gameStarted;
            if(snake.life == false){
                snake.life = true;
                snake.initialize();
                snake.dir = 'R';
            }
            repaint();
        }
        if(gameStarted){
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    snake.dir = 'U';
                    break;
                case KeyEvent.VK_DOWN:
                    snake.dir = 'D';
                    break;
                case KeyEvent.VK_LEFT:
                    snake.dir = 'L';
                    break;
                case KeyEvent.VK_RIGHT:
                    snake.dir = 'R';
                    break;

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(gameStarted){
            if(snake.snakeX[0]==food.getX() && snake.snakeY[0] == food.getY()){
                food.changeLocation();
                repaint();
                snake.length++;
                score++;
            }
            accelerate();
            snake.checkAlive();
            if(snake.life){
                snake.move();
            }

        }
        repaint();
        timer.start();
    }


    /**
     * 游戏加速
     */
    public void accelerate(){

        if(score>5){
            timer.setDelay(200);
        }
        if(score>7){
            timer.setDelay(175);
        }
        if(score>10){
            timer.setDelay(150);
        }
        if(score>13){
            timer.setDelay(125);
        }
        if(score>13){
            timer.setDelay(100);
        }
    }

}
