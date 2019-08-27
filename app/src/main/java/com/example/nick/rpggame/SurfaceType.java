package com.example.nick.rpggame;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Template for any level surface in game
 */
public abstract class SurfaceType extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;

    public SurfaceType(Context context) {
        super(context);
        this.setFocusable(true);
        this.getHolder().addCallback(this);
    }


    public void update() {}

    /**
     * Actions after any surface is created
     * @param holder - The SurfaceHolder whose surface is being created
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        /* Make thread work after surface creation */
        this.thread = new GameThread(this, holder);
        this.thread.setRunning(true);
        this.thread.start();
    }


    /**
     * Actions after surface is changed
     * @param holder - The SurfaceHolder whose surface is being changed
     * @param format - The new PixelFormat of the surface
     * @param width - The new width of the surface
     * @param height - The new height of the surface
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }


    /**
     * Actions after surface is destroyed
     * @param holder - The SurfaceHolder whose surface is being destroyed
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        try {
            this.thread.setRunning(false);
            this.thread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
