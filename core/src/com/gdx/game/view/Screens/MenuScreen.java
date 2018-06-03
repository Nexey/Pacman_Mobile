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

public class MenuScreen implements Screen {

    final PacManGdx game;
    private Stage stage;
    Animation<TextureRegion> animation;
    float elapsed;
    private Texture Texturebutton_play, Texturebutton_quit, Texturebutton_settings;
    private TextureRegion TextureRegionButton_play, TextureRegionButton_quit, TextureRegionButton_settings;
    private TextureRegionDrawable TexRegionDrawableButton_play, TexRegionDrawableButton_quit, TexRegionDrawableButton_settings;
    private ImageButton button_play, button_quit, button_settings;
    private Dialog dialogWindow;
    private boolean settings = false;
    private BitmapFont dialogfont;

    public MenuScreen(final PacManGdx game){
        this.game = game;
        stage = new Stage(game.screenPort);
        Gdx.input.setInputProcessor(stage);
        dialogfont = new BitmapFont();
        dialogWindow = new Dialog("Settings", new Window.WindowStyle(dialogfont, Color.WHITE, new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("button.png"))))));
        stage.addActor(dialogWindow);
        Texturebutton_play = new Texture(Gdx.files.internal("play_button.png"));
        Texturebutton_quit = new Texture(Gdx.files.internal("quit_button.png"));
        Texturebutton_settings = new Texture(Gdx.files.internal("settings.png"));
        TextureRegionButton_play = new TextureRegion(Texturebutton_play);
        TextureRegionButton_quit = new TextureRegion(Texturebutton_quit);
        TextureRegionButton_settings = new TextureRegion(Texturebutton_settings);
        TexRegionDrawableButton_play = new TextureRegionDrawable(TextureRegionButton_play);
        TexRegionDrawableButton_quit = new TextureRegionDrawable(TextureRegionButton_quit);
        TexRegionDrawableButton_settings = new TextureRegionDrawable(TextureRegionButton_settings);
        button_play = new ImageButton(TexRegionDrawableButton_play);
        button_quit = new ImageButton(TexRegionDrawableButton_quit);
        button_settings = new ImageButton(TexRegionDrawableButton_settings);
        float screenWidth = Gdx.graphics.getWidth()/10, screenHeight = Gdx.graphics.getHeight()/10;
        button_play.setPosition( screenWidth,  5*screenHeight);
        button_quit.setPosition( screenWidth*5.75f,  5*screenHeight);
        button_settings.setPosition( (screenWidth*10)-64,  (10*screenHeight)-64);
        stage.addActor(button_play);
        stage.addActor(button_quit);
        stage.addActor(button_settings);
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("pacman.gif").read());

        button_play.addListener(new InputListener(){
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
                settings = true;
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
        if(settings)
            dialogWindow.getStage().draw();

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
