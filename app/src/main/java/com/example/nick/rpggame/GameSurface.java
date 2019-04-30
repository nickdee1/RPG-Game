package com.example.nick.rpggame;

import android.content.Context;
import android.graphics.*;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;


/**
 * The GameSurface initializes and displays every action that
 * happens in game on user's screen
 * */
public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;

    private ModelCharacter mainCharacter;
    private Skeleton skeleton;
    private List<Heart> health = new ArrayList<Heart>();


    /**
     * Methods initializes health of character at the
     * beginning of game
     **/
    private void characterHealthInit(Bitmap heartBitmap) {
        Heart h1 = new Heart(this, heartBitmap, 50, 50);
        Heart h2 = new Heart(this, heartBitmap, 170, 50);
        Heart h3 = new Heart(this, heartBitmap, 290, 50);

        this.health.add(h1);
        this.health.add(h2);
        this.health.add(h3);
    }


    public GameSurface(Context context) {
        super(context);

        this.setFocusable(true);

        this.getHolder().addCallback(this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        Bitmap skeletonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.skeleton);
        Bitmap knightBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.knight);
        Bitmap heart = BitmapFactory.decodeResource(this.getResources(), R.drawable.heart_1);

        this.mainCharacter = new ModelCharacter(this, knightBitmap,200,885);
        this.skeleton = new Skeleton(this, skeletonBitmap,300,150);

        characterHealthInit(heart);

        this.gameThread = new GameThread(this, holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }



    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        while (retry) {
            try {
                this.gameThread.setRunning(false);
                this.gameThread.join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            retry = true;
        }
    }

    /**
     * Updates objects' state on Game Surface
     **/
    public void update() {
        skeleton.updateCharacterMovement();
        mainCharacter.updateCharacterMovement();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        this.mainCharacter.draw(canvas);
        this.skeleton.draw(canvas);

        for (Heart h: health) {
            h.draw(canvas);
        }
    }

    /**
     * Handles user's screen touch events (character's movement)
     **/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {


            int x =  (int)event.getX();
            int y = (int)event.getY();

            this.mainCharacter.setStopped(false);
            this.skeleton.setStopped(false);

            final int movingVectorX = x -  this.mainCharacter.getX() ;
            final int movingVectorY = y -  this.mainCharacter.getY() ;

            final int movingVectorX1 = x -  this.skeleton.getX() ;
            final int movingVectorY1 = y -  this.skeleton.getY() ;


            this.mainCharacter.setMovingVector(movingVectorX, movingVectorY);
            this.mainCharacter.stop();
            this.mainCharacter.setStopped(true);

            this.skeleton.setMovingVector(movingVectorX1, movingVectorY1);


            return true;
        }
        return false;
    }

}
