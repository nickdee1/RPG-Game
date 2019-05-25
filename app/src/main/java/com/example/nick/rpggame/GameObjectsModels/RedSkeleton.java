package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.example.nick.rpggame.GameSurface;

public class RedSkeleton extends Skeleton {


    private MainCharacter mainCharacter;
    private boolean farFromMainCharacter = true;
    private Fireball fireball;

    private int movingVectorX;
    private int movingVectorY;

    private boolean thrown = false;

    /**
     * Skeleton initialization
     *
     * @param gameSurface
     * @param image
     * @param x
     * @param y
     * @param mainCharacter
     * @params: gameSurface - surface that handles all graphic changes in game
     * image - image of character
     * x, y - coordinates on canvas
     * mainCharacter - player's character
     */
    public RedSkeleton(GameSurface gameSurface, Bitmap image, int x, int y, MainCharacter mainCharacter, Bitmap fireballBitmap) {
        super(gameSurface, image, x, y, mainCharacter);

        this.mainCharacter = mainCharacter;
        this.fireball = new Fireball(fireballBitmap, x, y);

    }

    @Override
    public void updateCharacterMovement() {
        super.updateCharacterMovement();
        setMovingVectorOffMainChar();

    }

    private void setMovingVectorOffMainChar() {
        int mainCharX = this.mainCharacter.getX();
        int mainCharY = this.mainCharacter.getY();

        movingVectorX = mainCharX - this.getX();
        movingVectorY = mainCharY - this.getY();

        if (Math.abs(movingVectorX) > 60 && Math.abs(movingVectorY) > 60) {
            setMovingVector(0,0);
            this.setStopped(true);
            farFromMainCharacter = true;

        }
        else {
            setMovingVector(movingVectorX, movingVectorY);
            this.setStopped(false);
            farFromMainCharacter = false;
        }
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public void setThrown(boolean thrown) {
        this.thrown = thrown;
    }

    public boolean isThrown() {
        return thrown;
    }
}
