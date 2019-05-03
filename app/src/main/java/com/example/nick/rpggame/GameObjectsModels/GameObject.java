package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;

/**
 * Blueprint for any object in game (Player's health status, chests, etc).
 * **/

public abstract class GameObject {

    protected Bitmap image;

    int x;
    int y;

    private int objectWidth;
    private int objectHeight;

    public GameObject(Bitmap image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;

        this.objectWidth = image.getWidth();
        this.objectHeight = image.getHeight();
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getObjectWidth() {
        return objectWidth;
    }

    public int getObjectHeight() {
        return objectHeight;
    }

    /**
     * Creates object's image from png IMAGE
     **/
    public Bitmap createImage() {
        return Bitmap.createBitmap(image, 0, 0, getObjectWidth(), getObjectHeight());
    }
}
