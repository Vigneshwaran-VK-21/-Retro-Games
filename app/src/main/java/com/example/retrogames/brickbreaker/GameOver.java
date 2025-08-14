package com.example.retrogames.brickbreaker;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.retrogames.R;

import java.time.Instant;

public class GameOver extends AppCompatActivity {

    TextView tvPoints;
    ImageView ivNewHeighest;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        ivNewHeighest = findViewById(R.id.ivNewHeighest);
        tvPoints = findViewById(R.id.tvPoints);
        int points = getIntent().getExtras().getInt("points");//point
        if(points == 240){
            ivNewHeighest.setVisibility(View.VISIBLE);
        }
        tvPoints.setText("" + points);
    }

    public void restart(View view){
        Intent intent = new Intent(GameOver.this, MainActivity3.class);
        startActivity(intent);
        finish();
    }

}
