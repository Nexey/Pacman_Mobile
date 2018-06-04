package com.gdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.gdx.game.PacManGdx;

public class BasicScreen implements Screen {

    protected final PacManGdx game;
    final Stage stage;
    final Animation<TextureRegion> animation;
    float elapsed;
    final ImageButton button1;
    final ImageButton button2;
    final ImageButton button_settings;
    BitmapFont font;
    final FreeTypeFontGenerator generator;
    GlyphLayout layout;
    final String title;

    BasicScreen(final PacManGdx game, FileHandle Filebutton1, FileHandle Filebutton2){
        this.game = game;
        stage = new Stage(game.screenPort);
        Gdx.input.setInputProcessor(stage);
        Texture texturebutton1 = new Texture(Filebutton1);
        Texture texturebutton2 = new Texture(Filebutton2);
        Texture texturebutton_settings = new Texture(Gdx.files.internal("settings.png"));
        TextureRegion textureRegionButton1 = new TextureRegion(texturebutton1);
        TextureRegion textureRegionButton2 = new TextureRegion(texturebutton2);
        TextureRegion textureRegionButton_settings = new TextureRegion(texturebutton_settings);
        TextureRegionDrawable texRegionDrawableButton1 = new TextureRegionDrawable(textureRegionButton1);
        TextureRegionDrawable texRegionDrawableButton2 = new TextureRegionDrawable(textureRegionButton2);
        TextureRegionDrawable texRegionDrawableButton_settings = new TextureRegionDrawable(textureRegionButton_settings);
        button1 = new ImageButton(texRegionDrawableButton1);
        button2 = new ImageButton(texRegionDrawableButton2);
        button_settings = new ImageButton(texRegionDrawableButton_settings);
        float screenWidth = Gdx.graphics.getWidth()/10, screenHeight = Gdx.graphics.getHeight()/10;
        button1.setPosition( screenWidth,  5*screenHeight);
        button2.setPosition( screenWidth*5.75f,  5*screenHeight);
        button_settings.setPosition( (screenWidth*10)-64,  (10*screenHeight)-64);
        stage.addActor(button1);
        stage.addActor(button2);
        stage.addActor(button_settings);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("PressStart2P.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 64;
        layout= new GlyphLayout();
        font = new BitmapFont();
        font = generator.generateFont(parameter);
        font.setColor(1,1,1,1);
        layout.setText(font,"Pacman" );
        title ="Pacman";
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("pacman.gif").read());

        
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
        font.draw(game.batch, title, 35, 400);
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
        generator.dispose();
    }
}

