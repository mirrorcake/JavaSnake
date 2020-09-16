package com.shouko.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 *
 *
 *
 * @author shouko
 */

public class SnakePanel extends JPanel implements KeyListener, ActionListener {

    //载入图片
    ImageIcon title = new ImageIcon("src/com/shouko/snake/images/title.png");
    ImageIcon body = new ImageIcon("src/com/shouko/snake/images/body.png");
    ImageIcon up = new ImageIcon("src/com/shouko/snake/images/up.png");
    ImageIcon down = new ImageIcon("src/com/shouko/snake/images/down.png");
    ImageIcon left = new ImageIcon("src/com/shouko/snake/images/left.png");
    ImageIcon right = new ImageIcon("src/com/shouko/snake/images/right.png");
    ImageIcon food = new ImageIcon("src/com/shouko/snake/images/food.png");


    Random random = new Random();

    char n = 10;

    //蛇的数据结构，位置和方向
    private int snakeLength;
    private int[] snakeX = new int[816];
    private int[] snakeY = new int[816];
    private char dir = 'R'; // L, R, U, D
    private int foodX = 25 + random.nextInt(34)*25;
    private int foodY = 75 + random.nextInt(24)*25;;
    private boolean life = true;
    private int score = 0;
    private int gameSpeed = 250;

    //游戏是否开始
    boolean isStarted = false;

    Timer timer = new Timer(gameSpeed,this);


    /**
     * 构造函数，初始化蛇，添加按键监听器，timer开始
     */
    public SnakePanel() {
        initSnake();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //设置背景颜色和标题栏
        this.setBackground(Color.WHITE);
        title.paintIcon(this,g,25,10);

        //游戏区域
        g.fillRect(25,75,850,600);


        //控制蛇头方向
        switch (dir){
            case 'L':
                left.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case 'R':
                right.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case 'U':
                up.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
            case 'D':
                down.paintIcon(this, g, snakeX[0], snakeY[0]);
                break;
        }

        //输出蛇的身体
        for(int i = 1; i < snakeLength; i++){
            body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }

        food.paintIcon(this, g, foodX, foodY);
        g.setColor(Color.BLACK);
        g.setFont(new Font("微软雅黑",0,20));
        g.drawString("当前得分："+ score,750,40);

        //开始游戏提示
        if(!isStarted){
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑",0,60));
            g.drawString("按空格键开始游戏",225,350);
        }

        if(!life){
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑",0,80));
            g.drawString("你蛇死了！",225,200);
            isStarted = false;
        }

    }

    /**
     * 初始化蛇的位置
     */
    public void initSnake(){

        snakeLength = 3;
        snakeX[0] = 100;
        snakeY[0] = 100;
        snakeX[1] = 75;
        snakeY[1] = 100;
        snakeX[2] = 50;
        snakeY[2] = 100;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * 刷新食物
     */
    public void changeFoodLocation(){

        int x = random.nextInt(34);
        int y = random.nextInt(24);
        foodX = 25 + x * 25;
        foodY = 75 + y * 25;
    }


    /**
     * 监听哪一个按键被按下
     *
     */
    @Override
    public void keyPressed(KeyEvent e) {


        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE){
            isStarted = !isStarted;
            if(life == false){
                life = true;
                initSnake();
                dir = 'R';
            }
            repaint();
        }
        if(isStarted){
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    dir = 'U';
                    break;
                case KeyEvent.VK_DOWN:
                    dir = 'D';
                    break;
                case KeyEvent.VK_LEFT:
                    dir = 'L';
                    break;
                case KeyEvent.VK_RIGHT:
                    dir = 'R';
                    break;

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    /**
     * 蛇的移动
     */
    public void snakeMove(){
        for(int i =snakeLength-1; i >=1; i--){
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
     * 判断蛇是否活着
     * @return True-活，False-死
     */
    public boolean isAlive(){
        //撞自己身子而死
        for(int i = 1; i < snakeLength; i++){
            if(snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]){
                return false;
            }
        }

        //撞墙而死
        if(snakeX[0] < 25 || snakeX[0] > 875 ){
            return false;
        }
        if(snakeY[0] < 75 || snakeY[0] > 675 ){
            return false;
        }

        return true;
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if(isStarted){
            if(snakeX[0]==foodX && snakeY[0] == foodY){
                changeFoodLocation();
                repaint();
                snakeLength++;
                score++;
            }
            accelerate();
            life = isAlive();
            if(life){
                snakeMove();
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
