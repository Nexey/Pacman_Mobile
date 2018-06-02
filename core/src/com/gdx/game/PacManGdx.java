package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.gdx.game.controller.controllers.DiagonalDirections;
import com.gdx.game.model.Maze;
import com.gdx.game.model.World;

public class PacManGdx extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture block;
	private Texture pacGomme;
	private Texture pacPower;
	private Texture dark;
	private World world;
	//private Maze laby;
	
	@Override
	public void create () {
        batch = new SpriteBatch();
        block = new Texture("bloc.png");
        pacGomme = new Texture("pellet.png");
        pacPower = new Texture("superpellet.png");
        dark = new Texture("dark.png");
		world = new World();
        //Gdx.input.setInputProcessor(new TouchControl());
		Gdx.input.setInputProcessor(new DiagonalDirections());
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.getMaze().updateMaze(batch, world.getPacman());
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
