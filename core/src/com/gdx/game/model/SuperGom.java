package com.gdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.view.TextureFactory;

public class SuperGom extends Gom{
    public SuperGom(Vector2 position, World monde) {
        super(position, monde);
    }

    @Override
    public Texture getTexture() {
        return TextureFactory.getInstance().getTexture(this.getClass());
    }
}
