package com.example.retrogames;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.retrogames.brickbreaker.MainActivity3;
import com.example.retrogames.snakegame.snakegame;
import com.example.retrogames.tetrisgame.MainActivity4;
import com.example.retrogames.tictactoe.MainActivity2;

public class cardview extends AppCompatActivity implements View.OnClickListener{
//card
    public CardView card1, card2, card3, card4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);

        card1 = (CardView) findViewById(R.id.card_tic1);
        card2 = (CardView) findViewById(R.id.card_sanke2);
        card3 = (CardView) findViewById(R.id.card_bb3);
        card4 = (CardView) findViewById(R.id.card_tetrgame4);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()){
            case R.id.card_tic1:
                i = new Intent(this, MainActivity2.class);
                startActivity(i);
                break;
            case R.id.card_sanke2:
                i = new Intent(this, snakegame.class);
                startActivity(i);
                break;

            case R.id.card_bb3:
                i= new Intent(this, MainActivity3.class);
                startActivity(i);
                break;

            case R.id.card_tetrgame4:
                i = new Intent( this, MainActivity4.class);
                startActivity(i);
                break;
        }
    }
}