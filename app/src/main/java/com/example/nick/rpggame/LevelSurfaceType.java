package com.example.nick.rpggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import com.example.nick.rpggame.GameObjectsModels.MainCharacter;

abstract public class LevelSurfaceType extends SurfaceType {

    public MainCharacter mainCharacter;
    public Bitmap knightBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.knight);
    public Bitmap background;


    public LevelSurfaceType(Context context) {
        super(context);
        this.setFocusable(true);
        this.getHolder().addCallback(this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        super.surfaceCreated(holder);
        this.mainCharacter = new MainCharacter(this, knightBitmap,400,885);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        super.surfaceDestroyed(holder);
        this.mainCharacter.recycle();
        knightBitmap.recycle();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public void update() {
        this.mainCharacter.updateCharacterMovement();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_MOVE) {

            int touchedX = (int)event.getX();
            int touchedY = (int)event.getY();

            this.mainCharacter.setStopped(false);


            final int movingVectorX = touchedX - this.mainCharacter.getX();
            final int movingVectorY = touchedY - this.mainCharacter.getY();

            this.mainCharacter.setMovingVector(movingVectorX, movingVectorY);

            this.mainCharacter.setStopped_X(touchedX);
            this.mainCharacter.setStopped_Y(touchedY);
        }
        return true;
    }
}
