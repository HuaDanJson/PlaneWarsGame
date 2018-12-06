package com.example.lizoe.androidgameexpand;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class SoundIcon {
	private Bitmap bmpSound,bmpSound2;
	public static int btn_sound_x,btn_sound_y;
	public SoundIcon(Bitmap bmpSound,Bitmap bmpsBitmap2) {
		this.bmpSound = bmpSound;
		this.bmpSound2 = bmpsBitmap2;
		//X居中，Y紧接屏幕底部
		btn_sound_x = MyView.screenW-bmpSound.getWidth();
		btn_sound_y = MyView.screenH*7/8-bmpSound.getHeight();
	}

	public void draw(Canvas canvas, Paint paint) {
		//绘制未按下按钮图
		if(MyView.soundFlag){
			canvas.drawBitmap(bmpSound, btn_sound_x,btn_sound_y, paint);
		} else{
			canvas.drawBitmap(bmpSound2,btn_sound_x,btn_sound_y, paint);
		}
	}

	public void onTouchEvent(MotionEvent event) {
		//获取用户当前触屏位置
		int pointX = (int) event.getX();
		int pointY = (int) event.getY();

		if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
			if (pointX > btn_sound_x-25 && pointX < btn_sound_x + bmpSound.getWidth()+25) {
				if (pointY > btn_sound_y-25 && pointY < btn_sound_y + bmpSound.getHeight()+25) {
					if (pointX > btn_sound_x && pointX < btn_sound_x + bmpSound.getWidth()&&pointY > btn_sound_y && pointY < btn_sound_y + bmpSound.getHeight()) {

					}else
					{
						if(pointX>btn_sound_x)
							btn_sound_x += (pointX-btn_sound_x)*0.9;
						else
							btn_sound_x -= (btn_sound_x-pointX)*0.9;

						if(pointY>btn_sound_y)
							btn_sound_y += (pointY-btn_sound_y)*0.9;
						else
							btn_sound_y -= (btn_sound_y-pointY)*0.9;
					}

				}
			}
		}

		if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
			if (pointX > btn_sound_x && pointX < btn_sound_x + bmpSound.getWidth()) {
				if (pointY > btn_sound_y && pointY < btn_sound_y + bmpSound.getHeight()) {
					//还原Button状态为未按下状态

					//改变当前游戏状态为开始游戏
					if(MyView.soundFlag){
						MyView.soundFlag = false;
						if(MyView.gameState == MyView.GAME_MENU)
							MyView.mediaPlayer2.start();
						else if(MyView.gameState == MyView.GAME_PAUSE)
							MyView.mediaPlayer2.start();
					} else {
						MyView.soundFlag = true;
						MyView.mediaPlayer.pause();
						MyView.mediaPlayer2.pause();
					}
				}
			}
		}

	}
}
