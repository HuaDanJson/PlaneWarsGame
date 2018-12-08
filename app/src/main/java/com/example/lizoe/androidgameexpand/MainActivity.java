package com.example.lizoe.androidgameexpand;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    public static MainActivity instance;
    @BindView(R.id.my_view_main_activity) MyView mMyView;
    @BindView(R.id.iv_pause_main_activity) ImageView mPause;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        //设置全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //显示自定义的SurfaceView视图
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_pause_main_activity)
    public void onPauseClicked() {
        if (MyView.gameState == MyView.GAME_PAUSE) {
            //如果游戏暂停中  就开始游戏
            MyView.gameState = MyView.GAMEING;
            if (MyView.soundFlag) {
                MyView.mediaPlayer.pause();
                //MySurfaceView.mediaPlayer2.start();
            } else {
                MyView.mediaPlayer.pause();
                MyView.mediaPlayer2.start();
            }
        } else if (MyView.gameState == MyView.GAMEING) {
            //如果游戏中,就改变为暂停
            MyView.gameState = MyView.GAME_PAUSE;
            MyView.mediaPlayer.pause();
            MyView.mediaPlayer2.pause();
        }
    }
}