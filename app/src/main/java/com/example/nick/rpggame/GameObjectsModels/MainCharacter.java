package com.example.nick.rpggame.GameObjectsModels;

import android.graphics.Bitmap;
import com.example.nick.rpggame.GameSurface;


/**
 * Character the user playing
 * */
public class MainCharacter extends ModelCharacter {

    private int health;
    private int armor;
    private int healingPotionCount = 0;


    /**
     * Main character initialization
     * @params:
     * gameSurface - surface that handles all graphic changes in game
     * image - image of character
     * x, y - coordinates on canvas
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
     * @params:
     * health - number of health points
     * */
    public void setHealth(int health) {
        this.health = health;
    }


    /**
     * Decrease/increase armor state
     * @params:
     * armor - number of armor points
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
     * @params:
     * healingPotionCount - number of potions in chest
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
}
