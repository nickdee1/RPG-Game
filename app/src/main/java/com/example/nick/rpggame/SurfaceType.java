package com.example.nick.rpggame;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

abstract class SurfaceType extends SurfaceView implements SurfaceHolder.Callback {


    public GameThread thread;


    public SurfaceType(Context context) {
        super(context);
        this.setFocusable(true);
        this.getHolder().addCallback(this);
    }

    public void update() {}


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        /* Make thread work after surface creation */
        this.thread = new GameThread(this, holder);
        this.thread.setRunning(true);
        this.thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

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
