package com.example.nick.rpggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import com.example.nick.rpggame.GameObjectsModels.MainCharacter;



public class FirstLevelSurface extends SurfaceType {

    private Bitmap background;
    private Bitmap knightBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.knight);
    private Bitmap keyYellowBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.key);

    private MainCharacter mainCharacter;

    public FirstLevelSurface(Context context) {
        super(context);
        this.setFocusable(true);
        this.getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        super.surfaceCreated(holder);

        background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.background), this.getWidth(), this.getHeight(), false);
        this.mainCharacter = new MainCharacter(this, knightBitmap,400,885);

    }


    public void update() {
        this.mainCharacter.updateCharacterMovement();
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawBitmap(this.background, 0, 0, null);
        canvas.drawBitmap(keyYellowBitmap, 200, 200, null);
        this.mainCharacter.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int)event.getX();
            int y = (int)event.getY();

            this.mainCharacter.setStopped(false);

            final int movingVectorX = x - this.mainCharacter.getX();
            final int movingVectorY = y - this.mainCharacter.getY();

            this.mainCharacter.setMovingVector(movingVectorX, movingVectorY);

            this.mainCharacter.setStopped_X(x);
            this.mainCharacter.setStopped_Y(y);

        }

        return true;
    }
}
