package com.gdx.game.utilities;
import java.util.Random;

public class DiceTwo extends Dice {
    @Override
    public int getFace() {
        Random rand = new Random();
        return rand.nextInt(2);
    }
}