package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import android.graphics.Canvas;


/**
 * Indicator of character's health and armor
 * */
public class HealthIndicator extends GameObject {


    private Bitmap heartImage;

    /**
     * Health indicator initialization
     * @param image - image of heart/armor
     * @param x, y - coordinates on canvas
     * */
    public HealthIndicator(Bitmap image, int x, int y) {
        super(image, x, y);

        this.heartImage = this.createImage();
    }


    /**
     * Draws heart/armor on canvas
     * */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(heartImage, x, y, null);
    }
}
