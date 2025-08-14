package com.example.retrogames.tetrisgame.models;

import com.example.retrogames.tetrisgame.presenters.GameModel;

public class GameModelFactory {
    private GameModelFactory(){

    }

    public static GameModel newGameModel(GameType type){

        switch (type){
            case TETRIS:
                return new TetrisGameModel();
            default:
                return null;
        }
    }
}
