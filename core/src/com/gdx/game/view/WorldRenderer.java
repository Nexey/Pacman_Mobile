package com.gdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.controller.utilities.Util;
import com.gdx.game.model.Entity;
import com.gdx.game.model.Fence;
import com.gdx.game.model.GameElement;
import com.gdx.game.model.World;

public class WorldRenderer {
    private SpriteBatch batch;
    private World world;
    private BitmapFont score;
    private int ppuX, ppuY;
    private final int _textWidth = 16;
    private final int _textHeight = 16;
    private GlyphLayout layout;

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
        layout = new GlyphLayout();
        this.batch = new SpriteBatch();
        world = new World();
        score = new BitmapFont();
        score.setColor(Color.YELLOW);
    }


    /*
    private void updateMaze(SpriteBatch batch, BitmapFont score) {
        if (!world.getMaze().placeBarrier) {
            world.getMaze().placeBarrier = this.world.get_yellow().isSorti();// && this._world.get_blue().isSorti() && this._world.get_pink().isSorti() && this._world.get_red().isSorti();
            if (world.getMaze().placeBarrier) {
                world.getMaze()._laby2[12][14] = new Fence(new Vector2(12, 14), this.world);
                world.getMaze()._laby2[13][14] = new Fence(new Vector2(13, 14), this.world);
            }
        }
        if (Util.currentDir != Util.NOWHERE) {
            this.world.getPacman().updateAnimation();
            // Après cet appel, les tiles des Entity seront mises à jour
            this.world.moveEntities();

            // Il faut redessiner les entitiés en les replaçant sur le labyrinthe
            for (Entity E: this.world.listEntity)
                world.getMaze()._laby2[(int)E.getPosition().x][(int)E.getPosition().y] = E;
        }
        this.drawMaze(batch);
        this.drawPacman(batch, score);
    }

    private void drawPacman(SpriteBatch batch, BitmapFont score) {
        batch.begin();
        batch.draw(this.world.getPacman().getTexture(), this.world.getPacman().getPosition().y * 16, (30 - this.world.getPacman().getPosition().x) * 16);
        String scoreStr = "SCORE : " + Util.SCORE;
        world.getMaze().layout.setText(score.newFontCache().getFont(), scoreStr);
        score.draw(batch, scoreStr, Gdx.graphics.getWidth() - world.getMaze().layout.width, Gdx.graphics.getHeight() - world.getMaze().layout.height);
        batch.end();
    }

    private void drawMaze(SpriteBatch batch) {
        for (Texture text : world.getMaze().listText) {
            if ((text.getWidth() != world.getMaze()._textWidth) || (text.getHeight() != world.getMaze()._textHeight))
                drawResize(batch, text);
            else draw(batch, text);
        }
    }

    private void draw(SpriteBatch batch, Texture text) {
        batch.begin();
        for (int i = 0; i < 28; i++)
            for (int j = 0; j < 31; j++)
                if (world.getMaze()._laby2[j][i].getTexture() == text)
                    batch.draw(
                            text,
                            i * 16,
                            (30 - j) * 16
                    );
        batch.end();
    }

    private void drawResize(SpriteBatch batch, Texture text) {
        batch.begin();
        for (int i = 0; i < 28; i++)
            for (int j = 0; j < 31; j++)
                if (world.getMaze()._laby2[j][i].getTexture() == text)
                    batch.draw(
                            text,
                            i * 16,
                            (30 - j) * 16,
                            world.getMaze()._textWidth,
                            world.getMaze()._textHeight,
                            0,
                            0,
                            text.getWidth(),
                            text.getHeight(),
                            false,
                            false
                    );
        batch.end();
    }
    */



    public void updateMaze(SpriteBatch batch, BitmapFont score) {
        if (Util.currentDir != Util.NOWHERE) {
            this.world.getPacman().updateAnimation();

            this.world.moveEntities();

            for (Entity E : this.world.listMovingEntities) {
                E.alpha += E.velocity;
                if (E.alpha >= 1.f) {
                    E.alpha = 1;
                    // Il faut redessiner les entitiés en les replaçant sur le labyrinthe
                    this.world.set(new Vector2(E.newPosition), E);

                    // Je mets à jour leurs positions
                    E.setPosition(new Vector2(E.newPosition));
                } else {
                    // Vers la droite
                    if (E.getPosition().x < E.newPosition.x) {
                        E.setX(E.getPosition().x + E.velocity);
                    }
                    // Vers la gauche
                    else if (E.getPosition().x > E.newPosition.x) {
                        E.setX(E.getPosition().x - E.velocity);
                    }
                    // Vers le haut
                    else if (E.getPosition().y < E.newPosition.y) {
                        E.setY(E.getPosition().y + E.velocity);
                    }
                    // Vers le bas
                    else if (E.getPosition().y > E.newPosition.y) {
                        E.setY(E.getPosition().y - E.velocity);
                    }
                }
            }
        }
        this.drawMaze(batch, score);
        this.drawEntities(batch, score);
    }

    private void drawEntities(SpriteBatch batch, BitmapFont score) {
        batch.begin();
        for (Entity E: this.world.listEntity)
            batch.draw(E.getTexture(), E.getPosition().y * 16, (30 - E.getPosition().x) * 16);
        batch.end();
        this.drawScore(batch, score);
    }

    private void drawScore(SpriteBatch batch, BitmapFont score) {
        batch.begin();
        String scoreStr = "SCORE : " + Util.SCORE;
        layout.setText(score.newFontCache().getFont(), scoreStr);
        score.draw(batch, scoreStr, Gdx.graphics.getWidth() - layout.width, Gdx.graphics.getHeight() - layout.height);
        batch.end();
    }

    private void drawMaze(SpriteBatch batch, BitmapFont score) {
        for (Texture text : this.world.getMaze().listText) {
            if ((text.getWidth() != _textWidth) || (text.getHeight() != _textHeight))
                drawResize(batch, text);
            else draw(batch, text);
        }
    }

    private void draw(SpriteBatch batch, Texture text) {
        batch.begin();
        for (int i = 0; i < 28; i++)
            for (int j = 0; j < 31; j++)
                if (this.world.getMaze()._laby2[j][i].getTexture() == text)
                    batch.draw(
                            text,
                            i * 16,
                            (30 - j) * 16
                    );
        batch.end();
    }

    private void drawResize(SpriteBatch batch, Texture text) {
        batch.begin();
        for (int i = 0; i < 28; i++)
            for (int j = 0; j < 31; j++)
                if (this.world.getMaze()._laby2[j][i].getTexture() == text)
                    batch.draw(
                            text,
                            i * 16,
                            (30 - j) * 16,
                            _textWidth,
                            _textHeight,
                            0,
                            0,
                            text.getWidth(),
                            text.getHeight(),
                            false,
                            false
                    );
        batch.end();
    }




    public void render(float delta)
    {
        updateMaze(batch, score);
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
