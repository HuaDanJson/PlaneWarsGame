package com.example.lizoe.androidgameexpand;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;


public class Background {
    //游戏背景的图片资源
    //为了循环播放，这里定义两个位图对象，
    //其资源引用的是同一张图片
    private Bitmap bmpBackGround1;
    private Bitmap bmpBackGround2;
    //游戏背景坐标
    private int bg1x, bg1y, bg2x, bg2y;
    private int btn_x, btn_y;

    private boolean isPress;

    //背景滚动速度
    private int speed = 3;

    //游戏背景构造函数
    public Background(Bitmap bmpBackGround, Bitmap bmpGamePause) {
        this.bmpBackGround1 = bmpBackGround;
        this.bmpBackGround2 = bmpBackGround;
        //首先让第一张背景底部正好填满整个屏幕
        bg1y = -Math.abs(bmpBackGround1.getHeight() - MyView.screenH);
        //第二张背景图紧接在第一张背景的上方
        //+101的原因：虽然两张背景图无缝隙连接但是因为图片资源头尾
        //直接连接不和谐，为了让视觉看不出是两张图连接而修正的位置
        bg2y = bg1y - bmpBackGround1.getHeight();

        btn_x = (int) (MyView.screenW * 0.9);
        btn_y = (int) (MyView.screenH * 0.01);

    }

    //游戏背景的绘图函数
    public void draw(Canvas canvas, Paint paint) {
        //绘制两张背景
        canvas.drawBitmap(bmpBackGround1, bg1x, bg1y, paint);
        canvas.drawBitmap(bmpBackGround2, bg2x, bg2y, paint);
    }

    public void onTouchEvent(MotionEvent event) {
        //获取用户当前触屏位置
        int pointX = (int) event.getX();
        int pointY = (int) event.getY();
        //当用户是按下动作或移动动作
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            //判定用户是否点击了按钮
//            if (pointX > btn_x && pointX < btn_x + bmpGamePause.getWidth()) {
//                if (pointY > btn_y && pointY < btn_y + bmpGamePause.getHeight()) {
//                    isPress = true;
//                } else {
//                    isPress = false;
//                }
//            } else {
//                isPress = false;
//            }
            //当用户是抬起动作
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            //抬起判断是否点击按钮，防止用户移动到别处
//            if (pointX > btn_x && pointX < btn_x + bmpGamePause.getWidth()) {
//                if (pointY > btn_y && pointY < btn_y + bmpGamePause.getHeight()) {
//                    //还原Button状态为未按下状态
//                    isPress = false;
//                    //改变当前游戏状态
////                    if (MyView.gameState == MyView.GAME_PAUSE) {
////                        //如果游戏暂停中  就开始游戏
////                        MyView.gameState = MyView.GAMEING;
////                    } else if (MyView.gameState == MyView.GAMEING) {
////                        //如果游戏中,就改变为暂停
////                        MyView.gameState = MyView.GAME_PAUSE;
////                    }
//
//                }
//            }
        }
    }


    //游戏背景的逻辑函数
    public void logic() {
        bg1y += speed;
        bg2y += speed;
        //当第一张图片的Y坐标超出屏幕，
        //立即将其坐标设置到第二张图的上方
        if (bg1y > MyView.screenH) {
            bg1y = bg2y - bmpBackGround1.getHeight();
        }
        //当第二张图片的Y坐标超出屏幕，
        //立即将其坐标设置到第一张图的上方
        if (bg2y > MyView.screenH) {
            bg2y = bg1y - bmpBackGround1.getHeight();
        }
    }
}
