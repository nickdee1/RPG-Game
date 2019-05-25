package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class Door extends GameObject {

    private Bitmap doorImage;
    private boolean closed;
    private MainCharacter mainCharacter;

    private int charsX;
    private int charsY;

    private Key key;

    /**
     * Game object initialization
     *
     * @param image
     * @param x
     * @param y
     * @params: image - image of game object
     * x, y - coordinates of creation on canvas
     */
    public Door(Bitmap image, int x, int y, MainCharacter mainCharacter, Key key) {
        super(image, x, y);

        this.doorImage = image;
        this.closed = true;
        this.mainCharacter = mainCharacter;
        this.key = key;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(doorImage, x, y, null);
    }


    public boolean playerOpenedDoor(int touchedX, int touchedY) {
        if ((Math.abs(charsX - this.getX()) < 70 && Math.abs(charsY - this.getY()) < 70) && (touchedX <= x + doorImage.getWidth() && touchedX >= x) && (touchedY <= y + doorImage.getHeight() && touchedY >= y)) {
                if (this.key.equals(this.mainCharacter.getBlueKey()) || this.key.equals(this.mainCharacter.getYellowKey())) {
                this.closed = false;
                Log.v("Door", "Opened");
                return true;
            }
        }
        return false;
    }


    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public void setCharsX(int charsX) {
        this.charsX = charsX;
    }

    public void setCharsY(int charsY) {
        this.charsY = charsY;
    }
}
