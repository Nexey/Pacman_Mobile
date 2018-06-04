package com.gdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.gdx.game.PacManGdx;
import com.gdx.game.controller.controllers.DiagonalDirections;
import com.gdx.game.controller.controllers.TouchControl;
import com.gdx.game.controller.utilities.Util;
import com.gdx.game.view.WorldRenderer;

public class GameScreen implements Screen
{
    private final PacManGdx game;

    private WorldRenderer worldRenderer;

    public GameScreen(final PacManGdx game)
    {
        this.game = game;
        worldRenderer = new WorldRenderer();
        /*batch = new SpriteBatch();
        world = new World();
        score = new BitmapFont();
        score.setColor(Color.YELLOW);*/
        if (game.controlMethod)
            Gdx.input.setInputProcessor(new DiagonalDirections());
        else
            Gdx.input.setInputProcessor(new TouchControl());
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        worldRenderer.render(delta);
        if(Util.GameOver)
        {
            Util.GameOver = false;
            worldRenderer.world.resetDirections();
            game.gotoGameOverScreen();
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        game.screenPort.update(width,height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose () {
        worldRenderer.dispose();
    }
}
