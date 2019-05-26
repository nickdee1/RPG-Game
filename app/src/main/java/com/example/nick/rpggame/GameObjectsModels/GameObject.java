package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;

/**
 * Blueprint for any object in game (Player's health status, chests, etc).
 * **/
public abstract class GameObject {

    protected Bitmap image;

    protected int x;
    protected int y;

    private int objectWidth;
    private int objectHeight;


    /**
     * Game object initialization
     * @param image - image of game object
     * @param x, y - coordinates of creation on canvas
     * */
    public GameObject(Bitmap image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;

        this.objectWidth = image.getWidth();
        this.objectHeight = image.getHeight();
    }


    /**
     * Get X coordinate of object
     * */
    public int getX() {
        return x;
    }

    /**
     * Get Y coordinate of object
     * */
    public int getY() {
        return y;
    }


    /**
     * Get width of image of object
     * */
    private int getObjectWidth() {
        return objectWidth;
    }

    /**
     * Get height of image of object
     * */
    private int getObjectHeight() {
        return objectHeight;
    }

    /**
     * Creates object's image from png IMAGE
     **/
    public Bitmap createImage() {
        return Bitmap.createBitmap(image, 0, 0, getObjectWidth(), getObjectHeight());
    }
}
