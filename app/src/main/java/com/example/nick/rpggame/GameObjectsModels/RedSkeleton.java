package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.example.nick.rpggame.GameSurface;


/**
 * Red skeleton initialization
 */
public class RedSkeleton extends Skeleton {


    private MainCharacter mainCharacter;
    private boolean farFromMainCharacter = true;
    private Fireball fireball;

    private int movingVectorX;
    private int movingVectorY;

    private boolean hit = false;
    private boolean farFromCharacter = true;

    /**
     * Skeleton initialization
     * @param gameSurface - surface that handles all graphic changes in game
     * @param image - image of skeleton
     * @param x - coordinates on canvas
     * @param y - coordinates on canvas
     * @param mainCharacter - player's character
     */
    public RedSkeleton(GameSurface gameSurface, Bitmap image, int x, int y, MainCharacter mainCharacter, Bitmap fireballBitmap) {
        super(gameSurface, image, x, y, mainCharacter);

        this.mainCharacter = mainCharacter;
        this.fireball = new Fireball(fireballBitmap, this.x, this.y, mainCharacter);

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

        if (Math.abs(movingVectorX) > 40 && Math.abs(movingVectorY) > 40) {
            setMovingVector(0,0);
            this.setStopped(true);
            this.farFromMainCharacter = true;

            this.fireball.updateFireballMovement();

            if (this.fireball.touchedMainCharacter()) {
                if (mainCharacter.getArmor() > 0) mainCharacter.setArmor(mainCharacter.getArmor() - 1);
                else mainCharacter.setHealth(mainCharacter.getHealth() - 1);
                hit = true;
            }
        }
        else {
            setMovingVector(movingVectorX, movingVectorY);
            this.setStopped(false);
            this.farFromMainCharacter = false;
        }
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (!hit && this.farFromMainCharacter) this.fireball.draw(canvas);
    }


}
