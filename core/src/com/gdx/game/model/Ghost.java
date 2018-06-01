package com.gdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.entities.Entity;
import com.gdx.game.utilities.DiceFour;
import com.gdx.game.utilities.DiceTwo;
import com.gdx.game.utilities.Util;
import com.gdx.game.view.TextureFactory;

import static java.lang.Math.abs;

public class Ghost extends Entity {
    private int color;
    private DiceFour diceFour;
    private DiceTwo diceTwo;
    private int dir;
    private int dep; //affecte une fonction de déplacement

    public Ghost(Vector2 position, World world, int color, int dep) {
        super(position, world);
        this.color = color;
        diceFour = new DiceFour();
        diceTwo = new DiceTwo();
        dir = diceFour.getFace();
    }

    @Override
    public Texture getTexture() {
        return TextureFactory.getInstance().getTexture("ghost" + this.color);
    }

    @Override
    public boolean move(Pacman pacman) {
        if(dep == 0) { // 50% de chance de changer la direction
            if (diceTwo.getFace() == 1)
                dir = diceFour.getFace();
            return updateCoords(dir);
        }
        else if (dep == 1)
        {
            return deplacementGhost1();
        }
        else if (dep == 2)
        {
            return deplacementGhost2(pacman);
        }
        else
            return deplacementGhost3(pacman);
    }

    public boolean deplacementGhost1 ()
    {
        if(this._world.getMaze().getCase(getPosition()) == 2)
        {
            do {
                dir = diceFour.getFace();
            }while(!updateCoords(dir));
            return true;
        }
        else
            return updateCoords(dir);
    }

    public boolean deplacementGhost2 (Pacman pacman)
    {
        if(this._world.getMaze().getCase(getPosition()) == 2)
        {
            Vector2 posPacman = pacman.getPosition();
            if(abs(getPosition().x - posPacman.x) > abs(getPosition().y - posPacman.y))
            {
                if(getPosition().x > posPacman.x)
                    dir = Util.LEFT;
                else
                    dir = Util.RIGHT;
            }
            else
            {
                if(getPosition().y > posPacman.y)
                    dir = Util.DOWN;
                else
                    dir = Util.UP;
            }
            return updateCoords(dir);
        }
        else
            return updateCoords(dir);
    }

    public boolean deplacementGhost3 (Pacman pacman)
    {
        if (diceTwo.getFace() == 0)
            return deplacementGhost1();
        else
            return deplacementGhost2(pacman);
    }
}
