package com.example.retrogames.snakegame;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.retrogames.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class snakegame extends AppCompatActivity {
//snake
    private static final int FPS = 60;
    private static final int SPEED =25;
    private  static final int STATUS_PAUSED_1 = 1;
    private  static final int STATUS_START_1 = 2;
    private  static final int STATUS_OVER_1 = 3;
    private  static final int STATUS_PLAYING_1 = 4;

    private GameView mGameView;
    private TextView mGameStatus_1_Text;
    private Button mGameBtn_1;
    private TextView mGameScoreText_1;

    private final AtomicInteger mGameStatus_1 = new AtomicInteger(STATUS_START_1);

    private  final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snakegame);

        mGameView = findViewById(R.id.game_view1);
        mGameStatus_1_Text = findViewById(R.id.game_status_1);
        mGameBtn_1 = findViewById(R.id.game_control_btn_1);
        mGameScoreText_1 = findViewById(R.id.game_score_1);
        mGameView.init();
        mGameView.setGameScoreUpdatedListener(score -> {
           mHandler.post(() -> mGameScoreText_1.setText("Score: " +score));
        });

        findViewById(R.id.up_btn1).setOnClickListener(v -> {
                    if(mGameStatus_1.get() == STATUS_PLAYING_1){
                        mGameView.setDirection(Direction.UP);
                    }
                });

        findViewById(R.id.down_btn1).setOnClickListener(v -> {
            if (mGameStatus_1.get() == STATUS_PLAYING_1) {
                mGameView.setDirection(Direction.DOWN);
            }
        });
        findViewById(R.id.left_btn1).setOnClickListener(v ->{
            if (mGameStatus_1.get() == STATUS_PLAYING_1) {
                mGameView.setDirection(Direction.LEFT);
            }
        });
        findViewById(R.id.Right_btn1).setOnClickListener(v -> {
            if (mGameStatus_1.get() == STATUS_PLAYING_1) {
                mGameView.setDirection(Direction.RIGHT);
            }
        });

        mGameBtn_1.setOnClickListener(v ->{
            if (mGameStatus_1.get() == STATUS_PLAYING_1){
                setGameStatus(STATUS_PAUSED_1);
            }else{
                setGameStatus(STATUS_PLAYING_1);
            }
        });

        setGameStatus(STATUS_START_1);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mGameStatus_1.get() == STATUS_PLAYING_1){
            setGameStatus(STATUS_PAUSED_1);
        }
    }

    private  void setGameStatus(int gameStatus)
    {
        int prevStatus = mGameStatus_1.get();
        mGameStatus_1_Text.setVisibility(View.VISIBLE);
        mGameBtn_1.setText("start");
        mGameStatus_1.set(gameStatus);
        switch (gameStatus)
        {
            case STATUS_OVER_1:
                mGameStatus_1_Text.setText("GAME OVER");
                break;
            case STATUS_START_1:
                mGameView.newGame();
                mGameStatus_1_Text.setText("START GAME");
                break;
            case STATUS_PAUSED_1:
                mGameStatus_1_Text.setText("GAME PAUSED");
                break;
            case STATUS_PLAYING_1:
                if (prevStatus == STATUS_OVER_1)
                {
                    mGameView.newGame();
                }
                startGame();
                mGameStatus_1_Text.setVisibility(View.INVISIBLE);
                mGameBtn_1.setText("pause");
                break;
        }

    }
    private void startGame(){
        final int delay = 1000 / FPS;
        new Thread(() -> {
            int count =0;
            while (!mGameView.isGameOver() && mGameStatus_1.get() != STATUS_PAUSED_1){
                try {
                    Thread.sleep(delay);
                    if (count % SPEED == 0){
                        mGameView.next();
                        mHandler.post(mGameView::invalidate);
                    }
                    count++;
                } catch (InterruptedException ignored) {

                }
            }
            if (mGameView.isGameOver()){
                mHandler.post(() ->setGameStatus(STATUS_OVER_1));
            }
        }).start();
    }
}