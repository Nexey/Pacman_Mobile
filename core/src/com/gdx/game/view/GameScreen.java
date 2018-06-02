package com.gdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.gdx.game.model.World;
import com.gdx.game.view.WorldRenderer;

public class GameScreen implements Screen {
    private World monde;
    private WorldRenderer renderer;

    public GameScreen() {
        monde = new World();
        renderer = new WorldRenderer(monde);
    }

    public World getMonde() {
        return monde;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        this.renderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        this.renderer.setPpuX((int)(width/(float)this.monde.getWidth()));
        this.renderer.setPpuY((int)(height/(float)this.monde.getHeight()));
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
    public void dispose() {

    }
}
