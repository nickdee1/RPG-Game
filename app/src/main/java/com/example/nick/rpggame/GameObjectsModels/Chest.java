package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.example.nick.rpggame.GameButton;


/**
 * Class for chest in game
 * */
public class Chest extends GameObject {


    private Bitmap chestImage;
    private Bitmap chestViewImage;
    private GameButton closeButton;
    private GameButton healingPotion;
    
    private boolean opened = false;
    private int healingPotionCount = 1;

    private int charsX;
    private int charsY;


    /**
     * Chest initialization
     * @param image - Bitmap image of chest
     * @param viewImage - Bitmap image of view after opening the chest
     * @param closeButtonBitmap - image of closeButton used to close chestView
     * @param healingPotion - Bitmap image of potion
     * @param x, y - coordinates of chest on canvas
     * */
    public Chest(Bitmap image, Bitmap viewImage, Bitmap closeButtonBitmap, Bitmap healingPotion, int x, int y) {
        super(image, x, y);

        this.chestImage = this.createImage();
        this.chestViewImage = viewImage;

        this.closeButton = new GameButton(Bitmap.createScaledBitmap(closeButtonBitmap, 180, 180, false), 1130, 350+60);
        this.healingPotion = new GameButton(Bitmap.createScaledBitmap(healingPotion, 360, 360, false), 510, 320);

    }

    /**
     * Draw chest and it's views on canvas
     * */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(chestImage, x, y, null);
        chestViewImage = Bitmap.createScaledBitmap(chestViewImage, 800, 300, false);

        if (opened) {
            canvas.drawBitmap(chestViewImage, 560, 350, null);
            this.closeButton.draw(canvas);

            if (this.healingPotionCount > 0) this.healingPotion.draw(canvas);
        }

    }


    /**
     * Checks if character opened chest
     * @param touchedX - coordinates of user's last screen tap
     * @param touchedY - coordinates of user's last screen tap
     * */
    public boolean characterOpenedChest(int touchedX, int touchedY) {
        return (Math.abs(charsX - this.getX()) < 70 && Math.abs(charsY - this.getY()) < 70) && (touchedX <= x + chestImage.getWidth() && touchedX >= x) && (touchedY <= y + chestImage.getHeight() && touchedY >= y);
    }

    /**
     * Checks if character closed chest
     * @param touchedX - coordinates of user's last screen tap
     * @param touchedY - coordinates of user's last screen tap
     * */
    public boolean characterClosedChest(int touchedX, int touchedY) {
        return closeButton.isPressed(touchedX, touchedY);
    }

    /**
     * Checks if character took item from chest
     * @param touchedX - coordinates of user's last screen tap
     * @param touchedY - coordinates of user's last screen tap
     * */
    public boolean characterTookPotion(int touchedX, int touchedY) {
        return healingPotion.isPressed(touchedX, touchedY);
    }

    /**
     * Gets number of potions chest contains
     * */
    public int getHealingPotionCount() {
        return healingPotionCount;
    }

    /**
     * Add potion to the chest
     * */
    public void setHealingPotionCount(int healingPotionCount) {
        this.healingPotionCount = healingPotionCount;
    }

    /**
     * Checks chest's current state (opened/not)
     * */
    public boolean isOpened() {
        return opened;
    }

    /**
     * Open/close chest
     * */
    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    /**
     * Methods used to check main character's current state on surface
     * */
    public void setCharsX(int charsX) {
        this.charsX = charsX;
    }

    public void setCharsY(int charsY) {
        this.charsY = charsY;
    }
}
