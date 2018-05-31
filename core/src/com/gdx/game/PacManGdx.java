package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.controller.TouchControl;
import com.gdx.game.controller.DiagonalDirections;
import com.gdx.game.model.GameElement;
import com.gdx.game.model.Maze;

public class PacManGdx extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture block;
	private Texture pacGomme;
	private Texture pacPower;
	private Texture dark;
	private Maze laby;
	
	@Override
	public void create () {
        batch = new SpriteBatch();
        block = new Texture("bloc.png");
        pacGomme = new Texture("pellet.png");
        pacPower = new Texture("superpellet.png");
        dark = new Texture("dark.png");
		laby = new Maze();

        //Gdx.input.setInputProcessor(new TouchControl());
		Gdx.input.setInputProcessor(new DiagonalDirections());
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		laby.updateMaze(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		block.dispose();
		pacGomme.dispose();
		pacPower.dispose();
		dark.dispose();
	}
}
