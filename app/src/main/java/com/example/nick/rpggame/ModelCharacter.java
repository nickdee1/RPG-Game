package com.example.nick.rpggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.concurrent.TimeUnit;

public class ModelCharacter extends Character {

    private static final int ROW_TOP_TO_BOTTOM = 0;
    private static final int ROW_RIGHT_TO_LEFT = 1;
    private static final int ROW_LEFT_TO_RIGHT = 2;
    private static final int ROW_BOTTOM_TO_TOP = 3;

    private int rowUsing = ROW_LEFT_TO_RIGHT;
    private int colUsing;

    private boolean stopped = true;

    private Bitmap[] leftToRights;
    private Bitmap[] rightToLefts;
    private Bitmap[] topToBottoms;
    private Bitmap[] bottomToTops;

    private static final float VELOCITY = 0.5f;

    private int movingVectorX = 10;
    private int movingVectorY = 5;


    private long lastDrawNanoTime = -1;

    private GameSurface gameSurface;

    public ModelCharacter(GameSurface gameSurface, Bitmap image, int x, int y) {
        super(image, 4, 3, x, y);

        this.gameSurface = gameSurface;

        this.topToBottoms = new Bitmap[columnCount]; // 3
        this.rightToLefts = new Bitmap[columnCount]; // 3
        this.leftToRights = new Bitmap[columnCount]; // 3
        this.bottomToTops = new Bitmap[columnCount]; // 3

        for(int col = 0; col < this.columnCount; col++ ) {
            this.topToBottoms[col] = this.createImageAt(ROW_TOP_TO_BOTTOM, col);
            this.rightToLefts[col]  = this.createImageAt(ROW_RIGHT_TO_LEFT, col);
            this.leftToRights[col] = this.createImageAt(ROW_LEFT_TO_RIGHT, col);
            this.bottomToTops[col]  = this.createImageAt(ROW_BOTTOM_TO_TOP, col);
        }
    }

    /* Animation handling methods */

    public Bitmap[] getMoveBitmaps()  {
        switch (rowUsing)  {
            case ROW_BOTTOM_TO_TOP:
                return  this.bottomToTops;
            case ROW_LEFT_TO_RIGHT:
                return this.leftToRights;
            case ROW_RIGHT_TO_LEFT:
                return this.rightToLefts;
            case ROW_TOP_TO_BOTTOM:
                return this.topToBottoms;
            default:
                return null;
        }
    }

    public Bitmap getCurrentMoveBitmap()  {
        Bitmap[] bitmaps = this.getMoveBitmaps();
        return bitmaps[this.colUsing];
    }

    private void animationEnabled() {
        if (isSetStopped()) {
            this.colUsing = 1;
            this.rowUsing = 0;
        }
        else {
            this.colUsing++;
            if(colUsing >= this.columnCount)  {
                this.colUsing = 0;
            }
        }
    }

    private void changeCharacterModel() {
        if(movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
            this.rowUsing = ROW_TOP_TO_BOTTOM;
        }else if(movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) {
            this.rowUsing = ROW_BOTTOM_TO_TOP;
        }else  {
            this.rowUsing = ROW_LEFT_TO_RIGHT;
        }
    }

    public void draw(Canvas canvas)  {
        Bitmap bitmap = this.getCurrentMoveBitmap();
        canvas.drawBitmap(bitmap, x, y, null);
        this.lastDrawNanoTime= System.nanoTime();
    }

    /* Movement handling methods */

    public void run () {
        long now = System.nanoTime();

        int deltaTime = (int) ((now - lastDrawNanoTime) / 1000000);

        float distance = VELOCITY * deltaTime;

        double movingVectorLength = Math.sqrt(movingVectorX* movingVectorX + movingVectorY*movingVectorY);

        // Calculate new position of the game character.
        this.x = x +  (int)(distance* movingVectorX / movingVectorLength);
        this.y = y +  (int)(distance* movingVectorY / movingVectorLength);

    }

    public void stop() {

        double distance = Math.sqrt(movingVectorX*movingVectorX + movingVectorY*movingVectorY);

        try {
            TimeUnit.MILLISECONDS.sleep((long) ((long) distance * 2.3));
            this.setMovingVector(0,0);
            this.setStopped(false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateCharacterMovement()  {

        animationEnabled();
        run();

        //Character stops if touches the edge of screen
        if(this.x < 0)  {
            this.x = 0;
            this.movingVectorX = 0;
        } else if(this.x > this.gameSurface.getWidth() - characterWidth)  {
            this.x = this.gameSurface.getWidth() - characterWidth;
            this.movingVectorX = 0;
        }

        if(this.y < 0 )  {
            this.y = 0;
            this.movingVectorY = 0;
        } else if(this.y > this.gameSurface.getHeight() - characterHeight)  {
            this.y = this.gameSurface.getHeight() - characterHeight;
            this.movingVectorY = 0 ;
        }


        if( movingVectorX > 0 )  {
            changeCharacterModel();
        } else {
            changeCharacterModel();
        }
    }


    public void setMovingVector(int movingVectorX, int movingVectorY)  {
        this.movingVectorX = movingVectorX;
        this.movingVectorY = movingVectorY;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public boolean isSetStopped() {
        return stopped;
    }
}