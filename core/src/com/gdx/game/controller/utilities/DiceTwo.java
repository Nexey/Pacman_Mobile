package com.gdx.game.controller.utilities;
import java.util.Random;

public class DiceTwo{
    public static int getFace() {
        Random rand = new Random();
        return rand.nextInt(2);
    }
}