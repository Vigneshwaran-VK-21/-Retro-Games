package com.example.retrogames.tetrisgame.presenters;

public enum GameStatus {

    START("START GAME"),
    PLAYING("PLAYING"),
    OVER("GAME OVER"),
    PAUSED("GAME PAUSED");

    private final String mvalue;
    GameStatus(String value){
        mvalue = value;
    }
    public String getvalue(){
        return mvalue;
    }
}
