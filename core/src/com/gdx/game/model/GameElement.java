package com.gdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class GameElement {
    private Vector2 position;
    protected World _world;

    protected GameElement(Vector2 position, World world) {
        this.position = position;
        this._world = world;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setX(int x) {
        this.position.x = x;
    }

    public void setY(int y) {
        this.position.y = y;
    }

    public abstract float getWidth();

    public abstract float getHeight();

    public abstract Texture getTexture();

    @Override
    public boolean equals(Object o) {
        return (this.getClass() == o.getClass());
    }

    public boolean equals(Class c)
    {
        return (this.getClass() == c);
    }
}
