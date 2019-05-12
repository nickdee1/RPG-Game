package com.example.nick.rpggame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class MainMenu {

    private Bitmap mainMenuBackground;
    private GameButton playButton;


    public MainMenu(Bitmap mainMenuBackground) {
        this.mainMenuBackground = mainMenuBackground;
    }


    public void setPlayButton(GameButton playButton) {
        this.playButton = playButton;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(mainMenuBackground, 0, 0, null);
    }
}
