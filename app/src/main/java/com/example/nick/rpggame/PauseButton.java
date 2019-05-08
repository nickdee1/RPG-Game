package com.example.nick.rpggame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import com.example.nick.rpggame.GameObjectsModels.GameObject;

public class PauseButton extends GameObject {

    private boolean pressed;
    private Bitmap pausedButtonBitmap;


    public PauseButton(Bitmap image, int x, int y) {
        super(image, x, y);

        pausedButtonBitmap = image;
    }

    /* Draw pause button */
    public void draw(Canvas canvas) {
        canvas.drawBitmap(pausedButtonBitmap, x, y, null);
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }


}
