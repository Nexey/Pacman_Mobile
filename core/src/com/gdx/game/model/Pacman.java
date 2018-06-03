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

    public Pacman(Vector2 position, World world) {
        super(position, world);
        animationStep = 0;
        currentAnim = "pacmanRight";
        startTime = TimeUtils.millis();
    }

    public Texture getTexture() {
        return TextureFactory.getInstance().getTexture(currentAnim);
    }

    @Override
    public boolean move() {
        if (updateCoords(Util.currentDir)) {
            // Si c'est une gomme, on la remplace par une case vide et on incrémente le score
            if (this.retrieveTile().equals(Gom.class)) {
                Util.SCORE++;
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
                if(enHaut())
                    return true;
                break;
            case Util.LEFTP:
                if(aGauche())
                    return true;
                break;
            case Util.DOWNP:
                if(enBas())
                    return true;
                break;
            case Util.RIGHTP:
                if(aDroite())
                    return true;
                break;
            default:
                break;
        }
        return false;
    }

    public void updateAnimation() {
        long elapsedTime = TimeUtils.timeSinceMillis(startTime);
        if (elapsedTime > 150) {
            startTime = TimeUtils.millis();
            this.animationStep ^= 1;
        }
        currentAnim = this.direction[Util.currentDir] + this.directionStep[animationStep];
    }
}