package com.example.nick.rpggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Heart extends GameObject {

    private GameSurface gameSurface;
    private Bitmap heartImage;


    public Heart(GameSurface gameSurface, Bitmap image, int x, int y) {
        super(image, x, y);

        this.gameSurface = gameSurface;
        this.heartImage = this.setImage();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(heartImage, x, y, null);
    }
}
