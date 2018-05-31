package com.gdx.game.entities;

import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
    protected Vector2 pos;

    public Entity(Vector2 pos) {
        this.pos = pos;
    }

    public abstract void move();
}
