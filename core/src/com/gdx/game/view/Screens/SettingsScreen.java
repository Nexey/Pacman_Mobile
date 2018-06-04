package com.gdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.gdx.game.PacManGdx;
import com.gdx.game.controller.utilities.Util;

public class SettingsScreen implements Screen
{
    private final PacManGdx game;
    private Stage stage;
    private Animation<TextureRegion> animation;
    private float elapsed;
    private BitmapFont MethodUsed;
    private Texture Texturebutton_touch, Texturebutton_slide, Texturebutton_settings;
    private TextureRegion TextureRegionButton_touch, TextureRegionButton_slide, TextureRegionButton_settings;
    private TextureRegionDrawable TexRegionDrawableButton_touch, TexRegionDrawableButton_slide, TexRegionDrawableButton_settings;
    private ImageButton button_touch, button_slide, button_settings;
    private GlyphLayout layout;
    private String method, effective;

    public SettingsScreen(final PacManGdx game){
        this.game = game;
        stage = new Stage(game.screenPort);
        Gdx.input.setInputProcessor(stage);
        Texturebutton_touch = new Texture(Gdx.files.internal("touch_button.png"));
        Texturebutton_slide = new Texture(Gdx.files.internal("slide_button.png"));
        Texturebutton_settings = new Texture(Gdx.files.internal("settings.png"));
        TextureRegionButton_touch = new TextureRegion(Texturebutton_touch);
        TextureRegionButton_slide = new TextureRegion(Texturebutton_slide);
        TextureRegionButton_settings = new TextureRegion(Texturebutton_settings);
        TexRegionDrawableButton_touch = new TextureRegionDrawable(TextureRegionButton_touch);
        TexRegionDrawableButton_slide = new TextureRegionDrawable(TextureRegionButton_slide);
        TexRegionDrawableButton_settings = new TextureRegionDrawable(TextureRegionButton_settings);
        button_touch = new ImageButton(TexRegionDrawableButton_touch);
        button_slide = new ImageButton(TexRegionDrawableButton_slide);
        button_settings = new ImageButton(TexRegionDrawableButton_settings);
        float screenWidth = Gdx.graphics.getWidth()/10, screenHeight = Gdx.graphics.getHeight()/10;
        button_touch.setPosition( screenWidth,  5*screenHeight);
        button_slide.setPosition( screenWidth*5.75f,  5*screenHeight);
        button_settings.setPosition( (screenWidth*10)-64,  (10*screenHeight)-64);
        stage.addActor(button_touch);
        stage.addActor(button_slide);
        stage.addActor(button_settings);
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("pacman.gif").read());

        method = "Méthode de déplacement : ";
        layout = new GlyphLayout();
        MethodUsed = new BitmapFont();
        effective = (game.controlMethod) ? "Contrôle par zones" : "Contrôle par glisser";
        layout.setText(MethodUsed.newFontCache().getFont(), method+effective);

        button_touch.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.controlMethod = true;
                return true;
            }
        });

        button_slide.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.controlMethod = false;
                return true;
            }
        });
        button_settings.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.gotoMenuScreen();
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
        effective = (game.controlMethod) ? "Contrôle par zones" : "Contrôle par glisser";
        layout.setText(MethodUsed.newFontCache().getFont(), method+effective);
        MethodUsed.draw(game.batch, method+effective, 75, 400);
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
