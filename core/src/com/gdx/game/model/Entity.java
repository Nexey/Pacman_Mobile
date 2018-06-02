package com.gdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.gdx.game.model.Dark;
import com.gdx.game.model.GameElement;
import com.gdx.game.model.Pacman;
import com.gdx.game.model.World;
import com.gdx.game.controller.utilities.Util;

public abstract class Entity extends GameElement {
    public static final float size=16;
    private GameElement tile;

    public Entity(Vector2 pos, World world) {
        super(pos, world);
        this.tile = null;
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
        this._world.getMaze().set(new Vector2(this.getPosition()), this.retrieveTile());
        switch(dir) {
            case Util.UP:
                if (this._world.getMaze().validTile(new Vector2(this.getPosition().x, this.getPosition().y + 1))) {
                    this.setY((int)this.getPosition().y + 1);
                    this.setCurrentTile();
                    return true;
                }
                break;
            case Util.LEFT:
                if (this._world.getMaze().validTile(new Vector2(this.getPosition().x - 1, this.getPosition().y))) {
                    this.setX((int)this.getPosition().x - 1);
                    this.setCurrentTile();
                    return true;
                }
                break;
            case Util.DOWN:
                if (this._world.getMaze().validTile(new Vector2(this.getPosition().x, this.getPosition().y - 1))) {
                    this.setY((int)this.getPosition().y - 1);
                    this.setCurrentTile();
                    return true;
                }
                break;
            case Util.RIGHT:
                if (this._world.getMaze().validTile(new Vector2(this.getPosition().x + 1, this.getPosition().y))) {
                    this.setX((int)this.getPosition().x + 1);
                    this.setCurrentTile();
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }

    public abstract boolean move();

    protected void setDarkTile() {
        this.tile = new Dark(new Vector2(this.getPosition()), this._world);
    }

    private void setCurrentTile() {
        this.tile = this._world.getMaze().get(new Vector2(this.getPosition()));
    }

    public GameElement retrieveTile() {
        if (this.tile == null) this.setDarkTile();
        return this.tile;
    }
}
