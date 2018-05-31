package com.gdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.controller.TouchControl;
import com.gdx.game.controller.DiagonalDirections;
import com.gdx.game.entities.PacmanEntity;
import com.gdx.game.model.GameElement;
import com.gdx.game.model.Maze;
import com.gdx.game.utilities.Util;

public class PacManGdx extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture block;
	private Texture pacGomme;
	private Texture pacPower;
	private Texture dark;
	private PacmanEntity pacman;
	
	@Override
	public void create () {
		//pacman = new PacmanEntity(Vector2);
        batch = new SpriteBatch();
        block = new Texture("bloc.png");
        pacGomme = new Texture("pellet.png");
        pacPower = new Texture("superpellet.png");
        dark = new Texture("dark.png");
        //laby = new Maze(block, pacGomme, pacPower, dark);
		Util.laby = new Maze();

        Gdx.input.setInputProcessor(new TouchControl());
		Gdx.input.setInputProcessor(new DiagonalDirections());


        for (GameElement E:Util.laby) System.out.println(E);
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Util.laby.drawMaze(batch);
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
