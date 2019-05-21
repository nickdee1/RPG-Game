package com.example.nick.rpggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.example.nick.rpggame.GameObjectsModels.GameObject;


/**
 * Class of any button in game
 * */
public class GameButton extends GameObject {

    private Bitmap buttonBitmap;
    private int width;
    private int height;


    public GameButton(Bitmap image, int x, int y) {
        super(image, x, y);

        buttonBitmap = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    /**
     * Draw button on surface
     * */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(buttonBitmap, x, y, null);
    }

    /**
     * Return true if user pressed button, false otherwise
     * */
    public boolean isPressed(int touchedX, int touchedY) {
        return (touchedX <= x + this.width && touchedX >= x) && (touchedY <= y + this.height && touchedY >= y);
    }


}
