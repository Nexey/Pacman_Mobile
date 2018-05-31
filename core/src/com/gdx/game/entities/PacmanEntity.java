package com.gdx.game.entities;

import com.badlogic.gdx.math.Vector2;
import com.gdx.game.model.*;
import com.gdx.game.utilities.Util;

public class PacmanEntity extends Entity {

    private Pacman text;

    public PacmanEntity(Vector2 pos, Pacman text) {
        super(pos);
        this.text = text;
    }

    private boolean canMove(Vector2 pos) {
        return (Util.laby.get(pos).equals(Dark.class)) || (Util.laby.get(pos).equals(Gom.class)) || (Util.laby.get(pos).equals(SuperGom.class));
    }

    @Override
    public void move() {
        switch(Util.currentDir) {
            case Util.UP:
                if (this.canMove(new Vector2(this.pos.x, this.pos.y + 1))) {
                    this.pos.y++;
                }
                break;
            case Util.LEFT:
                if (this.canMove(new Vector2(this.pos.x - 1, this.pos.y)))
                    this.pos.x--;
                break;
            case Util.DOWN:
                if (this.canMove(new Vector2(this.pos.x, this.pos.y - 1)))
                    this.pos.y--;
                break;
            case Util.RIGHT:
                if (this.canMove(new Vector2(this.pos.x + 1, this.pos.y)))
                    this.pos.x++;
                break;
            default:
                break;
        }
        this.text.setPosition(new Vector2(this.pos.x, this.pos.y));
    }
}
