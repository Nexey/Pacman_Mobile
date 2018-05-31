package com.gdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.gdx.game.utilities.Util;

public class TouchControl implements InputProcessor {

    public TouchControl() {
    }

    public boolean swipeRight() {
        return (Gdx.input.isTouched() && Gdx.input.getDeltaX() > 0);
    }

    public boolean swipeLeft() {
        return (Gdx.input.isTouched() && Gdx.input.getDeltaX() < 0);
    }

    public boolean swipeUp() {
        return (Gdx.input.isTouched() && Gdx.input.getDeltaY() < 0);
    }

    public boolean swipeDown() {
        return (Gdx.input.isTouched() && Gdx.input.getDeltaY() > 0);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int arg0) {
        return false;
    }

    @Override
    public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (swipeUp()) Util.currentDir = Util.UP;
        else if (swipeLeft()) Util.currentDir = Util.LEFT;
        else if (swipeDown()) Util.currentDir = Util.DOWN;
        else if (swipeRight()) Util.currentDir = Util.RIGHT;
        return true;
    }

    @Override
    public boolean touchUp(int a, int arg1, int arg2, int arg3) {
        return false;
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
}