package com.gdx.game.controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

public class touchControl implements InputProcessor {
    int oldScreenX, oldScreenY;

    public touchControl(int oldScreenX, int oldScreenY) {
        this.oldScreenX = oldScreenX;
        this.oldScreenY = oldScreenY;
    }

    @Override public boolean mouseMoved(int screenX, int screenY) { return false; }

    @Override public boolean scrolled(int arg0) { return false; }

    @Override
    public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
        return false;
    }

    @Override public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println(screenX+" : "+screenY+" : "+pointer);
        if((screenY < oldScreenY) && (screenX/100 == oldScreenX/100))
            System.out.println("Haut");
        if((screenY > oldScreenY) && (screenX/100 == oldScreenX/100))
            System.out.println("Bas");
        if((screenX > oldScreenX)  && (screenY/100 == oldScreenY/100))
            System.out.println("Droite");
        else if((screenX < oldScreenX) && (screenY/100 == oldScreenY/100))
            System.out.println("Gauche");
        return false;
    }

    @Override public boolean touchUp(int a, int arg1, int arg2, int arg3) {
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
