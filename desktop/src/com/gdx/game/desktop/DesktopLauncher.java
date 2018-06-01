package com.gdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdx.game.PacManGdx;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "PacMan";
		config.width = 28 * 16;
		config.height = 31 * 16;
		config.foregroundFPS = 60;
		new LwjglApplication(new PacManGdx(), config);
	}
}
