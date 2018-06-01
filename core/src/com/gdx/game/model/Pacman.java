package com.gdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.entities.Entity;
import com.gdx.game.utilities.Util;
import com.gdx.game.view.TextureFactory;

public class Pacman extends Entity {

    public Pacman(Vector2 position, World world) {
        super(position, world);
    }

    @Override
    public Texture getTexture() {
        return TextureFactory.getInstance().getTexture("pacman");
    }

    @Override
    public void move() {
        updateCoords(Util.currentDir);
    }
}