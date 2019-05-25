package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Fireball extends GameObject {


    private Bitmap fireballImage;
    private float VELOCITY;

    private int movingVectorX;
    private int movingVectorY;

    private long lastDrawNanoTime;
    /**
     * Game object initialization
     *
     * @param image
     * @param x
     * @param y
     * @params: image - image of game object
     * x, y - coordinates of creation on canvas
     */
    public Fireball(Bitmap image, int x, int y) {
        super(image, x, y);

        this.fireballImage = image;
        this.VELOCITY = 0.8f;
        this.lastDrawNanoTime = -1;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(fireballImage, x, y, null);
    }


    public void updateFireballMovement() {

        long now = System.nanoTime();

        int deltaTime = (int) ((now - lastDrawNanoTime) / 1000000);

        float distance = VELOCITY * deltaTime;

        double movingVectorLength = Math.sqrt(movingVectorX * movingVectorX + movingVectorY * movingVectorY);

        this.x = x +  (int)(distance* movingVectorX / movingVectorLength);
        this.y = y +  (int)(distance* movingVectorY / movingVectorLength);
    }

    public void setMovingVector(int movingVectorX, int movingVectorY) {
        this.movingVectorX = movingVectorX;
        this.movingVectorY = movingVectorY;
    }



}
