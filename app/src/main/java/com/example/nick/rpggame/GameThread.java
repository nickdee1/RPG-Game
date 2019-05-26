package com.example.nick.rpggame;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Thread handles all the game events
 * */
public class GameThread extends Thread {

    private GameSurface gameSurface;
    private SurfaceHolder surfaceHolder;

    private boolean running;

    /**
     * Game thread initialization
     * @param gameSurface - surface which handles game actions
     * @param surfaceHolder - game surface holder
     * */
    public GameThread(GameSurface gameSurface, SurfaceHolder surfaceHolder) {
        this.gameSurface = gameSurface;
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
                        this.gameSurface.update();
                        this.gameSurface.draw(canvas);

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
