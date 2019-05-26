package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;


/**
 * Door initialization
 */
public class Door extends GameObject {

    private Bitmap doorImage;
    private MainCharacter mainCharacter;

    private int charsX;
    private int charsY;

    private Key key;

    /**
     * Game object initialization
     *
     * @param image - image of game object
     * @param x - x coordinate of Door
     * @param y - y coordinate of Door
     */
    public Door(Bitmap image, int x, int y, MainCharacter mainCharacter, Key key) {
        super(image, x, y);

        this.doorImage = image;
        this.mainCharacter = mainCharacter;
        this.key = key;
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
                if (this.key.equals(this.mainCharacter.getBlueKey()) || this.key.equals(this.mainCharacter.getYellowKey())) {

                Log.v("Door", "Opened");
                return true;
            }
        }
        return false;
    }


    /**
     * @param charsX - X coordinate of player
     */
    public void setCharsX(int charsX) {
        this.charsX = charsX;
    }


    /**
     * @param charsY - Y coordinate of player
     */
    public void setCharsY(int charsY) {
        this.charsY = charsY;
    }
}
