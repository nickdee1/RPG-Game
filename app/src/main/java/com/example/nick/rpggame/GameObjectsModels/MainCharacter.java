package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import android.util.Log;
import com.example.nick.rpggame.GameSurface;

import java.util.ArrayList;
import java.util.List;


/**
 * Character the user playing
 * */
public class MainCharacter extends ModelCharacter {

    private int health;
    private int armor;
    private int healingPotionCount = 0;
    private Key yellowKey = null;
    private Key blueKey = null;

    /**
     * Main character initialization
     * @param gameSurface - surface that handles all graphic changes in game
     * @param  image - image of character
     * @param  x, y - coordinates on canvas
     * */
    public MainCharacter(GameSurface gameSurface, Bitmap image, int x, int y) {
        super(gameSurface, image, x, y);

        this.health = 5;
        this.armor = 2;
    }


    /**
     * Get current health state
     * */
    public int getHealth() {
        return health;
    }

    /**
     * Gets current armor state
     * */
    public int getArmor() {
        return armor;
    }


    /**
     * Decrease/increase health level
     * @param health - number of health points
     * */
    public void setHealth(int health) {
        this.health = health;
    }


    /**
     * Decrease/increase armor state
     * @param armor - number of armor points
     * */
    public void setArmor(int armor) {
        this.armor = armor;
    }

    /**
     * Get an amount of healing potions character has
     * */
    public int getHealingPotionCount() {
        return healingPotionCount;
    }

    /**
     * Decrease/increase number of healing potions
     * @param healingPotionCount - number of potions in chest
     * */
    public void setHealingPotionCount(int healingPotionCount) {
        this.healingPotionCount = healingPotionCount;
    }

    /**
     * Checks if character dead
     * */
    public boolean isDead() {
        return health < 1;
    }


    /**
     * @return returns yellow key
     */
    public Key getYellowKey() {
        return yellowKey;
    }


    /**
     * @return returns blue key
     */
    public Key getBlueKey() {
        return blueKey;
    }


    /**
     * @param yellowKey - key to be set
     */
    public void setYellowKey(Key yellowKey) {
        this.yellowKey = yellowKey;
    }


    /**
     * @param blueKey - key to be set
     */
    public void setBlueKey(Key blueKey) {
        this.blueKey = blueKey;
    }
}
