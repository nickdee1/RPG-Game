package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Key extends GameObject {

    private Bitmap keyImage;
    private String name;
    private boolean visible = false;

    /**
     * Game object initialization
     *
     * @param image - image of key
     * @param x - x coordinate to be drawn
     * @param y - y coordinate to be drawn
     */
    public Key(Bitmap image, int x, int y, String name) {
        super(image, x, y);
        this.keyImage = image;
        this.name = name;
    }


    /**
     * Draw key on canvas
     * @param canvas -  game canvas
     */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(keyImage, x, y, null);
    }


    /**
     * @return name of key
     */
    public String getName() {
        return name;
    }


    /**
     * @return true/false if key is visible on cnavas
     */
    public boolean isVisible() {
        return visible;
    }


    /**
     * Make key be visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
