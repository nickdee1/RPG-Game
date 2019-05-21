package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import android.graphics.Canvas;


/**
 * Is drawn after NPC dies
 * */
public class Tomb extends GameObject {

    private Bitmap tombImage;

    public Tomb(Bitmap image, int x, int y) {
        super(image, x, y);

        this.tombImage = image;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(tombImage, x, y, null);
    }
}
