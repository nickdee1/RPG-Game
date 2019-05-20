package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Chest extends GameObject {

    private Bitmap chestImage;
    private int healingPotionCount = 1;

    public Chest(Bitmap image, int x, int y) {
        super(image, x, y);

        this.chestImage = this.createImage();

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(chestImage, x, y, null);
    }
}
