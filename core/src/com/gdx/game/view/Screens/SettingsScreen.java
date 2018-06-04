package com.gdx.game.view.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.gdx.game.PacManGdx;

public class SettingsScreen extends BasicScreen
{
    private String method, effective;
    private BitmapFont MethodUsed;

    public SettingsScreen(final PacManGdx game){

        super(game, Gdx.files.internal("touch_button.png"), Gdx.files.internal("slide_button.png"));
        method = "Méthode de déplacement : ";
        layout = new GlyphLayout();
        MethodUsed = new BitmapFont();
        effective = (game.controlMethod) ? "Contrôle par zones" : "Contrôle par glisser";
        layout.setText(MethodUsed.newFontCache().getFont(), method+effective);

        button1.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                game.controlMethod = true;
                return true;
            }
        });

        button2.addListener(new InputListener(){
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
        font.draw(game.batch, title, 35, 400);
        MethodUsed.draw(game.batch, method+effective, 75, 200);
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
