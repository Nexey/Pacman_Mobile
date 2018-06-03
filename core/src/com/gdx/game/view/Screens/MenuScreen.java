package com.gdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.gdx.game.PacManGdx;

public class MenuScreen implements Screen {

    final PacManGdx game;
    private Stage stage;
    Animation<TextureRegion> animation;
    float elapsed;
    private Texture Texturebutton_play, Texturebutton_quit;
    private TextureRegion TextureRegionButton_play, TextureRegionButton_quit;
    private TextureRegionDrawable TexRegionDrawableButton_play, TexRegionDrawableButton_quit;
    private ImageButton button_play, button_quit;

    public MenuScreen(final PacManGdx game){
        this.game = game;
        stage = new Stage(game.screenPort);
        Gdx.input.setInputProcessor(stage);
        Texturebutton_play = new Texture(Gdx.files.internal("play_button.png"));
        Texturebutton_quit = new Texture(Gdx.files.internal("quit_button.png"));
        TextureRegionButton_play = new TextureRegion(Texturebutton_play);
        TextureRegionButton_quit = new TextureRegion(Texturebutton_quit);
        TexRegionDrawableButton_play = new TextureRegionDrawable(TextureRegionButton_play);
        TexRegionDrawableButton_quit = new TextureRegionDrawable(TextureRegionButton_quit);
        button_play = new ImageButton(TexRegionDrawableButton_play);
        button_quit = new ImageButton(TexRegionDrawableButton_quit);
        float screenWidth = Gdx.graphics.getWidth(), screenHeight = Gdx.graphics.getHeight();
        button_play.setPosition( screenWidth/3,  screenHeight- screenHeight/5);
        button_quit.setPosition( screenWidth/3,  screenHeight/2);
        stage.addActor(button_play);
        stage.addActor(button_quit);
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
