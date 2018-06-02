package com.gdx.game.controller.deplacements;

import com.badlogic.gdx.math.Vector2;
import com.gdx.game.controller.utilities.Util;
import com.gdx.game.model.Entity;
import com.gdx.game.model.Pacman;

public abstract class deplacement {
    public static boolean updateCoords(int dir, Entity e) {
        //Vector2 oldPos = new Vector2(e.getPosition());
        switch(dir) {
            case Util.UP:
                if (e.getWorld().getMaze().validTile(new Vector2(e.getPosition().x, e.getPosition().y + 1)))
                {
                    e.setY((int)e.getPosition().y + 1);
                    e.setCurrentTile();
                    return true;
                }
                break;
            case Util.LEFT:
                if (e.getWorld().getMaze().validTile(new Vector2(e.getPosition().x - 1, e.getPosition().y))) {
                    e.setX((int)e.getPosition().x - 1);
                    e.setCurrentTile();
                    return true;
                }
                break;
            case Util.DOWN:
                if (e.getWorld().getMaze().validTile(new Vector2(e.getPosition().x, e.getPosition().y - 1))) {
                    e.setY((int)e.getPosition().y - 1);
                    e.setCurrentTile();
                    return true;
                }
                break;
            case Util.RIGHT:
                if (e.getWorld().getMaze().validTile(new Vector2(e.getPosition().x + 1, e.getPosition().y))) {
                    e.setX((int)e.getPosition().x + 1);
                    e.setCurrentTile();
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }
}
