package com.gdx.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.gdx.game.model.Dark;
import com.gdx.game.model.GameElement;
import com.gdx.game.model.Pacman;
import com.gdx.game.model.World;
import com.gdx.game.utilities.Util;

public abstract class Entity extends GameElement {
    public static final float size=16;
    private GameElement tile;

    public Entity(Vector2 pos, World world) {
        super(pos, world);
    }

    @Override
    public float getWidth() {
        return size;
    }

    @Override
    public float getHeight() {
        return size;
    }

    protected boolean updateCoords(int dir) {
        //Vector2 oldPos = new Vector2(this.getPosition());
        switch(dir) {
            case Util.UP:
                if (this._world.getMaze().validTile(new Vector2(this.getPosition().x, this.getPosition().y + 1)))
                {
                    this.getPosition().y++;
                    this.setCurrentTile();
                    return true;
                }
            case Util.LEFT:
                if (this._world.getMaze().validTile(new Vector2(this.getPosition().x - 1, this.getPosition().y))) {
                    this.getPosition().x--;
                    this.setCurrentTile();
                    return true;
                }
            case Util.DOWN:
                if (this._world.getMaze().validTile(new Vector2(this.getPosition().x, this.getPosition().y - 1))) {
                    this.getPosition().y--;
                    this.setCurrentTile();
                    return true;
                }
            case Util.RIGHT:
                if (this._world.getMaze().validTile(new Vector2(this.getPosition().x + 1, this.getPosition().y))) {
                    this.getPosition().x++;
                    this.setCurrentTile();
                    return true;
                }
            default:
                return false;
        }
        // On a détecté un changement de getPosition(). On récupère alors la case sur laquelle on est, car on va l'écraser juste après
        // avec l'entity
        //if (oldPos != this.getPosition()) this.setCurrentTile();
    }

    public abstract boolean move(Pacman pacman);

    public void setDarkTile() {
        this.tile = new Dark(new Vector2(this.getPosition()), this._world);
    }

    public void setCurrentTile() {
        this.tile = this._world.getMaze().get(this.getPosition());
    }

    public GameElement retrieveTile() {
        return this.tile;
    }
}
