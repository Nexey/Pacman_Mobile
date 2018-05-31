package com.gdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.entities.Entity;
import com.gdx.game.utilities.Util;
import com.gdx.game.view.TextureFactory;

public class Ghost extends GameElement implements Entity {
    public static final float size=16;
    private int dir;

    public Ghost(Vector2 position, World monde) {
        super(position, monde);
        dir = Util.UP;
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

    private boolean canMove(Vector2 pos) {
        Block block = new Block(new Vector2(0, 0), this.monde);
        return (!this.monde.getMaze().get(pos).equals(block));
    }

    @Override
    public void move() {/*
        switch(Util.currentDir) {
            case Util.UP:
                if (this.canMove(new Vector2(this.position.x, this.position.y + 1))) {
                    this.position.y++;
                }
                break;
            case Util.LEFT:
                if (this.canMove(new Vector2(this.position.x - 1, this.position.y)))
                    this.position.x--;
                break;
            case Util.DOWN:
                if (this.canMove(new Vector2(this.position.x, this.position.y - 1)))
                    this.position.y--;
                break;
            case Util.RIGHT:
                if (this.canMove(new Vector2(this.position.x + 1, this.position.y)))
                    this.position.x++;
                break;
            default:
                break;
        }*/
    }
}
