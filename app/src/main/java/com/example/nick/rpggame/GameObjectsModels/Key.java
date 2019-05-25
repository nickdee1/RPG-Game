package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Key extends GameObject {

    private Bitmap keyImage;
    private String name;

    /**
     * Game object initialization
     *
     * @param image
     * @param x
     * @param y
     * @params: image - image of game object
     * x, y - coordinates of creation on canvas
     */
    public Key(Bitmap image, int x, int y, String name) {
        super(image, x, y);
        this.keyImage = image;
        this.name = name;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(keyImage, x, y, null);
    }

    public String getName() {
        return name;
    }


}
