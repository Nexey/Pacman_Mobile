package com.gdx.game.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public abstract class Entity extends GameElement {
    public static final float size=16;
    private GameElement tile;

    ArrayList<GameElement> listValidTiles;

    public Entity(Vector2 pos, World world) {
        super(pos, world);
        this.tile = null;
        this.listValidTiles = new ArrayList<GameElement>();
        this.listValidTiles.add(new Dark(new Vector2(0, 0), this._world));
        this.listValidTiles.add(new Gom(new Vector2(0, 0), this._world));
        this.listValidTiles.add(new SuperGom(new Vector2(0, 0), this._world));
    }

    @Override
    public float getWidth() {
        return size;
    }

    @Override
    public float getHeight() {
        return size;
    }

    protected abstract boolean updateCoords(int dir);

    public abstract boolean move();

    protected void setDarkTile() {
        this.tile = new Dark(new Vector2(this.getPosition()), this._world);
    }

    protected void setCurrentTile() {
        this.tile = this._world.getMaze().get(new Vector2(this.getPosition()));
    }

    public GameElement retrieveTile() {
        if (this.tile == null)
            this.setDarkTile();
        return this.tile;
    }

    private boolean checkCoords(Vector2 pos) {
        boolean checkLeftBound = (pos.x >= 0) && (pos.y >= 0);
        boolean checkRightBound = (pos.x < this._world.getHeight()) && (pos.y < this._world.getWidth());
        return checkLeftBound && checkRightBound;
    }

    public boolean validTile(Vector2 pos) {
        GameElement ge;

        if (checkCoords(pos)) {
            ge = this._world.getMaze().get(new Vector2(pos));
            return this.listValidTiles.contains(ge);
        }
        else return false;
    }


    public boolean aGauche()
    {
        if (this.validTile(new Vector2(this.getPosition().x - 1, this.getPosition().y))) {
            this.setX((int)this.getPosition().x - 1);
            return true;
        }
        return false;
    }

    public boolean aDroite()
    {
        if (this.validTile(new Vector2(this.getPosition().x + 1, this.getPosition().y))) {
            this.setX((int)this.getPosition().x + 1);
            return true;
        }
        return false;
    }

    public boolean enHaut()
    {
        if (this.validTile(new Vector2(this.getPosition().x, this.getPosition().y + 1))) {
            this.setY((int)this.getPosition().y + 1);
            return true;
        }
        return false;
    }

    public boolean enBas()
    {
        if (this.validTile(new Vector2(this.getPosition().x, this.getPosition().y - 1))) {
            this.setY((int)this.getPosition().y - 1);
            return true;
        }
        return false;
    }
}
