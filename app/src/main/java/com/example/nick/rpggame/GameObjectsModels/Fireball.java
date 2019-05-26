package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;


/**
 * Fireball which is thrown by red skeletons
 */
public class Fireball extends GameObject {

    private MainCharacter mainCharacter;
    private Bitmap fireballImage;
    private float VELOCITY;

    private int movingVectorX;
    private int movingVectorY;

    private long lastDrawNanoTime;

    /**
     * Game object initialization
     *
     * @param image - image of fireball
     * @param x - x coordinate, where fireball would be drawn
     * @param y - y coordinate, where fireball would be drawn
     */
    public Fireball(Bitmap image, int x, int y, MainCharacter mainCharacter) {
        super(image, x, y);

        this.fireballImage = image;
        this.VELOCITY = 0.8f;
        this.lastDrawNanoTime = -1;

        this.mainCharacter = mainCharacter;
    }


    /**
     * Draw fireball on canvas
     * @param canvas -  game canvas
     */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(fireballImage, x, y, null);
        this.lastDrawNanoTime = System.nanoTime();
    }


    /**
     * Update fireball position on canvas
     */
    public void updateFireballMovement() {

        setMovingVector();

        int deltaTime = (int) ((System.nanoTime() - lastDrawNanoTime) / 1000000);

        float distance = VELOCITY * deltaTime;

        double movingVectorLength = Math.sqrt(movingVectorX * movingVectorX + movingVectorY * movingVectorY);

        this.x = x + (int)(distance* movingVectorX / movingVectorLength);
        this.y = y + (int)(distance* movingVectorY / movingVectorLength);
    }

    private void setMovingVector() {
        int mainCharX = this.mainCharacter.getX();
        int mainCharY = this.mainCharacter.getY();

        this.movingVectorX = mainCharX - this.getX();
        this.movingVectorY = mainCharY - this.getY();
    }


    /**
     * @return true/false if fireball touched main character
     */
    public boolean touchedMainCharacter() {
        return Math.abs(this.mainCharacter.getX() - this.getX()) < 5 && Math.abs(this.mainCharacter.getY() - this.getY()) < 5;
    }

}
