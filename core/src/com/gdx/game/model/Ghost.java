package com.gdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.entities.Entity;
import com.gdx.game.utilities.DiceFour;
import com.gdx.game.utilities.Util;
import com.gdx.game.view.TextureFactory;

public class Ghost extends GameElement implements Entity {
    public static final float size=16;
    private int dir;
    private int color;

    public Ghost(Vector2 position, World world, int color) {
        super(position, world);
        this.color = color;
        dir = Util.NOWHERE;
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
        return TextureFactory.getInstance().getTexture("ghost" + this.color);
    }

    @Override
    public void move() {
        this.dir = new DiceFour().getFace();
        switch(this.dir) {
            case Util.UP:
                if (this._world.getMaze().validTile(new Vector2(this.position.x, this.position.y + 1))) {
                    this.position.y++;
                }
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
