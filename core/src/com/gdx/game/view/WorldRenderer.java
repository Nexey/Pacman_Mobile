package com.gdx.game.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.model.GameElement;
import com.gdx.game.model.World;

public class WorldRenderer {
    private SpriteBatch batch;
    private World world;
    private BitmapFont score;
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

    public WorldRenderer()
    {
        this.batch = new SpriteBatch();
        world = new World();
        score = new BitmapFont();
        score.setColor(Color.YELLOW);
    }

    public void render(float delta)
    {
        world.getMaze().updateMaze(batch, score);
        /*this.batch.begin();
        for (GameElement element : this.world) {
            this.batch.draw(
                    TextureFactory.getInstance().getTexture(element.getClass()),
                    element.getPosition().x * ppuX,
                    element.getPosition().y * ppuY,
                    element.getWidth() * ppuX,
                    element.getHeight() * ppuY
            );
        }
        this.batch.end();*/
    }

    public void dispose () {
        batch.dispose();
    }
}
