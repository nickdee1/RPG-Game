package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import com.example.nick.rpggame.GameButton;

public class Chest extends GameObject {


    private Bitmap chestImage;
    private Bitmap chestViewImage;
    private GameButton closeButton;
    private GameButton healingPotion;
    
    private boolean opened = false;
    private int healingPotionCount = 1;

    private int charsX;
    private int charsY;

    public Chest(Bitmap image, Bitmap viewImage, Bitmap closeButtonBitmap, Bitmap healingPotion, int x, int y) {
        super(image, x, y);

        this.chestImage = this.createImage();
        this.chestViewImage = viewImage;

        this.closeButton = new GameButton(Bitmap.createScaledBitmap(closeButtonBitmap, 180, 180, false), 1130, 350+60);
        this.healingPotion = new GameButton(Bitmap.createScaledBitmap(healingPotion, 360, 360, false), 510, 320);

    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(chestImage, x, y, null);
        chestViewImage = Bitmap.createScaledBitmap(chestViewImage, 800, 300, false);

        if (opened) {
            canvas.drawBitmap(chestViewImage, 560, 350, null);
            this.closeButton.draw(canvas);

            if (healingPotionCount > 0) this.healingPotion.draw(canvas);
        }

    }

    public boolean characterOpenedChest(int touchedX, int touchedY) {

        System.out.println(this.getX());

        if ((Math.abs(charsX - this.getX()) < 70 && Math.abs(charsY - this.getY()) < 70) && (touchedX <= x + chestImage.getWidth() && touchedX >= x) && (touchedY <= y + chestImage.getHeight() && touchedY >= y)) {
            return true;
        }
        return false;
    }

    public boolean characterClosedChest(int touchedX, int touchedY) {

        if (closeButton.isPressed(touchedX, touchedY)) return true;
        return false;
    }

    public boolean characterTookPotion(int touchedX, int touchedY) {
        if (healingPotion.isPressed(touchedX, touchedY)) return true;
        return false;
    }

    public int getHealingPotionCount() {
        return healingPotionCount;
    }

    public void setHealingPotionCount(int healingPotionCount) {
        this.healingPotionCount = healingPotionCount;
    }

    public boolean isOpened() {
        return opened;
    }


    public void setOpened(boolean opened) {
        this.opened = opened;
    }


    public void setCharsX(int charsX) {
        this.charsX = charsX;
    }

    public void setCharsY(int charsY) {
        this.charsY = charsY;
    }
}
