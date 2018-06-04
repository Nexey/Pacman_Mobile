package com.gdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.gdx.game.PacManGdx;

import java.awt.*;

public class GameOverScreen implements Screen {

    private final PacManGdx game;
    private Stage stage;
    private Animation<TextureRegion> animation;
    private float elapsed;
    private Texture Texturebutton_retry, Texturebutton_quit, Texturebutton_settings;
    private TextureRegion TextureRegionButton_retry, TextureRegionButton_quit, TextureRegionButton_settings;
    private TextureRegionDrawable TexRegionDrawableButton_retry, TexRegionDrawableButton_quit, TexRegionDrawableButton_settings;
    private ImageButton button_retry, button_quit, button_settings;

    public GameOverScreen(final PacManGdx game){
        this.game = game;
        stage = new Stage(game.screenPort);
        Gdx.input.setInputProcessor(stage);
        Texturebutton_retry = new Texture(Gdx.files.internal("retry_button.png"));
        Texturebutton_quit = new Texture(Gdx.files.internal("quit_button.png"));
        Texturebutton_settings = new Texture(Gdx.files.internal("settings.png"));
        TextureRegionButton_retry = new TextureRegion(Texturebutton_retry);
        TextureRegionButton_quit = new TextureRegion(Texturebutton_quit);
        TextureRegionButton_settings = new TextureRegion(Texturebutton_settings);
        TexRegionDrawableButton_retry = new TextureRegionDrawable(TextureRegionButton_retry);
        TexRegionDrawableButton_quit = new TextureRegionDrawable(TextureRegionButton_quit);
        TexRegionDrawableButton_settings = new TextureRegionDrawable(TextureRegionButton_settings);
        button_retry = new ImageButton(TexRegionDrawableButton_retry);
        button_quit = new ImageButton(TexRegionDrawableButton_quit);
        button_settings = new ImageButton(TexRegionDrawableButton_settings);
        float screenWidth = Gdx.graphics.getWidth()/10, screenHeight = Gdx.graphics.getHeight()/10;
        button_retry.setPosition( screenWidth,  5*screenHeight);
        button_quit.setPosition( screenWidth*5.75f,  5*screenHeight);
        button_settings.setPosition( (screenWidth*10)-64,  (10*screenHeight)-64);
        stage.addActor(button_retry);
        stage.addActor(button_quit);
        stage.addActor(button_settings);
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("pacman.gif").read());

        button_retry.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.gotoGameScreen();
                return true;
            }
        });

        button_quit.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                Gdx.app.exit();
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
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
        elapsed += Gdx.graphics.getDeltaTime();
        game.batch.begin();
        game.batch.draw(animation.getKeyFrame(elapsed), 45.0f, -60.0f);
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
        stage.dispose();
    }
}

