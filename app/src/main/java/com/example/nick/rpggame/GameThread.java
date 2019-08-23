package com.example.nick.rpggame;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Thread handles all the game events
 * */
public class GameThread extends Thread {

    private SurfaceType newSurface;
    private SurfaceHolder surfaceHolder;

    private boolean running;

    /**
     * Game thread initialization
     * @param surfaceHolder - game surface holder
     * */
    public GameThread(SurfaceType newSurface, SurfaceHolder surfaceHolder) {
        this.newSurface = newSurface;
        this.surfaceHolder = surfaceHolder;
    }

    /**
     * Makes thread run
     * */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Functionality during the run of thread
     * */
    public void run() {
        while (running) {

            Canvas canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();

                synchronized (canvas) {
                    this.newSurface.update();
                    this.newSurface.draw(canvas);

                }

            } catch(Exception ignored) {
                Log.v("Game Thread", "Unable to update game");
            } finally {
                if(canvas!=null) {
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }


}
