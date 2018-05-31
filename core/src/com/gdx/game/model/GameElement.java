package com.gdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class GameElement {
    protected Vector2 position;
    protected World monde;

    protected GameElement(Vector2 position, World monde) {
        this.position = position;
        this.monde = monde;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public abstract float getWidth();

    public abstract float getHeight();

    public abstract Texture getTexture();

    @Override
    public boolean equals(Object o) {
        return (this.getClass() == o.getClass());
    }
}
