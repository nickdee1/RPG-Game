package com.example.nick.rpggame;

import android.content.Context;
import android.graphics.*;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.MotionEvent;
import com.example.nick.rpggame.GameObjectsModels.*;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;

/**
 * The GameSurface initializes and displays every action that
 * happens in game on user's screen
 * */
public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {


    private GameThread gameThread;


    /* Game Buttons */
    private MainMenu mainMenu;
    private GameButton playGameButton;
    private GameButton gameButton;
    private GameButton hitButton;
    private GameButton resumeGameButton;
    private GameButton toMainMenuButton;
    private GameButton useHealingPotion;


    /* Game objects */
    private MainCharacter mainCharacter;
    private List<Skeleton> skeletons = new ArrayList<>();
    private List<Tomb> tombs = new ArrayList<>();
    private Chest chest;


    /* Game indicators */
    private List<HealthIndicator> health = new ArrayList<>();
    private List<HealthIndicator> armor = new ArrayList<>();

    /* Game images */
    private Bitmap background;
    private Bitmap pauseBackground;
    private Bitmap heartBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.heart_1);
    private Bitmap armorBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.armor_shield);
    private Bitmap gameOver = BitmapFactory.decodeResource(this.getResources(), R.drawable.game_over);
    private Bitmap skeletonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.skeleton);
    private Bitmap knightBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.knight);
    private Bitmap tombBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.tomb);
    private Bitmap healingPotionBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.healing_potion);


    /* Game switchers */
    private boolean paused = false;
    private boolean started = false;
    private int numberOfNPC = 1;



    public GameSurface(Context context) {
        super(context);
        this.setFocusable(true);
        this.getHolder().addCallback(this);
    }


    /**
     * Game Images initialization after launching the game
     * @params:
     * holder - game surface holder
     **/
    @Override
    public void surfaceCreated(SurfaceHolder holder) {


        /* Images are used in game */
        Bitmap mainMenuBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.main_menu);
        Bitmap playButtonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.play_button);
        Bitmap chest = BitmapFactory.decodeResource(this.getResources(), R.drawable.chest);
        Bitmap chestView = BitmapFactory.decodeResource(this.getResources(), R.drawable.chest_view_image);
        Bitmap closeButtonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.close_buttom);
        Bitmap backgroundBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.background);
        Bitmap pauseBackgroundBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.pause_backgound);
        Bitmap pauseButtonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.pause_button);
        Bitmap resumeGameButtonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.resume_button);
        Bitmap toMainMenuButtonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.back_to_menu_button);
        Bitmap hitButtonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.hit_button);


        /* Game subviews and backgrounds initialization */
        this.mainMenu = new MainMenu(Bitmap.createScaledBitmap(mainMenuBitmap, 1900, 1080, false));
        this.chest = new Chest(chest, chestView, closeButtonBitmap, healingPotionBitmap,700, 300);
        this.background = Bitmap.createScaledBitmap(backgroundBitmap, 1900, 1080, false);
        this.pauseBackground = Bitmap.createScaledBitmap(pauseBackgroundBitmap, 1130, 450, false);


        /* Main character initialization */
        this.mainCharacter = new MainCharacter(this, knightBitmap,400,885);


        /* Game buttons initialization */
        this.gameButton = new GameButton(Bitmap.createScaledBitmap(pauseButtonBitmap, 150, 150, false), 1620, 10);
        this.playGameButton = new GameButton(Bitmap.createScaledBitmap(playButtonBitmap, 650, 200, false), 625, 445);
        this.resumeGameButton = new GameButton(Bitmap.createScaledBitmap(resumeGameButtonBitmap, 386, 116, false), 750, 450);
        this.toMainMenuButton = new GameButton(Bitmap.createScaledBitmap(toMainMenuButtonBitmap, 386, 116, false), 750, 590);
        this.hitButton = new GameButton(Bitmap.createScaledBitmap(hitButtonBitmap, 200, 200, false), 1530, 840);
        this.useHealingPotion = new GameButton(Bitmap.createScaledBitmap(healingPotionBitmap, 360, 360, false), 0, 790);


        /* Make thread work after surface creation */
        this.gameThread = new GameThread(this, holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }


    /**
     * Actions after surface is changed
     * */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    /**
     * Actions after surface destroyed
     * @params:
     * holder - game surface holder
     * */
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
            if (!paused && !chest.isOpened()) {

                for (Skeleton skeleton: this.skeletons) {
                    skeleton.updateCharacterMovement();
                }

                mainCharacter.updateCharacterMovement();
                characterHealthUpdate();
                characterArmorUpdate();

                chest.setCharsX(mainCharacter.getX());
                chest.setCharsY(mainCharacter.getY());


                if (skeletons.isEmpty()) {
                    numberOfNPC += 1;
                    mobsInit(numberOfNPC);
                }


                if (numberOfNPC % 2 == 0) chest.setHealingPotionCount(1);

                if (mainCharacter.isDead()) gameFinished();

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
     * @params:
     * canvas - game canvas
     * */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        if (!started) {
            this.mainMenu.draw(canvas);
            this.playGameButton.draw(canvas);
        } else {

            canvas.drawBitmap(this.background, 0, 0, null);

            /* Draw tomb on place where skeleton died */
            if (!tombs.isEmpty()) {
                for (Tomb tomb: tombs) {
                    tomb.draw(canvas);
                }
            }

            this.mainCharacter.draw(canvas);
            for (Skeleton skeleton: this.skeletons) {
                skeleton.draw(canvas);
            }


            /* Draw health and armor indicators */
            for (HealthIndicator h : health) {
                h.draw(canvas);
            }

            for (HealthIndicator a : armor) {
                a.draw(canvas);
            }

            if (mainCharacter.getHealingPotionCount() > 0) this.useHealingPotion.draw(canvas);

            this.hitButton.draw(canvas);
            this.chest.draw(canvas);

            /* If main char is dead, GameOver is drawn */
            if (mainCharacter.isDead()) canvas.drawBitmap(Bitmap.createScaledBitmap(gameOver, 1920, 1080, false), 0,0,null);


            /* Draw pause menu subview */
            if (paused) {
                canvas.drawBitmap(pauseBackground, 395, 315, null);
                if (!mainCharacter.isDead()) this.resumeGameButton.draw(canvas);
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


            int x = (int)event.getX();
            int y = (int)event.getY();


            /* Game starts after user touched PLAY button */
            if (!started && playGameButton.isPressed(x, y)) {
                started = true;
                return true;

            } else {

                /* User touched PAUSE button */
                if (gameButton.isPressed(x, y)) {
                    paused = true;
                    Log.v("onTouchEventMethod", "PAUSE button pressed");

                /* User touched HIT button */
                } else if (hitButton.isPressed(x, y)) {

                        /*  */
                    if (!skeletons.isEmpty()) {
                        for (Skeleton skeleton : this.skeletons) {

                            if (Math.abs(this.mainCharacter.getX() - skeleton.getX()) <= 20 &&
                                    Math.abs(this.mainCharacter.getY() - skeleton.getY()) <= 20) {

                                tombs.add(new Tomb(Bitmap.createScaledBitmap(tombBitmap, 100, 110, false),
                                        skeleton.getX(), skeleton.getY()));

                                skeletons.remove(skeleton);
                                Log.v("onTouchEventMethod", "Skeleton killed by user");
                                break;
                            }

                        }
                    }

                    /* Chest interaction */
                } else if (chest.characterOpenedChest(x, y)) {
                    chest.setOpened(true);
                    Log.v("onTouchEventMethod", "User opened chest");

                } else if (chest.characterClosedChest(x, y) && chest.isOpened()) {
                    chest.setOpened(false);
                    Log.v("onTouchEventMethod", "User closed chest");

                } else if (chest.isOpened() && chest.characterTookPotion(x, y)) {
                    chest.setHealingPotionCount(chest.getHealingPotionCount() - 1);
                    mainCharacter.setHealingPotionCount(mainCharacter.getHealingPotionCount() + 1);
                    Log.v("onTouchEventMethod", "User took healing potion");


                } else if (!mainCharacter.isDead() && useHealingPotion.isPressed(x, y) && mainCharacter.getHealingPotionCount() > 0) {
                    mainCharacter.setHealth(mainCharacter.getHealth() + 1);
                    mainCharacter.setHealingPotionCount(mainCharacter.getHealingPotionCount() - 1);
                    Log.v("onTouchEventMethod", "Main character was healed");

                /* Switch to the game regime */
                } else if (paused && resumeGameButton.isPressed(x, y) && !mainCharacter.isDead()) {
                    paused = !paused;

                /* Switch to MENU regime */
                } else if (paused && toMainMenuButton.isPressed(x, y)) {
                    started = false;
                    paused = false;
                    Log.v("onTouchEventMethod", "Went to the main menu");

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



    /* Methods initialize health and armor of character at the
     * beginning of game */
    private void characterHealthUpdate() {

        this.health.clear();
        for (int i = 0; i < this.mainCharacter.getHealth(); i ++) {
            this.health.add(new HealthIndicator(heartBitmap, 50 + i*120, 50));
        }
    }

    private void characterArmorUpdate() {

        this.armor.clear();
        for (int i = 0; i < this.mainCharacter.getArmor(); i++) {
            this.armor.add(new HealthIndicator(Bitmap.createScaledBitmap(armorBitmap, 120, 120, false), 50 + i * 120, 130));
        }
    }


    /* Mob spawn in the game */
    private void mobsInit(int numberOfNPC) {
        for (int i = 0; i < numberOfNPC; i++) {
            this.skeletons.add(new Skeleton(this, skeletonBitmap, 1000 + i*30, 170 + i*43, this.mainCharacter));
        }
    }


    /* Methods handle actions after game starts or finished */
    private void gameStarted() {
        this.mainCharacter = new MainCharacter(this, knightBitmap,400,885);
        skeletons.clear();
        tombs.clear();
        numberOfNPC = 1;
        chest.setHealingPotionCount(1);
        mobsInit(numberOfNPC);
        characterHealthUpdate();
        characterArmorUpdate();
    }

    private void gameFinished() {
        skeletons.clear();
        tombs.clear();
        numberOfNPC = 0;
    }

}
