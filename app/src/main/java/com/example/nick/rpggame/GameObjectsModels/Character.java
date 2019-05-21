package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;

/**
 * Interface for any character in game
 **/
public abstract class Character {

    protected Bitmap image;

    final int rowCount;
    final int columnCount;

    final int characterWidth;
    final int characterHeight;

    int x;
    int y;


    /**
     * Character initialization
     * @params:
     * image - image would be displayed on screen
     * rowCount - count of rows in image (used for animation)
     * columnCount - count of columns in image (used for animation)
     * x, y - location of character after initialization
     * */
    public Character(Bitmap image, int rowCount, int columnCount, int x, int y) {
        this.image = image;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.x = x;
        this.y = y;

        int IMAGE_HEIGHT = image.getHeight();
        int IMAGE_WIDTH = image.getWidth();

        this.characterHeight = IMAGE_HEIGHT / rowCount;
        this.characterWidth = IMAGE_WIDTH / columnCount;
    }

    /**
     * Creates character's model from PNG image
     * @params:
     * row, column - values used for character image creation at certain row and column
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
