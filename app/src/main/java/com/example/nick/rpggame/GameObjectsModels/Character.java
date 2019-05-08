package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;

/**
 * Interface for any character in game
 **/

public abstract class Character {

    protected Bitmap image;

    final int rowCount;
    final int columnCount;

    private final int IMAGE_WIDTH;
    private final int IMAGE_HEIGHT;

    final int characterWidth;
    final int characterHeight;

    int x;
    int y;

    public Character(Bitmap image, int rowCount, int columnCount, int x, int y) {
        this.image = image;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.x = x;
        this.y = y;

        this.IMAGE_HEIGHT = image.getHeight();
        this.IMAGE_WIDTH = image.getWidth();

        this.characterHeight = this.IMAGE_HEIGHT / rowCount;
        this.characterWidth = this.IMAGE_WIDTH / columnCount;
    }

    /**
     * Creates character's model from PNG image
     * */
    public Bitmap createImageAt(int row, int column) {
        return Bitmap.createBitmap(image, column*characterWidth, row*characterWidth, characterWidth, characterHeight);
    }

    public int getCharacterWidth() {
        return characterWidth;
    }

    public int getCharacterHeight() {
        return characterHeight;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
