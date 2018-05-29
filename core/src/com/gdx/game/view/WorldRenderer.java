package com.gdx.game.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.model.GameElement;
import com.gdx.game.model.World;

public class WorldRenderer {
    private SpriteBatch spriteBatch;
    private World monde;
    private int ppuX, ppuY;

    public int getPpuX() {
        return ppuX;
    }

    public void setPpuX(int ppuX) {
        this.ppuX = ppuX;
    }

    public int getPpuY() {
        return ppuY;
    }

    public void setPpuY(int ppuY) {
        this.ppuY = ppuY;
    }

    public WorldRenderer(World monde)
    {
        this.monde = monde;
        this.spriteBatch = new SpriteBatch();
    }

    public void render(float delta)
    {
        this.spriteBatch.begin();
        for (GameElement element : this.monde) {
            this.spriteBatch.draw(
                    TextureFactory.getInstance().getTexture(element.getClass()),
                    element.getPosition().x * ppuX,
                    element.getPosition().y * ppuY,
                    element.getWidth() * ppuX,
                    element.getHeight() * ppuY
            );
        }
        this.spriteBatch.end();
    }
}
