package com.gdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.entities.Entity;
import com.gdx.game.utilities.Util;
import com.gdx.game.view.TextureFactory;

public class Pacman extends GameElement implements Entity {
    public static final float size=16;

    public Pacman(Vector2 position, World monde) {
        super(position, monde);
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
        return TextureFactory.getInstance().getTexture(this.getClass());
    }

    private boolean checkCoords(Vector2 pos) {
        boolean checkLeftBound = (pos.x >= 0) && (pos.y >= 0);
        boolean checkRightBound = (pos.x < this.monde.getMaze().getHeight()) && (pos.y < this.monde.getMaze().getWidth());
        return checkLeftBound && checkRightBound;
    }

    private boolean validTile(Vector2 pos) {
        Dark dark = new Dark(new Vector2(0, 0), this.monde);
        Gom gom = new Gom(new Vector2(0, 0), this.monde);
        SuperGom superGom = new SuperGom(new Vector2(0, 0), this.monde);

        GameElement ge;

        if (checkCoords(pos)) {
            ge = this.monde.getMaze().get(pos);
            return (ge.equals(dark)) || (ge.equals(gom)) || (ge.equals(superGom));
        }
        else return false;
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
                if (this.validTile(new Vector2(this.position.x, this.position.y + 1)))
                    this.position.y++;
                break;
            case Util.LEFT:
                if (this.validTile(new Vector2(this.position.x - 1, this.position.y)))
                    this.position.x--;
                break;
            case Util.DOWN:
                if (this.validTile(new Vector2(this.position.x, this.position.y - 1)))
                    this.position.y--;
                break;
            case Util.RIGHT:
                if (this.validTile(new Vector2(this.position.x + 1, this.position.y)))
                    this.position.x++;
                break;
            default:
                break;
        }
    }
}