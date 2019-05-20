package com.example.nick.rpggame;

import android.content.Context;
import android.graphics.*;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.MotionEvent;
import com.example.nick.rpggame.GameObjectsModels.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The GameSurface initializes and displays every action that
 * happens in game on user's screen
 * */
public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;



    private MainMenu mainMenu;
    private GameButton playGameButton;

    /* Game objects */
    private MainCharacter mainCharacter;
    private Skeleton skeleton;
    private Chest chest;

    private List<Skeleton> skeletons = new ArrayList<Skeleton>();

    /* Game components */
    private Bitmap background;

    private List<Heart> health = new ArrayList<Heart>();
    private Bitmap heartBitmap;

    private GameButton gameButton;
    private GameButton hitButton;

    private GameButton resumeGameButton;
    private GameButton toMainMenuButton;

    private Bitmap pauseBackground;


    private Bitmap skeletonBitmap;
    private Bitmap knightBitmap;

    private boolean paused = false;
    private boolean started = false;


    private List<Tomb> tombs = new ArrayList<Tomb>();
    private Bitmap tombBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.tomb);

    private int numberOfNPC = 1;

    public GameSurface(Context context) {
        super(context);

        this.setFocusable(true);

        this.getHolder().addCallback(this);
    }


    /**
     * Game Images initialization after launching the game
     **/
    @Override
    public void surfaceCreated(SurfaceHolder holder) {


        /* Game Menu view initialization */
        Bitmap mainMenuBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.main_menu);
        mainMenuBitmap = Bitmap.createScaledBitmap(mainMenuBitmap, 1900, 1080, false);
        this.mainMenu = new MainMenu(mainMenuBitmap);

        Bitmap playButtonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.play_button);
        playButtonBitmap = Bitmap.createScaledBitmap(playButtonBitmap, 650, 200, false);
        this.playGameButton = new GameButton(playButtonBitmap, 625, 445);

        this.mainMenu.setPlayButton(playGameButton);


        skeletonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.skeleton);
        knightBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.knight);
        this.mainCharacter = new MainCharacter(this, knightBitmap,400,885);


        this.heartBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.heart_1);
        Bitmap chest = BitmapFactory.decodeResource(this.getResources(), R.drawable.chest);
        this.chest = new Chest(chest, 700, 300);


        /* Game components images initialization */
        Bitmap backgroundBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.background);
        Bitmap pauseBackgroundBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.pause_backgound);
        this.background = Bitmap.createScaledBitmap(backgroundBitmap, 1900, 1080, false);
        this.pauseBackground = Bitmap.createScaledBitmap(pauseBackgroundBitmap, 1130, 450, false);


        Bitmap pauseButtonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.pause_button);
        pauseButtonBitmap = Bitmap.createScaledBitmap(pauseButtonBitmap, 150, 150, false);
        this.gameButton = new GameButton(pauseButtonBitmap, 1620, 10);


        Bitmap resumeGameButtonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.resume_button);
        resumeGameButtonBitmap = Bitmap.createScaledBitmap(resumeGameButtonBitmap, 386, 116, false);
        this.resumeGameButton = new GameButton(resumeGameButtonBitmap, 750, 450);


        Bitmap toMainMenuButtonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.back_to_menu_button);
        toMainMenuButtonBitmap = Bitmap.createScaledBitmap(toMainMenuButtonBitmap, 386, 116, false);
        this.toMainMenuButton = new GameButton(toMainMenuButtonBitmap, 750, 590);


        Bitmap hitButtonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.hit_button);
        hitButtonBitmap = Bitmap.createScaledBitmap(hitButtonBitmap, 200, 200, false);
        this.hitButton = new GameButton(hitButtonBitmap, 1530, 840);



        this.gameThread = new GameThread(this, holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }



    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        try {
            this.gameThread.setRunning(false);
            this.gameThread.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     * Updates objects' state in game
     **/
    public void update() {


        if (started) {
            if (!paused) {

                for (Skeleton skeleton: this.skeletons) {
                    skeleton.updateCharacterMovement();
                }

                mainCharacter.updateCharacterMovement();
                characterHealthUpdate();

                if (skeletons.isEmpty()) {
                    numberOfNPC += 1;
                    mobsInit(numberOfNPC);
                }




            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            gameStarted();
        }
    }



    /**
     * Updates objects' place on canvas
     * */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (!started) {

            this.mainMenu.draw(canvas);
            this.playGameButton.draw(canvas);


        } else {

            canvas.drawBitmap(this.background, 0, 0, null);

            this.chest.draw(canvas);
            this.hitButton.draw(canvas);

            if (!tombs.isEmpty()) {
                for (Tomb tomb: tombs) {
                    tomb.draw(canvas);
                }
            }

            this.mainCharacter.draw(canvas);
            for (Skeleton skeleton: this.skeletons) {
                skeleton.draw(canvas);
            }

            for (Heart h : health) {
                h.draw(canvas);
            }


            if (paused) {
                canvas.drawBitmap(pauseBackground, 395, 315, null);
                this.resumeGameButton.draw(canvas);
                this.toMainMenuButton.draw(canvas);
            } else this.gameButton.draw(canvas);
        }
    }




    /**
     * Handles user's screen touch events (character's movement)
     **/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {


            int x =  (int)event.getX();
            int y = (int)event.getY();


            if (!started && playGameButton.isPressed(x, y)) {
                started = true;
                return true;


            } else {

                /* User touched PAUSE button */
                if (gameButton.isPressed(x, y)) {
                    paused = true;

                /* User touched HIT button */
                } else if (hitButton.isPressed(x, y)) {

                    System.out.println(tombs.size());

                    if (!skeletons.isEmpty()) {
                        for (Skeleton skeleton: this.skeletons) {

                           if (Math.abs(this.mainCharacter.getX() - skeleton.getX()) <= 20 &&
                                   Math.abs(this.mainCharacter.getY() - skeleton.getY()) <= 20) {

                                tombs.add(new Tomb(Bitmap.createScaledBitmap(tombBitmap, 100, 110, false),
                                        skeleton.getX(), skeleton.getY()));

                                System.out.printf("Is empty", tombs.isEmpty());
                                skeletons.remove(skeleton);
                                break;
                           }

                        }
                    }

                /* Switch to the game regime */
                } else if (paused && resumeGameButton.isPressed(x, y)) {
                    paused = !paused;

                /* Switch to MENU regime */
                } else if (paused && toMainMenuButton.isPressed(x, y)) {
                    started = false;
                    paused = false;

                /* Main character goes to touched point */
                } else if (!paused) {

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
        return false;
    }


    /**
     * Methods initializes health of character at the
     * beginning of game
     **/
    private void characterHealthUpdate() {

        this.health.clear();
        for (int i = 0; i < this.mainCharacter.getHealth(); i ++) {
            this.health.add(new Heart(heartBitmap, 50 + i*120, 50));
        }
    }


    private void mobsInit(int numberOfNPC) {
        for (int i = 0; i < numberOfNPC; i++) {
            this.skeletons.add(new Skeleton(this, skeletonBitmap, 1000 + i*30, 170 + i*43, this.mainCharacter));
        }
    }

    private void gameStarted() {
        this.mainCharacter = new MainCharacter(this, knightBitmap,400,885);
        skeletons.clear();
        tombs.clear();
        numberOfNPC = 1;
        mobsInit(numberOfNPC);
        characterHealthUpdate();
    }


}
