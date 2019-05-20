package com.example.nick.rpggame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    private GameSurface gameSurface;
    private SurfaceHolder surfaceHolder;

    private boolean running;

    public GameThread(GameSurface gameSurface, SurfaceHolder surfaceHolder) {
        this.gameSurface = gameSurface;
        this.surfaceHolder = surfaceHolder;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

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

            } finally {
                if(canvas!=null) {
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }



}
