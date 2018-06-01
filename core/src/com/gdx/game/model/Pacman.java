package com.gdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.gdx.game.entities.Entity;
import com.gdx.game.utilities.Util;
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
    private long elapsedTime;

    public Pacman(Vector2 position, World world) {
        super(position, world);
        animationStep = 0;
        currentAnim = "pacmanRight";
        startTime = TimeUtils.millis();
    }

    // TODO : Ré-écrire ce getter pour récupérer dynamiquement le sprite selon la direction et la directionStep
    @Override
    public Texture getTexture() {
        return TextureFactory.getInstance().getTexture(currentAnim);
    }

    @Override
    public void move() {
        updateCoords(Util.currentDir);
    }

    public void updateAnimation() {
        elapsedTime = TimeUtils.timeSinceMillis(startTime);
        if (elapsedTime > 1000)
            startTime = TimeUtils.millis();
        this.animationStep ^= 1;
        currentAnim = this.direction[Util.currentDir] + this.directionStep[animationStep];
    }
}