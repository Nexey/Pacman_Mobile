package com.gdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.entities.Entity;
import com.gdx.game.utilities.Util;
import com.gdx.game.view.TextureFactory;

public class Pacman extends GameElement implements Entity {
    public static final float size=16;

    public Pacman(Vector2 position, World world) {
        super(position, world);
    }

    @Override
    public float getWidth() {
        return size;
    }

    @Override
    public float getHeight() {
        return size;
    }

    @Override
    public Texture getTexture() {
        return TextureFactory.getInstance().getTexture("pacman");
    }
    /*
    private void resumeOldDir(Vector2 oldPos) {
        if (validTile(oldPos))
        Util.currentDir = Util.previousDir;
    }
    */

    @Override
    public void move() {
        switch(Util.currentDir) {
            case Util.UP:
                if (this._world.getMaze().validTile(new Vector2(this.position.x, this.position.y + 1)))
                    this.position.y++;
                break;
            case Util.LEFT:
                if (this._world.getMaze().validTile(new Vector2(this.position.x - 1, this.position.y)))
                    this.position.x--;
                break;
            case Util.DOWN:
                if (this._world.getMaze().validTile(new Vector2(this.position.x, this.position.y - 1)))
                    this.position.y--;
                break;
            case Util.RIGHT:
                if (this._world.getMaze().validTile(new Vector2(this.position.x + 1, this.position.y)))
                    this.position.x++;
                break;
            default:
                break;
        }
    }
}