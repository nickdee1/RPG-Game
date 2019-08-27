package com.example.nick.rpggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class SecondLevelSurface extends LevelSurfaceType {

    // TODO: - Change red skeleton behavior

    private Bitmap background;
    private Bitmap skeletonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.skeleton_red);
    private Bitmap fireballBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.fireball), 40, 40, false);


    public SecondLevelSurface(Context context) {
        super(context);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        super.surfaceCreated(holder);

        this.background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.level_2), this.getWidth(), this.getHeight(), false);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        super.surfaceDestroyed(holder);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawBitmap(background, 0,0, null);
        this.mainCharacter.draw(canvas);
    }

    @Override
    public void update() {
        super.update();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
