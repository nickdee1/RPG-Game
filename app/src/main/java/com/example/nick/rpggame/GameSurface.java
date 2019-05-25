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
    private int level = 1;


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
    private List<RedSkeleton> redSkeletons = new ArrayList<>();
    private List<Skeleton> skeletons = new ArrayList<>();
    private List<Tomb> tombs = new ArrayList<>();
    private GreenSkeleton greenSkeleton;
    private Chest chest;
    private Fireball fireball;
    private Door doorToSecondLevel;
    private Door doorToThirdLevel;
    private Key yellowKey;
    private Key blueKey;

    /* Game indicators */
    private List<HealthIndicator> health = new ArrayList<>();
    private List<HealthIndicator> armor = new ArrayList<>();

    /* Game images */
    private Bitmap background;
    private Bitmap pauseBackground;
    private Bitmap secondLevelBackground;
    private Bitmap thirdLevelBackground;
    private Bitmap heartBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.heart_1);
    private Bitmap armorBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.armor_shield);
    private Bitmap gameOver = BitmapFactory.decodeResource(this.getResources(), R.drawable.game_over);
    private Bitmap skeletonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.skeleton);
    private Bitmap redSkeletonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.skeleton_red);
    private Bitmap greenSkeletonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.skeleton_big_green);
    private Bitmap knightBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.knight);
    private Bitmap tombBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.tomb);
    private Bitmap healingPotionBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.healing_potion);
    private Bitmap fireballBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.fireball);
    private Bitmap keyYellowBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.key);
    private Bitmap keyBlueBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.key_blue);

    /* Game switchers */
    private boolean paused = false;
    private boolean started = false;
    private int numberOfRedSkeletons = 1;
    private int numberOfWhiteSkeletons = 1;


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
        Bitmap pauseBackgroundBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.pause_backgound);
        Bitmap pauseButtonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.pause_button);
        Bitmap resumeGameButtonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.resume_button);
        Bitmap toMainMenuButtonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.back_to_menu_button);
        Bitmap hitButtonBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.hit_button);

        /* Game subviews and backgrounds initialization */
        this.mainMenu = new MainMenu(Bitmap.createScaledBitmap(mainMenuBitmap, 1900, 1080, false));
        this.chest = new Chest(chest, chestView, closeButtonBitmap, healingPotionBitmap,700, 300);
        this.background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.background), 1900, 1080, false);
        this.pauseBackground = Bitmap.createScaledBitmap(pauseBackgroundBitmap, 1130, 450, false);


        /* Main character initialization */
        this.mainCharacter = new MainCharacter(this, knightBitmap,400,885);
        this.fireball = new Fireball(Bitmap.createScaledBitmap(this.fireballBitmap, 50, 50, false), 600, 900);

        /* Game buttons initialization */
        this.gameButton = new GameButton(Bitmap.createScaledBitmap(pauseButtonBitmap, 150, 150, false), 1620, 10);
        this.playGameButton = new GameButton(Bitmap.createScaledBitmap(playButtonBitmap, 650, 200, false), 625, 445);
        this.resumeGameButton = new GameButton(Bitmap.createScaledBitmap(resumeGameButtonBitmap, 386, 116, false), 750, 450);
        this.toMainMenuButton = new GameButton(Bitmap.createScaledBitmap(toMainMenuButtonBitmap, 386, 116, false), 750, 590);
        this.hitButton = new GameButton(Bitmap.createScaledBitmap(hitButtonBitmap, 200, 200, false), 1530, 840);
        this.useHealingPotion = new GameButton(Bitmap.createScaledBitmap(healingPotionBitmap, 360, 360, false), 0, 790);


        this.greenSkeleton = new GreenSkeleton(this, greenSkeletonBitmap, 600, 600, this.mainCharacter);

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

                for (Skeleton skeleton: this.redSkeletons) {
                    skeleton.updateCharacterMovement();
                }

                for (Skeleton skeleton: this.skeletons) {
                    skeleton.updateCharacterMovement();
                }

                greenSkeleton.updateCharacterMovement();

                mainCharacter.updateCharacterMovement();
                fireball.updateFireballMovement();
                characterHealthUpdate();
                characterArmorUpdate();

                chest.setCharsX(mainCharacter.getX());
                chest.setCharsY(mainCharacter.getY());

                doorToSecondLevel.setCharsX(mainCharacter.getX());
                doorToSecondLevel.setCharsY(mainCharacter.getY());

                doorToThirdLevel.setCharsX(mainCharacter.getX());
                doorToThirdLevel.setCharsY(mainCharacter.getY());

                if (this.skeletons.isEmpty() && numberOfWhiteSkeletons < 3) {
                    numberOfWhiteSkeletons += 1;
                    mobsInit(numberOfWhiteSkeletons, 1);
                }


                if (this.redSkeletons.isEmpty() && numberOfRedSkeletons < 3) {
                    numberOfRedSkeletons += 1;
                    mobsInit(numberOfRedSkeletons, 2);
                }


                if (numberOfRedSkeletons % 2 == 0) chest.setHealingPotionCount(1);

                if (mainCharacter.isDead()) {
                    gameFinished();
                    level = 1;
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

            if (level == 1)  {
                canvas.drawBitmap(this.background, 0, 0, null);

                /* Draw tomb on place where skeleton died */
                if (!tombs.isEmpty()) {
                    for (Tomb tomb: tombs) {
                        tomb.draw(canvas);
                    }
                }
                this.chest.draw(canvas);

                this.doorToSecondLevel.draw(canvas);
                this.doorToThirdLevel.draw(canvas);

                for (Skeleton skeleton: this.skeletons) {
                    skeleton.draw(canvas);
                }


                /* Yellow key interaction */
                if (this.skeletons.isEmpty() && numberOfWhiteSkeletons > 0 && mainCharacter.getYellowKey() == null) this.yellowKey.draw(canvas);
                if (Math.abs(this.mainCharacter.getX() - this.yellowKey.getX()) < 25 && Math.abs(this.mainCharacter.getY() - this.yellowKey.getY()) < 25
                        && this.mainCharacter.getYellowKey() == null) this.mainCharacter.setYellowKey(this.yellowKey);


            } else if (level == 2){

                canvas.drawBitmap(this.secondLevelBackground, 0, 0,null);

                this.doorToSecondLevel.draw(canvas);

                for (RedSkeleton skeleton: this.redSkeletons) {
                    skeleton.draw(canvas);
                }

                if (this.redSkeletons.isEmpty() && numberOfRedSkeletons > 0 && mainCharacter.getBlueKey() == null) this.blueKey.draw(canvas);
                if (Math.abs(this.mainCharacter.getX() - this.blueKey.getX()) < 10 && Math.abs(this.mainCharacter.getY() - this.blueKey.getY()) < 10
                        && this.mainCharacter.getBlueKey() == null) this.mainCharacter.setBlueKey(this.blueKey);

            } else {
                canvas.drawBitmap(this.thirdLevelBackground, 0, 0, null);
                this.doorToThirdLevel.draw(canvas);
                if (greenSkeleton.getHealth() > 0) greenSkeleton.draw(canvas);
            }


            this.mainCharacter.draw(canvas);


            /* Draw health and armor indicators */
            for (HealthIndicator h : health) {
                h.draw(canvas);
            }

            for (HealthIndicator a : armor) {
                a.draw(canvas);
            }

            if (mainCharacter.getHealingPotionCount() > 0) this.useHealingPotion.draw(canvas);

            this.hitButton.draw(canvas);

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
                        killSkeleton();

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


                 /* Doors interaction */
                } else if (doorToSecondLevel.playerOpenedDoor(x, y)) {
                    level = (level == 1) ? 2 : 1;
                    firstLevelFinished();
                    secondLevelLoaded();

                } else if (doorToThirdLevel.playerOpenedDoor(x, y)) {
                    level = (level == 1) ? 3 : 1;
                    secondLevelFinished();
                    thirdLevelLoaded();

                /* Switch to the game regime */
                } else if (paused && resumeGameButton.isPressed(x, y) && !mainCharacter.isDead()) {
                    paused = !paused;

                /* Switch to MENU regime */
                } else if (paused && toMainMenuButton.isPressed(x, y)) {
                    started = false;
                    paused = false;
                    level = 1;
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
    private void mobsInit(int numberOfNPC, int whichLevel) {

        if (whichLevel == 1) {
            for (int i = 0; i < numberOfNPC; i++) {
                this.skeletons.add(new Skeleton(this, skeletonBitmap, 1000 + i*150, 170 + i*100, this.mainCharacter));
                Log.d("MOBS:", "mobsInit: " + this.skeletons.size());
            }
        } else if (whichLevel == 2) {
            for (int i = 0; i < numberOfNPC; i++) {
                this.redSkeletons.add(new RedSkeleton(this, redSkeletonBitmap, 1000 + i*30, 170 + i*43, this.mainCharacter, Bitmap.createScaledBitmap(this.fireballBitmap, 50, 50, false)));
            }
        }
    }

    /* User pressed HIT button near to skeleton */
    private void killSkeleton() {
        if (!redSkeletons.isEmpty()) {
            for (RedSkeleton skeleton : this.redSkeletons) {

                if (Math.abs(this.mainCharacter.getX() - skeleton.getX()) <= 20 &&
                        Math.abs(this.mainCharacter.getY() - skeleton.getY()) <= 20) {

                    tombs.add(new Tomb(Bitmap.createScaledBitmap(tombBitmap, 100, 110, false),
                            skeleton.getX(), skeleton.getY()));

                    redSkeletons.remove(skeleton);
                    Log.v("onTouchEventMethod", "Skeleton killed by user");
                    break;
                }
            }
        }
        if (!skeletons.isEmpty()) {
            for (Skeleton whiteSkeleton : this.skeletons) {

                if (Math.abs(this.mainCharacter.getX() - whiteSkeleton.getX()) <= 20 &&
                        Math.abs(this.mainCharacter.getY() - whiteSkeleton.getY()) <= 20) {

                    tombs.add(new Tomb(Bitmap.createScaledBitmap(tombBitmap, 100, 110, false),
                            whiteSkeleton.getX(), whiteSkeleton.getY()));

                    skeletons.remove(whiteSkeleton);
                    Log.v("onTouchEventMethod", "Skeleton killed by user");
                    break;
                }
            }
        }

        if (greenSkeleton.getHealth() > 0 && Math.abs(this.mainCharacter.getX() - greenSkeleton.getX()) <= 20 &&
                Math.abs(this.mainCharacter.getY() - greenSkeleton.getY()) <= 20) {
            greenSkeleton.setHealth(greenSkeleton.getHealth() - 1);
        }
    }


    /* Methods handle actions after game starts or finishes */
    private void gameStarted() {
        this.mainCharacter = new MainCharacter(this, knightBitmap,400,885);
        this.yellowKey = new Key(Bitmap.createScaledBitmap(keyYellowBitmap, 100, 50, false), 700,400, "YellowKey");
        this.blueKey = new Key(Bitmap.createScaledBitmap(keyBlueBitmap, 100, 50, false), 700,600, "BlueKey");
        this.doorToSecondLevel = new Door(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.door), 100, 150, false), 1650, 300, this.mainCharacter, this.yellowKey);
        this.doorToThirdLevel = new Door(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.door), 100, 150, false), 1650, 650, this.mainCharacter, this.blueKey);
        redSkeletons.clear();
        skeletons.clear();
        tombs.clear();
        numberOfRedSkeletons = 1;
        chest.setHealingPotionCount(1);
        mobsInit(numberOfRedSkeletons, level);
        characterHealthUpdate();
        characterArmorUpdate();
    }

    private void gameFinished() {
        redSkeletons.clear();
        skeletons.clear();
        tombs.clear();
        numberOfRedSkeletons = 0;
        numberOfWhiteSkeletons = 0;
    }

    private void firstLevelLoaded() {
        gameStarted();
        this.background = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.background), 1900, 1080, false);
    }

    private void firstLevelFinished() {
        gameFinished();
    }

    private void secondLevelLoaded() {
        secondLevelBackground = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.level_2), 1920, 1080, false);
    }

    private void secondLevelFinished() {
        gameFinished();
    }

    private void thirdLevelLoaded() {
        thirdLevelBackground = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.level_3), 1920, 1080, false);
        this.greenSkeleton = new GreenSkeleton(this, greenSkeletonBitmap, 200, 200, this.mainCharacter);

    }
}
