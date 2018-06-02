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
    }

    @Override
    public float getWidth() {
        return size;
    }

    @Override
    public float getHeight() {
        return size;
    }

    //public abstract boolean move(Pacman pacman);

    //public abstract boolean move();

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
