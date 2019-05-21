package com.example.nick.rpggame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Class for Main Menu
 * */
public class MainMenu {

    private Bitmap mainMenuBackground;

    public MainMenu(Bitmap mainMenuBackground) {
        this.mainMenuBackground = mainMenuBackground;
    }

    /**
     * Draw Main Menu on screen
     * */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(mainMenuBackground, 0, 0, null);
    }
}
