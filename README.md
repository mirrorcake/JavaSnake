# JavaSnake - 用Java实现一个拥有基本功能的贪吃蛇小游戏

### 0x00. 写在前面
* 本程序参考了B站UP主[麦叔编程](https://www.bilibili.com/video/BV1AJ411A7rP?from=search&seid=11246926418463273436)的视频中的相关编程思想，已点赞投币收藏三连~~

### 0x01. 游戏功能
* 通过上、下、左、右键控制蛇的转向、按空格键实现游戏的暂停和开始
* 按下蛇运动方向相同的按键可以实现蛇的加速
* 得分的显示，以及得分越高游戏速度越快
* 蛇装墙或撞自己身子就会死亡

### 0x02. 文件说明
* 界面和蛇采用Adobe Illustrator绘制，其文件在[ai](https://github.com/ShoukoNx/JavaSnake/tree/master/ai)文件夹中
* 可执行jar包在[这里](https://github.com/ShoukoNx/JavaSnake/tree/master/out/artifacts/Snake_jar)
* [src](https://github.com/ShoukoNx/JavaSnake/tree/master/src/com/shouko/snake)文件夹中Food和Snake分别为食物和蛇类，SnakePanel控制游戏的主逻辑、按键和面板，SnakeGame为游戏主类
