package com.gdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.gdx.game.controller.utilities.Util;
import com.gdx.game.view.TextureFactory;

public class Pacman extends Entity {
    private final String direction[] = {
            "pacmanUp",
            "pacmanLeft",
            "pacmanDown",
            "pacmanRight"
    };

    private final String directionStep[] = {
            "",
            "-2"
    };

    private int animationStep;
    private String currentAnim;
    private long startTime;

    public int getAnimationStep() {
        return animationStep;
    }

    public String getCurrentAnim() {
        return currentAnim;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setAnimationStep(int animationStep) {
        this.animationStep = animationStep;
    }

    public void setCurrentAnim(String currentAnim) {
        this.currentAnim = currentAnim;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void incAnimationStep()
    {
        this.animationStep ^= 1;
    }

    public String getDirection(int i) {
        return direction[i];
    }

    public String getDirectionStep(int i) {
        return directionStep[i];
    }

    public Pacman(Vector2 position, World world) {
        super(position, world);
        animationStep = 0;
        currentAnim = "pacmanRight";
        startTime = TimeUtils.millis();
    }

    public Texture getTexture() {
        return TextureFactory.getInstance().getTexture(currentAnim);
    }

    public void updateAnimation() {
        long elapsedTime = TimeUtils.timeSinceMillis(startTime);
        if (elapsedTime > 150) {
            startTime = TimeUtils.millis();
            this.animationStep ^= 1;
        }
        currentAnim = this.direction[Util.currentDir] + this.directionStep[animationStep];
    }

    public int getDep()
    {
        return 0;
    }
}