package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import com.example.nick.rpggame.GameSurface;


/**
 * Final boss initialization
 * */
public class GreenSkeleton extends Skeleton {

    private Bitmap greenSkeletonImage;
    private MainCharacter mainCharacter;

    private int health;
    /**
     * Skeleton initialization
     *
     * @param gameSurface - surface that handles all graphic changes in game
     * @param image - skeleton image
     * @param x - coordinates on canvas
     * @param y - coordinates on canvas
     * @param mainCharacter - player's character
     */
    public GreenSkeleton(GameSurface gameSurface, Bitmap image, int x, int y, MainCharacter mainCharacter) {
        super(gameSurface, image, x, y, mainCharacter);

        this.greenSkeletonImage = image;
        this.mainCharacter = mainCharacter;

        this.health = 10;

        VELOCITY = (float) 0.25;
    }


    /**
     * @return number of health points of skeleton
     */
    public int getHealth() {
        return health;
    }


    /**
     * @param health - set health points
     */
    public void setHealth(int health) {
        this.health = health;
    }
}
