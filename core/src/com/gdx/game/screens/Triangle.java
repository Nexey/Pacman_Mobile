package com.gdx.game.screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.gdx.game.model.World;

public class Triangle implements Screen, InputProcessor, ApplicationListener {
    private Mesh mesh;
    private Vector2 _mid;
    private Vector2 _left;
    private Vector2 _right;
    private SpriteBatch batch;
    ShaderProgram shader;

    public Triangle(Vector2 verticeLeft,Vector2 verticeRight, SpriteBatch batch) {
        this.batch = batch;
        this._left = new Vector2(verticeLeft.x, verticeLeft.y);
        this._mid = new Vector2(Gdx.app.getGraphics().getWidth() / 2, Gdx.app.getGraphics().getHeight() / 2);
        this._right = new Vector2(verticeRight.x, verticeRight.y);

        this.create();
        //this.createShader();
    }

    @Override
    public void create() {
        if (mesh == null) {
            mesh = new Mesh(true, 3, 3,
                    new VertexAttribute(VertexAttributes.Usage.Position,
                            3, "aPosition"));
            mesh.setVertices(new float[]{
                    this._left.x, this._left.y, 0,
                    this._mid.x, this._mid.y, 0,
                    this._right.x, this._right.y, 0
            });
            mesh.setIndices(new short[]{0, 1, 2});
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 touchPos = new Vector3(screenX, screenY, 0);

        boolean belowUpRight = (screenX > screenY);
        boolean belowDownRight = (screenX < (Gdx.graphics.getWidth() - screenY));
        boolean touchLeft = !belowUpRight && belowDownRight;
        boolean touchDown = !belowUpRight && !belowDownRight;
        boolean touchRight = belowUpRight && !belowDownRight;
        boolean touchUp = belowUpRight && belowDownRight;

        System.out.println(touchDown);
        System.out.println(touchLeft);
        System.out.println(touchRight);
        System.out.println(touchUp);



        if (this.mesh.calculateBoundingBox().contains(touchPos)) {
            Gdx.app.log("Test", "Le triange a été cliqué" + screenX + " " + screenY);
            return true;
        }
        else {
            Gdx.app.log("Test", "Le triange n'a pas été cliqué" + screenX + " " + screenY);
            return false;
        }
    }

    public void createShader() {

        String vertexShader =
                "attribute vec4 vPosition; 		\n" +
                "uniform mat4 u_projectionViewMatrix; \n" +
                "void main()					\n" +
                "{								\n" +
                "	gl_Position = u_projectionViewMatrix *  aPosition;	\n" +
                "}								\n";
        String fragmentShader =
                "#ifdef GL_ES 								\n" +
                "precision mediump float;					\n" +
                "#endif 									\n" +
                "void main()								\n" +
                "{											\n" +
                "	gl_FragColor = vec4(1.0,1.0,0.0,1.0);	\n" +
                "}											\n";


        shader = new ShaderProgram(vertexShader, fragmentShader);
        if (!shader.isCompiled())
            Gdx.app.log("ShaderError", shader.getLog());
    }


    public void render() {
        //clear viewport to black
        //Gdx.gl20.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //draw
        //batch.begin();
        mesh.render(shader, GL20.GL_TRIANGLES);
        //batch.end();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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
        batch.dispose();
        mesh.dispose();
    }
}
