package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import com.example.nick.rpggame.GameSurface;

public class Skeleton extends ModelCharacter {

    private MainCharacter mainCharacter;


    public Skeleton(GameSurface gameSurface, Bitmap image, int x, int y, MainCharacter mainCharacter) {
        super(gameSurface, image, x, y);
        this.mainCharacter = mainCharacter;

        VELOCITY = (float) 0.4;
    }

    private void setMovingVectorToMainChar() {
        int mainCharX = this.mainCharacter.getX();
        int mainCharY = this.mainCharacter.getY();

        int movingVectorX = mainCharX - this.getX();
        int movingVectorY = mainCharY - this.getY();

        setMovingVector(movingVectorX, movingVectorY);

        hitMainCharacter();
    }

    private void hitMainCharacter() {
        if (Math.abs(this.mainCharacter.getX() - this.getX()) <= 15 || Math.abs(this.mainCharacter.getY() - this.getY()) <= 15)
            mainCharacter.setHealth(mainCharacter.getHealth() - 1);
    }

    @Override
    public void updateCharacterMovement() {
        super.updateCharacterMovement();
        setMovingVectorToMainChar();
    }
}
