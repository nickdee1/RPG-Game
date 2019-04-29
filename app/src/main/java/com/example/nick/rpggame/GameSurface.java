package com.example.nick.rpggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.MotionEvent;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;
    private ModelCharacter mainCharacter;
    private Skeleton skeleton;

    public GameSurface(Context context) {
        super(context);

        this.setFocusable(true);

        this.getHolder().addCallback(this);
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Bitmap skeletonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.skeleton);
        Bitmap knightBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.knight);

        this.mainCharacter = new ModelCharacter(this, knightBitmap,100,50);
        this.skeleton = new Skeleton(this, skeletonBitmap,300,150);

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

    public void update() {

        skeleton.updateCharacterMovement();
        mainCharacter.updateCharacterMovement();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.mainCharacter.draw(canvas);
        this.skeleton.draw(canvas);
    }

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
