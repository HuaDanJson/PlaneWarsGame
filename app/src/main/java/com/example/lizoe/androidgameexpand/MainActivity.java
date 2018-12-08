package com.example.lizoe.androidgameexpand;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lizoe.androidgameexpand.event.LoseGameEvent;
import com.example.lizoe.androidgameexpand.event.StartGameEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    public static MainActivity instance;

    @BindView(R.id.my_view_main_activity) MyView mMyView;
    @BindView(R.id.iv_pause_main_activity) ImageView mPause;
    @BindView(R.id.iv_sound_main_activity) ImageView mSound;

    @BindView(R.id.tv_lose_score_main_activity) TextView mLoseScore;
    @BindView(R.id.iv_lose_continue_main_activity) ImageView mLoseContinue;
    @BindView(R.id.iv_lose_exit_main_activity) ImageView mLoseExit;
    @BindView(R.id.ll_lose_game_main_activity) RelativeLayout mLoseGameView;

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
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public void startGame() {
        //如果游戏暂停中  就开始游戏
        MyView.gameState = MyView.GAMEING;
        if (MyView.soundFlag) {
            MyView.mediaPlayer.pause();
            //MySurfaceView.mediaPlayer2.start();
        } else {
            MyView.mediaPlayer.pause();
            MyView.mediaPlayer2.start();
        }
    }

    @OnClick(R.id.iv_pause_main_activity)
    public void onPauseClicked() {
        if (MyView.gameState == MyView.GAME_PAUSE) {
            startGame();
        } else if (MyView.gameState == MyView.GAMEING) {
            //如果游戏中,就改变为暂停
            MyView.gameState = MyView.GAME_PAUSE;
            MyView.mediaPlayer.pause();
            MyView.mediaPlayer2.pause();
        }
    }

    @OnClick(R.id.iv_sound_main_activity)
    public void onSoundClicked() {
        if (mSound.isSelected()) {
            mSound.setSelected(false);
            MyView.soundFlag = true;
            MyView.mediaPlayer.pause();
            MyView.mediaPlayer2.pause();
        } else {
            mSound.setSelected(true);
            MyView.soundFlag = false;
            MyView.mediaPlayer.pause();
            MyView.mediaPlayer2.start();
        }
    }

    @OnClick(R.id.iv_lose_continue_main_activity)
    public void onLoseContinueClicked() {
        mLoseGameView.setVisibility(View.GONE);
        mMyView.setPlayerHp(3);
        startGame();
    }

    @OnClick(R.id.iv_lose_exit_main_activity)
    public void onLoseExitClicked() {
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveStartGameEvent(StartGameEvent event) {
        mPause.setVisibility(View.VISIBLE);
        mSound.setVisibility(View.VISIBLE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveLoseGameEvent(LoseGameEvent event) {
        if (event == null) {return;}
        mLoseGameView.setVisibility(View.VISIBLE);
        mLoseScore.setText("得分：" + String.valueOf(event.getLoseScore()));
    }
}