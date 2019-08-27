package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import android.view.SurfaceView;
import com.example.nick.rpggame.LevelSurfaceType;
import com.example.nick.rpggame.SurfaceType;


/**
 * Class for NPC in game
 * */
public class Skeleton extends ModelCharacter {

    private MainCharacter mainCharacter;
    private long hitTime = 0;


    /**
     * Skeleton initialization
     * @params:
     * @param gameSurface - surface that handles all graphic changes in game
     * @param image - image of character
     * @param x, y - coordinates on canvas
     * @param  mainCharacter - player's character
     * */
    public Skeleton(LevelSurfaceType gameSurface, Bitmap image, int x, int y, MainCharacter mainCharacter) {
        super(gameSurface, image, x, y);
        this.mainCharacter = mainCharacter;

        VELOCITY = (float) 0.4;
    }


    /**
     * Skeleton moves towards main character setting his moving vector due to X/Y position of main character
     * */
    private void setMovingVectorToMainChar() {
        int mainCharX = this.mainCharacter.getX();
        int mainCharY = this.mainCharacter.getY();

        int movingVectorX = mainCharX - this.getX();
        int movingVectorY = mainCharY - this.getY();

        setMovingVector(movingVectorX, movingVectorY);

        hitMainCharacter();
    }

    /**
     * Hits main character only within radius of 5 pixels and after 1 sec after last hit
     * */
    private void hitMainCharacter() {
        if ((Math.abs(this.mainCharacter.getX() - this.getX()) < 5 && Math.abs(this.mainCharacter.getY() - this.getY()) < 5)
                && (System.currentTimeMillis() - hitTime > 1000)) {

            if (mainCharacter.getArmor() > 0) mainCharacter.setArmor(mainCharacter.getArmor() - 1);
            else mainCharacter.setHealth(mainCharacter.getHealth() - 1);
            hitTime = System.currentTimeMillis();
        }
    }


    /**
     * Updates skeleton's movement
     * */
    @Override
    public void updateCharacterMovement() {
        super.updateCharacterMovement();
        setMovingVectorToMainChar();
    }
}
