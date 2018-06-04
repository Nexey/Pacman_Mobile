package com.gdx.game.controller.utilities;

public class Util {
    public static final int
            LEFTP = 0,
            DOWNP = 1,
            RIGHTP = 2,
            UPP = 3,
            LEFTG = 3,
            DOWNG = 0,
            RIGHTG = 1,
            UPG = 2,
            NOWHERE = -1;

    public static int currentDir = NOWHERE;
    public static int previousDir = currentDir;

    public static final int
            PACMAN = 0,
            RED = 1,
            PINK = 2,
            BLUE = 3,
            YELLOW = 4;

    public static int SCORE = 0;

    public static boolean GameOver = false;
}
