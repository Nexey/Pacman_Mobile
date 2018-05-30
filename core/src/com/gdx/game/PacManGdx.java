package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.model.Maze;
import com.gdx.game.screens.Triangle;

public class PacManGdx extends ApplicationAdapter {
	SpriteBatch batch;
	Texture block;
	Texture pacGomme;
	Texture pacPower;
	Texture dark;
	Maze laby;
	
	@Override
	public void create () {
        batch = new SpriteBatch();
        block = new Texture("bloc.png");
        pacGomme = new Texture("pellet.png");
        pacPower = new Texture("superpellet.png");
        dark = new Texture("dark.png");
        //laby = new Maze(block, pacGomme, pacPower, dark);
        laby = new Maze();
        Gdx.input.setInputProcessor(new Triangle(new Vector2(0, 0), new Vector2(Gdx.graphics.getWidth() - 1, 0), batch));
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		laby.drawMaze(batch);
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
