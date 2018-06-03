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
    private Texture Texturebutton;
    private TextureRegion TextureRegionButton;
    private TextureRegionDrawable TexRegionDrawableButton;
    private ImageButton button;

    public MenuScreen(final PacManGdx game){
        this.game = game;
        stage = new Stage(game.screenPort);
        Gdx.input.setInputProcessor(stage);
        Texturebutton = new Texture(Gdx.files.internal("button.png"));
        TextureRegionButton = new TextureRegion(Texturebutton);
        TexRegionDrawableButton = new TextureRegionDrawable(TextureRegionButton);
        button = new ImageButton(TexRegionDrawableButton);
        float screenWidth = Gdx.graphics.getWidth(), screenHeight = Gdx.graphics.getHeight();
        button.setPosition( screenWidth/8,  screenHeight- 2*screenHeight/10);
        stage.addActor(button);
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("pacman.gif").read());

        button.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.gotoGameScreen();
                return true;
            }
        });

        /*stage.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.gotoGameScreen();
                return true;
            }
            });*/

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
