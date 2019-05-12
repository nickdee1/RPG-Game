package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.example.nick.rpggame.GameSurface;


import java.util.concurrent.TimeUnit;

/**
 * Blueprint for any character in game
 * */

public class ModelCharacter extends Character{

    private static final int ROW_TOP_TO_BOTTOM = 0;
    private static final int ROW_RIGHT_TO_LEFT = 1;
    private static final int ROW_LEFT_TO_RIGHT = 2;
    private static final int ROW_BOTTOM_TO_TOP = 3;

    private int rowUsing = ROW_LEFT_TO_RIGHT;
    private int colUsing;

    private boolean stopped = false;

    private Bitmap[] leftToRights;
    private Bitmap[] rightToLefts;
    private Bitmap[] topToBottoms;
    private Bitmap[] bottomToTops;

    private static final float VELOCITY = 0.5f;

    private int movingVectorX = 10;
    private int movingVectorY = 5;

    private int stopped_X;
    private int stopped_Y;


    private long lastDrawNanoTime = -1;

    private GameSurface gameSurface;

    public ModelCharacter(GameSurface gameSurface, Bitmap image, int x, int y) {
        super(image, 4, 3, x, y);

        this.gameSurface = gameSurface;

        this.topToBottoms = new Bitmap[columnCount]; // 3
        this.rightToLefts = new Bitmap[columnCount]; // 3
        this.leftToRights = new Bitmap[columnCount]; // 3
        this.bottomToTops = new Bitmap[columnCount]; // 3

        for(int col = 0; col < this.columnCount; col++) {
            this.topToBottoms[col] = this.createImageAt(ROW_TOP_TO_BOTTOM, col);
            this.rightToLefts[col]  = this.createImageAt(ROW_RIGHT_TO_LEFT, col);
            this.leftToRights[col] = this.createImageAt(ROW_LEFT_TO_RIGHT, col);
            this.bottomToTops[col]  = this.createImageAt(ROW_BOTTOM_TO_TOP, col);
        }
    }

    /* Animation handling methods */


    /**
     * Returns current Bitmap image of character's movement
     * */

    private Bitmap getCurrentMoveBitmap()  {
        Bitmap[] bitmaps = this.getMoveBitmaps();
        assert bitmaps != null;
        return bitmaps[this.colUsing];
    }

    private Bitmap[] getMoveBitmaps()  {
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
        if (movingVectorY > 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) this.rowUsing = ROW_TOP_TO_BOTTOM;
         else if (movingVectorY < 0 && Math.abs(movingVectorX) < Math.abs(movingVectorY)) this.rowUsing = ROW_BOTTOM_TO_TOP;
         else if (movingVectorX < 0) this.rowUsing = ROW_RIGHT_TO_LEFT;
         else  this.rowUsing = ROW_LEFT_TO_RIGHT;
    }

    /**
     * Draw character on surface with his current movement image
     * */

    public void draw(Canvas canvas)  {
        Bitmap bitmap = this.getCurrentMoveBitmap();
        canvas.drawBitmap(bitmap, x, y, null);
        this.lastDrawNanoTime = System.nanoTime();
    }

    /* Movement handling methods */

    /**
     * Method handles character's movement
     * */
    public void updateCharacterMovement()  {


        animationEnabled();

        if ((x >= stopped_X - 40 && x <= stopped_X + 40) && (y >= stopped_Y - 40 && y <= stopped_Y + 40))
            setMovingVector(0,0);

        run_mov();

        //Character stops if touches the edge of screen
        if(this.x < 0)  {
            this.x = 0;
            this.movingVectorX = 0;
        } else if(this.x > this.gameSurface.getWidth() - characterWidth)  {
            this.x = this.gameSurface.getWidth() - characterWidth;
            this.movingVectorX = 0;
        }

        if(this.y < 0)  {
            this.y = 0;
            this.movingVectorY = 0;
        } else if(this.y > this.gameSurface.getHeight() - characterHeight)  {
            this.y = this.gameSurface.getHeight() - characterHeight;
            this.movingVectorY = 0 ;
        }

        changeCharacterModel();
    }


    private void run_mov() {
        long now = System.nanoTime();

        int deltaTime = (int) ((now - lastDrawNanoTime) / 1000000);

        float distance = VELOCITY * deltaTime;

        double movingVectorLength = Math.sqrt(movingVectorX* movingVectorX + movingVectorY*movingVectorY);

        // Calculate new position of the game character.
        this.x = x +  (int)(distance* movingVectorX / movingVectorLength);
        this.y = y +  (int)(distance* movingVectorY / movingVectorLength);

    }


    public void setMovingVector(int movingVectorX, int movingVectorY)  {
        this.movingVectorX = movingVectorX;
        this.movingVectorY = movingVectorY;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    private boolean isSetStopped() {
        return stopped;
    }


    public void setStopped_X(int stopped_X) {
        this.stopped_X = stopped_X;
    }

    public void setStopped_Y(int stopped_Y) {
        this.stopped_Y = stopped_Y;
    }
}