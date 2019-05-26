package com.example.nick.rpggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.example.nick.rpggame.GameObjectsModels.GameObject;


/**
 * Blueprint of any button in game
 * */
public class GameButton extends GameObject {

    private Bitmap buttonBitmap;
    private int width;
    private int height;


    /**
     * Game button initialization
     * @param image - image of button
     * @param x, y - coordinates of button
     * */
    public GameButton(Bitmap image, int x, int y) {
        super(image, x, y);

        buttonBitmap = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    /**
     * Draw button on surface
     * @param canvas - game canvas
     * */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(buttonBitmap, x, y, null);
    }

    /**
     * Return true if user pressed button, false otherwise
     * @param touchedX - coordinates of user's last screen tap
     * @param touchedY - coordinates of user's last screen tap
     * */
    public boolean isPressed(int touchedX, int touchedY) {
        return (touchedX <= x + this.width && touchedX >= x) && (touchedY <= y + this.height && touchedY >= y);
    }


}
