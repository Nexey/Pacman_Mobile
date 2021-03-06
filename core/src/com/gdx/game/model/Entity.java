package com.gdx.game.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public abstract class Entity extends GameElement {
    public static final float size=16;
    private GameElement tile;
    protected ArrayList<GameElement> listValidTiles;

    protected String directions[] = {
            "",
            "",
            "",
            ""
    };
    protected String currentAnim;

    // alpha va varier entre 0 et 1. 0 : doit bouger, 0.5 est à la moitié du mouvement, 1 ne doit pas bouger
    public float alpha;
    public Vector2 newPosition;
    public float velocity = 0.075f;

    public Entity(Vector2 pos, World world) {
        super(pos, world);
        this.tile = null;
        this.listValidTiles = new ArrayList<GameElement>();
        this.listValidTiles.add(new Dark(new Vector2(0, 0), this._world));
        this.listValidTiles.add(new Gom(new Vector2(0, 0), this._world));
        this.listValidTiles.add(new SuperGom(new Vector2(0, 0), this._world));
        this.alpha = 1;
    }
    public abstract void updateAnimation();

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
        this.tile = this._world.getMaze().get(new Vector2(this.newPosition));
    }

    // Récupère la tile à la position de l'entity
    // C'est pour cela que si elle est vide je lui mets une case vide à sa propre position
    public GameElement retrieveTile() {
        if (this.tile == null)
            this.setDarkTile();
        return this.tile;
    }

    private boolean checkLeftBound(Vector2 pos) {
        return (pos.x >= 0) && (pos.y >= 0);
    }

    private boolean checkRightBound(Vector2 pos) {
        return (pos.x < this._world.getHeight()) && (pos.y < this._world.getWidth());
    }

    private boolean checkCoords(Vector2 pos) {
        return checkLeftBound(pos) && checkRightBound(pos);
    }

    public boolean validTile(Vector2 pos) {
        GameElement ge;
        if (!checkCoords(pos)) {
            // L'Entity est sortie à gauche du labyrinthe, elle doit réapparaitre à droite
            if (!checkLeftBound(pos)) {
                this.setPosition(new Vector2(this.getPosition().x, this._world.getWidth()));
                pos = new Vector2(this.getPosition().x, this._world.getWidth() - 1);
            }
            else {
                this.setPosition(new Vector2(this.getPosition().x, (this._world.getWidth() - (this.getPosition().y + 1)) - 1));
                pos = new Vector2(this.getPosition().x, (this.getPosition().y + 1 ));
            }
        }
        ge = this._world.getMaze().get(new Vector2(pos));
        return this.listValidTiles.contains(ge);
    }


    public boolean aGauche()
    {
        if (this.validTile(new Vector2(this.getPosition().x - 1, this.getPosition().y))) {
            this.newPosition = new Vector2(this.getPosition().x - 1, this.getPosition().y);
            //this.setX((int)this.getPosition().x - 1);
            return true;
        }
        return false;
    }

    public boolean aDroite()
    {
        if (this.validTile(new Vector2(this.getPosition().x + 1, this.getPosition().y))) {
            this.newPosition = new Vector2(this.getPosition().x + 1, this.getPosition().y);
            //this.setX((int)this.getPosition().x + 1);
            return true;
        }
        return false;
    }

    public boolean enHaut()
    {
        if (this.validTile(new Vector2(this.getPosition().x, this.getPosition().y + 1))) {
            this.newPosition = new Vector2(this.getPosition().x, this.getPosition().y + 1);
            return true;
        }
        return false;
    }

    public boolean enBas()
    {
        if (this.validTile(new Vector2(this.getPosition().x, this.getPosition().y - 1))) {
            this.newPosition = new Vector2(this.getPosition().x, this.getPosition().y - 1);
            return true;
        }
        return false;
    }

    boolean collision(Entity E)
    {
        return false;
    }
}
