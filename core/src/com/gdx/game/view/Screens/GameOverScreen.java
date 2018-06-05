package com.gdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.gdx.game.PacManGdx;
import com.gdx.game.controller.utilities.Util;

public class GameOverScreen extends BasicScreen {
    private BitmapFont score;

    public GameOverScreen(final PacManGdx game){
        super(game, Gdx.files.internal("retry_button.png"), Gdx.files.internal("quit_button.png"));

        score = new BitmapFont();

        button1.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                Util.SCORE = 0;
                game.gotoMenuScreen();
                return true;
            }
        });

        button2.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                Gdx.app.exit();
                //game.gotoGameOverScreen();
                return false;
            }
        });
        button_settings.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.gotoSettingsScreen();
                return true;
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        game.batch.begin();
        score.draw(game.batch, "Score : "+ Util.SCORE, 185, 200);
        game.batch.end();
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
    public void dispose() {
        super.dispose();
    }
}
