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

    private long startAnimTime;
    private boolean powerUp;

    private long startPowerUpTime;

    public Pacman(Vector2 position, World world) {
        super(position, world);
        animationStep = 0;
        currentAnim = "pacmanRight";
        startAnimTime = TimeUtils.millis();
        startPowerUpTime = TimeUtils.millis();
        this.powerUp = false;
    }

    public Texture getTexture() {
        return TextureFactory.getInstance().getTexture(currentAnim);
    }


    public boolean getPowerUp() {
        long elapsedTime = TimeUtils.timeSinceMillis(startPowerUpTime);
        if (elapsedTime > 5000)
            this.powerUp = false;
        return this.powerUp;
    }

    public void setPowerUp() {
        this.powerUp = true;
        startPowerUpTime = TimeUtils.millis();
    }

    @Override
    public boolean move() {
        if (updateCoords(Util.currentDir)) {
            // Si c'est une gomme, on la remplace par une case vide et on incrémente le score
            if (this.retrieveTile().equals(Gom.class)) {
                Util.SCORE++;
                this.setDarkTile();
            }
            else if (this.retrieveTile().equals(SuperGom.class)) {
                this.setPowerUp();
                this.setDarkTile();
            }
            return true;
        }
        return false;
    }

    protected boolean updateCoords(int dir) {
        this._world.set(new Vector2(this.getPosition()), this.retrieveTile());
        switch(dir) {
            case Util.UPP:
                if(enHaut()) {
                    this.setCurrentTile();
                    Util.previousDir = Util.currentDir;
                    return true;
                }
                else Util.currentDir = Util.previousDir;
                break;
            case Util.LEFTP:
                if(aGauche()) {
                    this.setCurrentTile();
                    Util.previousDir = Util.currentDir;
                    return true;
                }
                else Util.currentDir = Util.previousDir;
                break;
            case Util.DOWNP:
                if(enBas()) {
                    this.setCurrentTile();
                    Util.previousDir = Util.currentDir;
                    return true;
                }
                else Util.currentDir = Util.previousDir;
                break;
            case Util.RIGHTP:
                if(aDroite()) {
                    this.setCurrentTile();
                    Util.previousDir = Util.currentDir;
                    return true;
                }
                else Util.currentDir = Util.previousDir;
                break;
            default:
                break;
        }
        return false;
    }

    public void updateAnimation() {
        long elapsedTime = TimeUtils.timeSinceMillis(startAnimTime);
        if (elapsedTime > 150) {
            startAnimTime = TimeUtils.millis();
            this.animationStep ^= 1;
        }
        currentAnim = this.direction[Util.currentDir] + this.directionStep[animationStep];
    }
}