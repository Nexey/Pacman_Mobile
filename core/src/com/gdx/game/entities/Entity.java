package com.gdx.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.gdx.game.model.Dark;
import com.gdx.game.model.GameElement;
import com.gdx.game.model.World;
import com.gdx.game.utilities.Util;

public abstract class Entity extends GameElement {
    public static final float size=16;
    private GameElement tile;

    public Entity(Vector2 pos, World world) {
        super(pos, world);
    }

    @Override
    public float getWidth() {
        return size;
    }

    @Override
    public float getHeight() {
        return size;
    }

    protected void updateCoords(int dir) {
        Vector2 oldPos = new Vector2(this.position);
        switch(dir) {
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
        // On a détecté un changement de position. On récupère alors la case sur laquelle on est, car on va l'écraser juste après
        // avec l'entity
        if (oldPos != this.position) this.setCurrentTile();
    }

    public abstract void move();

    public void setDarkTile() {
        this.tile = new Dark(new Vector2(this.position), this._world);
    }

    public void setCurrentTile() {
        this.tile = this._world.getMaze().get(this.position);
    }

    public GameElement retrieveTile() {
        return this.tile;
    }
}
