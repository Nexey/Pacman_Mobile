package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.controller.controllers.DiagonalDirections;
import com.gdx.game.model.World;

public class PacManGdx extends ApplicationAdapter {
	private SpriteBatch batch;
	private World world;
	private BitmapFont score;

	@Override
	public void create () {
        batch = new SpriteBatch();
		world = new World();
		score = new BitmapFont();
		score.setColor(Color.YELLOW);
        //Gdx.input.setInputProcessor(new TouchControl());
		Gdx.input.setInputProcessor(new DiagonalDirections());
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.getMaze().updateMaze(batch, world.getPacman(), score);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
