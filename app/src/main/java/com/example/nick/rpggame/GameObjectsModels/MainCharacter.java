package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import com.example.nick.rpggame.GameSurface;

public class MainCharacter extends ModelCharacter {

    private int health;

    public MainCharacter(GameSurface gameSurface, Bitmap image, int x, int y) {
        super(gameSurface, image, x, y);

        this.health = 3;
    }


    public int getHealth() {
        return health;
    }
}
