package com.example.nick.rpggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.example.nick.rpggame.GameObjectsModels.GameObject;

public class GameButton extends GameObject {

    private Bitmap pausedButtonBitmap;
    private int width;
    private int height;


    public GameButton(Bitmap image, int x, int y) {
        super(image, x, y);

        pausedButtonBitmap = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    /* Draw pause button */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(pausedButtonBitmap, x, y, null);
    }

    public boolean isPressed(int touchedX, int touchedY) {
        return (touchedX <= x + this.width && touchedX >= x) && (touchedY <= y + this.height && touchedY >= y);
    }


}
