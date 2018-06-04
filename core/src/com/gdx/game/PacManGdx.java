package com.gdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gdx.game.view.Screens.GameOverScreen;
import com.gdx.game.view.Screens.GameScreen;
import com.gdx.game.view.Screens.MenuScreen;
import com.gdx.game.view.Screens.SettingsScreen;

public class PacManGdx extends Game {

	public SpriteBatch batch;
	public Viewport screenPort;
	public boolean controlMethod;

	@Override
	public void create () {
		batch = new SpriteBatch();
		OrthographicCamera camera = new OrthographicCamera();
		camera.setToOrtho(false);
		screenPort = new ScreenViewport();
		this.setScreen(new MenuScreen(this));
		controlMethod = true;
	}

	public void gotoMenuScreen(){
		MenuScreen menuScreen = new MenuScreen(this);
		setScreen(menuScreen);
	}

	public void gotoSettingsScreen(){
		SettingsScreen settingsScreen = new SettingsScreen(this);
		setScreen(settingsScreen);
	}

	public void gotoGameScreen(){
		GameScreen gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}

	public void gotoGameOverScreen(){
		GameOverScreen gameOverScreen = new GameOverScreen(this);
		setScreen(gameOverScreen);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
	}
}
