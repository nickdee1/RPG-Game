package com.example.nick.rpggame.GameObjectsModels;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import com.example.nick.rpggame.R;


/**
 * Door initialization
 */
public class Door extends GameObject {

    private Bitmap doorImage;
    private int charsX;
    private int charsY;


    /**
     * Game object initialization
     *
     * @param image - image of game object
     * @param x - x coordinate of Door
     * @param y - y coordinate of Door
     */
    public Door(Bitmap image, int x, int y) {
        super(image, x, y);

        this.doorImage = image;

    }


    /**
     * Draw door on canvas
     * @param canvas - game canvas
     */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(doorImage, x, y, null);
    }


    /**
     * Checks if player opened the door
     * @param touchedX - touched X point on screen
     * @param touchedY - touched Y point on screen
     * @return true/false depending on whether player opened door/not
     */
    public boolean playerOpenedDoor(int touchedX, int touchedY) {
        if ((Math.abs(charsX - this.getX()) < 70 && Math.abs(charsY - this.getY()) < 70) && (touchedX <= x + doorImage.getWidth() && touchedX >= x) && (touchedY <= y + doorImage.getHeight() && touchedY >= y)) {

            Log.v("Door", "Opened");
            return true;

        }
        return false;
    }


    /**
     * @param charsX - X coordinate of player
     * @param charsY - Y coordinate of player
     */
    public void setCharsCoordinates(int charsX, int charsY) {
        this.charsX = charsX;
        this.charsY = charsY;
    }

}
