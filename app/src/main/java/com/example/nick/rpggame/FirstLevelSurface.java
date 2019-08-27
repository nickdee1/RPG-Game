package com.example.nick.rpggame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.drm.DrmStore;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import com.example.nick.rpggame.GameObjectsModels.Door;
import com.example.nick.rpggame.GameObjectsModels.MainCharacter;
import com.example.nick.rpggame.GameObjectsModels.Skeleton;


public class FirstLevelSurface extends LevelSurfaceType {

    // TODO: - Change door behavior

    private Bitmap background;
    private Bitmap skeletonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.skeleton);
    private Bitmap doorBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.door), 100, 160, false);
    private Skeleton skeleton;
    private Door door;

    public FirstLevelSurface(Context context) {
        super(context);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        super.surfaceCreated(holder);

        this.background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.background), this.getWidth(), this.getHeight(), false);
        this.skeleton = new Skeleton(this, skeletonBitmap, 1000, 1000, mainCharacter);
        this.door = new Door(doorBitmap, getWidth() - 200, 600);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        super.surfaceDestroyed(holder);

        this.skeleton.recycle();
    }

    @Override
    public void update() {
        super.update();
        this.skeleton.updateCharacterMovement();

        this.door.setCharsCoordinates(mainCharacter.getX(), mainCharacter.getY());
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawBitmap(this.background, 0, 0, null);
        this.skeleton.draw(canvas);
        this.mainCharacter.draw(canvas);
        this.door.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int touchedX = (int) event.getX();
            int touchedY = (int) event.getY();

            if (door.playerOpenedDoor(touchedX, touchedY)) {
                Intent intent = new Intent().setClass(getContext(), SecondLevelActivity.class);
                ((Activity) getContext()).startActivity(intent);
            }

        }
        return true;
    }
}
