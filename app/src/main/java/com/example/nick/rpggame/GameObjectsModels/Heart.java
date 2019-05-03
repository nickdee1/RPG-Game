package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Heart extends GameObject {


    private Bitmap heartImage;


    public Heart(Bitmap image, int x, int y) {
        super(image, x, y);

        this.heartImage = this.createImage();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(heartImage, x, y, null);
    }
}
