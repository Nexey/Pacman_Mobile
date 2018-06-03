package com.gdx.game.view.Screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gdx.game.PacManGdx;
import com.gdx.game.controller.controllers.DiagonalDirections;
import com.gdx.game.model.World;

public class GameScreen implements Screen
{
    final PacManGdx game;
    private Stage stage;

    private SpriteBatch batch;
    private World world;
    private BitmapFont score;

    public GameScreen(final PacManGdx game)
    {
        this.game = game;
        //stage = new Stage(game.screenPort);

        //Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        world = new World();
        score = new BitmapFont();
        score.setColor(Color.YELLOW);
        //Gdx.input.setInputProcessor(new TouchControl());
        Gdx.input.setInputProcessor(new DiagonalDirections());
    }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.getMaze().updateMaze(batch, score);
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
        batch.dispose();
    }
}