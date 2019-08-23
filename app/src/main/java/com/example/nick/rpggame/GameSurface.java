package com.example.nick.rpggame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.view.SurfaceHolder;
import android.view.MotionEvent;


/**
 * The GameSurface initializes and displays every action that
 * happens in game on user's screen
 * */
public class GameSurface extends SurfaceType implements SurfaceHolder.Callback {


    /* Game Buttons */
    private MainMenu mainMenu;
    private GameButton playGameButton;



    public GameSurface(Context context) {
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
        Bitmap mainMenuBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.main_menu);
        Bitmap playButtonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.play_button);


        /* Game subviews and backgrounds initialization */
        this.mainMenu = new MainMenu(Bitmap.createScaledBitmap(mainMenuBitmap, this.getWidth(), this.getHeight(), false));

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

        this.mainMenu.draw(canvas);
        this.playGameButton.draw(canvas);

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
