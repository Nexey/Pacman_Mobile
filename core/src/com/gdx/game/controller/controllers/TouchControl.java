package com.gdx.game.controller.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.gdx.game.controller.utilities.Util;

public class TouchControl implements InputProcessor {

    public TouchControl() {}

    public boolean swipeLeft() {
        return (Gdx.input.justTouched() && Gdx.input.getDeltaY() < 0);
    }

    public boolean swipeDown() {
        return (Gdx.input.justTouched() && Gdx.input.getDeltaX() < 0);
    }

    public boolean swipeRight() {
        return (Gdx.input.justTouched() && Gdx.input.getDeltaY() > 0);
    }

    public boolean swipeUp() {
        return (Gdx.input.justTouched() && Gdx.input.getDeltaX() > 0);
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