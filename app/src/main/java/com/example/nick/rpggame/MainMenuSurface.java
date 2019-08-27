package com.example.nick.rpggame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.view.SurfaceHolder;
import android.view.MotionEvent;


/**
 * The MainMenuSurface initializes and displays
 * main menu surface
 * */
public class MainMenuSurface extends SurfaceType implements SurfaceHolder.Callback {


    private GameButton playGameButton;
    private Bitmap mainMenuBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.main_menu);

    public MainMenuSurface(Context context) {
        super(context);
        this.setFocusable(true);
        this.getHolder().addCallback(this);

    }


    /**
     * Game Images initialization after launching the game
     * @param holder - game surface holder
     **/
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        super.surfaceCreated(holder);

        /* Images are used in game */
        Bitmap playButtonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.play_button);
        mainMenuBitmap = Bitmap.createScaledBitmap(mainMenuBitmap, getWidth(), getHeight(), false);

        /* Game buttons initialization */
        this.playGameButton = new GameButton(Bitmap.createScaledBitmap(playButtonBitmap, 650, 200, false), 625, 445);

    }


    /**
     * Updates objects' place on canvas
     * @param canvas - game canvas
     * */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawBitmap(mainMenuBitmap, 0, 0, null);
        this.playGameButton.draw(canvas);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        super.surfaceDestroyed(holder);

        mainMenuBitmap.recycle();
        playGameButton.recycle();
    }

    /**
     * Handles user's screen touch events (character's movement)
     **/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {


            int x = (int)event.getX();
            int y = (int)event.getY();


            /* Game starts after user touched PLAY button */
            if (playGameButton.isPressed(x, y)) {
                Intent intent = new Intent().setClass(getContext(), FirstLevelActivity.class);
                ((Activity) getContext()).startActivity(intent);
            }
        }
        return true;
    }
}
