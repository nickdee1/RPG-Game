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

    public MainCharacter(GameSurface gameSurface, Bitmap image, int x, int y) {
        super(gameSurface, image, x, y);

        this.health = 5;
        this.armor = 2;
    }


    public int getHealth() {
        return health;
    }

    public int getArmor() {
        return armor;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getHealingPotionCount() {
        return healingPotionCount;
    }

    public void setHealingPotionCount(int healingPotionCount) {
        this.healingPotionCount = healingPotionCount;
    }

    public boolean isDead() {
        return health < 1;
    }
}
