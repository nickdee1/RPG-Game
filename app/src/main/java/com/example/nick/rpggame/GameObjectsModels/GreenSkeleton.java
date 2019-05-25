package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import com.example.nick.rpggame.GameSurface;

public class GreenSkeleton extends Skeleton {

    private Bitmap greenSkeletonImage;
    private MainCharacter mainCharacter;

    private int health;
    /**
     * Skeleton initialization
     *
     * @param gameSurface
     * @param image
     * @param x
     * @param y
     * @param mainCharacter
     * @params: gameSurface - surface that handles all graphic changes in game
     * image - image of character
     * x, y - coordinates on canvas
     * mainCharacter - player's character
     */
    public GreenSkeleton(GameSurface gameSurface, Bitmap image, int x, int y, MainCharacter mainCharacter) {
        super(gameSurface, image, x, y, mainCharacter);

        this.greenSkeletonImage = image;
        this.mainCharacter = mainCharacter;

        this.health = 10;

        VELOCITY = (float) 0.25;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
