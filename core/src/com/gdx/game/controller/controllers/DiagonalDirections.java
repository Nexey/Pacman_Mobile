package com.gdx.game.controller.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.gdx.game.controller.utilities.Util;

public class DiagonalDirections implements InputProcessor {

    public DiagonalDirections() {}

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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        boolean belowUpRight = (screenX > screenY);
        boolean belowDownRight = (screenX < (Gdx.graphics.getWidth() - screenY));

        boolean directions[] = {
                belowUpRight && belowDownRight,
                !belowUpRight && belowDownRight,
                !belowUpRight && !belowDownRight,
                belowUpRight && !belowDownRight
        };

        // On garde l'ancienne direction avant de mettre à jour celle en cours
        Util.previousDir = Util.currentDir;
        for (int i = 0; i < directions.length; i++)
            if (directions[i])
                Util.currentDir = i;
        return true;
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
}
