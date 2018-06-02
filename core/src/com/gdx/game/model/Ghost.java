package com.gdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.controller.utilities.DiceFour;
import com.gdx.game.controller.utilities.DiceTwo;
import com.gdx.game.controller.utilities.Util;
import com.gdx.game.view.TextureFactory;

import static java.lang.Math.abs;

public class Ghost extends Entity {
    private int color;
    private DiceFour diceFour;
    private DiceTwo diceTwo;
    private int dir;
    private int dep; //affecte une fonction de déplacement
    private boolean sorti;

    public Ghost(Vector2 position, World world, int color, int dep) {
        super(position, world);
        this.color = color;
        diceFour = new DiceFour();
        diceTwo = new DiceTwo();
        dir = diceFour.getFace();
        this.dep = dep;
        sorti = false;
    }

    @Override
    public Texture getTexture() {
        return TextureFactory.getInstance().getTexture("ghost" + this.color);
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public boolean isSorti() {
        return sorti;
    }

    public void setSorti(boolean sorti) {
        this.sorti = sorti;
    }

    @Override
    public boolean move() {
        if (this.retrieveTile().equals(Pacman.class)); // GAME OVER
        switch(dep) {
            case 0:
                if (diceTwo.getFace() == 1)
                    dir = diceFour.getFace();
                return updateCoords(dir);
            case 1:
                return deplacementGhost1();
            case 2:
                return deplacementGhost2(this._world.getPacman());
            case 3:
                return deplacementGhost3(this._world.getPacman());
            case 4:
                return deplacementGhost4(this._world.getPacman());
            default:
                return false;
        }
    }

    protected boolean updateCoords(int dir) {
        //System.out.println("Update : "+showDir());
        this._world.getMaze().set(new Vector2(this.getPosition()), this.retrieveTile());
        switch(dir) {
            case Util.UPG:
                if(enHaut())
                    return true;
                break;
            case Util.LEFTG:
                if(aGauche())
                    return true;
                break;
            case Util.DOWNG:
                if(enBas())
                    return true;
                break;
            case Util.RIGHTG:
                if(aDroite())
                    return true;
                break;
            default:
                break;
        }
        return false;
    }

    private boolean deplacementGhost1 ()
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

    private boolean deplacementGhost2 (Pacman pacman)
    {
        //System.out.println(this.getDir());
        //System.out.println(this.getWorld().getMaze().validTile(new Vector2(this.getPosition().x, this.getPosition().y + 1)));
        //System.out.println(this._world.getMaze().getCase(this.getPosition()));
        setSorti(true);
        //if(this._world.getMaze().getCase(this.getPosition()) == 2)
        //{
            this.setSorti(true);
            Vector2 posPacman = new Vector2(pacman.getPosition());
            System.out.println("X ghost : "+this.getPosition().x+" Y ghost : "+this.getPosition().y);
            System.out.println("X pacman : "+posPacman.x+" Y pacman : "+posPacman.y);
            if(this.getPosition().x > posPacman.x) {
                System.out.println("F2 : "+showDir());
                this.setDir(Util.LEFTG);
                if(updateCoords(this.dir)) return true;
            }
            if(this.getPosition().x < posPacman.x)
            {
                System.out.println("F2 : "+showDir());
                this.setDir(Util.RIGHTG);
                if(updateCoords(this.dir)) return true;
            }
            if(this.getPosition().y > posPacman.y)
            {
                System.out.println("F2 : "+showDir());
                this.setDir(Util.DOWNG);
                if(updateCoords(this.dir)) return true;
            }
            if(this.getPosition().y < posPacman.y)
            {
                System.out.println("F2 : "+showDir());
                this.setDir(Util.UPG);
                if(updateCoords(this.dir)) return true;
            }

            if (diceTwo.getFace() == 1)
            dir = diceFour.getFace();
            return updateCoords(dir);

        //}
        /*else
        {
            if (this.isSorti())
            {
                return updateCoords(dir);
            }
            else
            {
                boolean a = sortez(dir);
                return a;
            }
        }*/
    }

    private boolean deplacementGhost3 (Pacman pacman)
    {
        if (diceTwo.getFace() == 0)
            return deplacementGhost1();
        else
            return deplacementGhost2(pacman);
    }

    private boolean deplacementGhost4 (Pacman pacman)
    {
        return false;
    }

    private boolean sortez(int dir)
    {
        System.out.println(dir);
        this.dir = Util.UPG;
        if(enHaut())
            return true;
        else {
            this.dir = dir;
            if (dir == Util.LEFTG) {
                if (aGauche())
                    return true;
            } else {
                if (aDroite())
                    return true;
            }
        }
        return false;
    }

    private String showDir()
    {
        switch(dir) {
            case Util.UPG:
                    return "Haut";
            case Util.LEFTG:
                if (aGauche())
                    return "Gauche";
            case Util.DOWNG:
                    return "Bas";
            case Util.RIGHTG:
                    return "Droite";
            default:
                break;
        }
        return dir + " ?? No Direction !!!";
    }
}
