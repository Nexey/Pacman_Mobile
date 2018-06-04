package com.gdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.gdx.game.controller.utilities.DiceFour;
import com.gdx.game.controller.utilities.DiceTwo;
import com.gdx.game.controller.utilities.Util;
import com.gdx.game.view.TextureFactory;
import sun.security.ssl.Krb5Helper;

import java.util.HashMap;

import static java.lang.Math.abs;

public class Ghost extends Entity {
    private int color;
    private DiceFour diceFour;
    private DiceTwo diceTwo;
    private int dir;
    private int dep; //affecte une fonction de déplacement
    private boolean sorti;
    private HashMap<Integer, String> listState;
    private int state;
    private final int
            ALIVE = 0,
            ESCAPING = 1,
            DEAD = 2;
    private long startDeathTime;

    public Ghost(Vector2 position, World world, int color, int dep) {
        super(position, world);
        this.color = color;
        diceFour = new DiceFour();
        diceTwo = new DiceTwo();
        dir = diceFour.getFace();
        this.dep = dep;
        sorti = false;
        this.listValidTiles.add(new Fence(new Vector2(0, 0), this._world));
        state = 0;
        listState = new HashMap<Integer, String>();
        listState.put(0, "ghost" + this.color);
        listState.put(1, "ghostEscaping");
        listState.put(2, "ghostDead");

        this.currentAnim = "ghost" + this.color + "Up";

        this.directions[3] = "Up";
        this.directions[0] = "Left";
        this.directions[1] = "Down";
        this.directions[2] = "Right";
    }

    public void updateAnimation() {
        switch(state) {
            case ALIVE:
                currentAnim = this.listState.get(ALIVE) + this.directions[dir];
                break;
            case ESCAPING:
                currentAnim = this.listState.get(ESCAPING);
                break;
            case DEAD:
                currentAnim = this.listState.get(DEAD);
                break;
        }
    }

    private void updateState() {
        boolean powerUp = this._world.getPacman().getPowerUp();

        // 2 cas : Game Over ou le Ghost est mort pendant un certain temps
        if (state == DEAD) {
            long elapsedTime = TimeUtils.timeSinceMillis(startDeathTime);
            if (elapsedTime > 10000)
                this.state = ALIVE;
        }
        else if (powerUp) {
            // Mise à jour de l'état du fantome
            if (this.state == ALIVE) this.state = ESCAPING;

            // Maintenant il se peut que le fantôme soit sur la case du pacman, il doit mourir s'il n'est pas encore mort
            if (this.state == ESCAPING) {
                if (this._world.getPacman().newPosition.equals(this.newPosition)) {
                    this.startDeathTime = TimeUtils.millis();
                    this.state = DEAD;
                }
            }
        }
        // Il n'y a pas de pouvoir, je vérifie que l'état est bien remis à 0 s'il n'est pas actuellement mort
        else if (this.state == ESCAPING)
            this.state = ALIVE;
    }

    @Override
    public Sprite getSprite() {
        this.updateState();
        this.updateAnimation();
        return TextureFactory.getInstance().getSprite(this.currentAnim);
    }

    @Override
    public Texture getTexture() {
        return getSprite().getTexture();
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
        if (sorti)
            this.listValidTiles.remove(new Fence(new Vector2(0, 0), this._world));
        this.sorti = sorti;
    }

    @Override
    public boolean move() {
        if (alpha == 1) {
            if (this._world.listMovingEntities.contains(this))
               this._world.listMovingEntities.remove(this);
            Vector2 oldPos = new Vector2(this.getPosition());
        /*if (this.getPosition().equals(this._world.getPacman().getPosition())) {
            if (this._world.getPacman().getPowerUp())
                this.state = 2;
            else {
                this.state = 0;

                // TODO : GAME OVER
            }
        }*/

            switch (dep) {
                case 0:
                    if (diceTwo.getFace() == 1)
                        dir = diceFour.getFace();
                    if (updateCoords(dir)) {
                        this._world.set(new Vector2(oldPos), this.retrieveTile());
                        return true;
                    }
                    break;
                case 1:
                    if (deplacementGhost1()) {
                        this._world.set(new Vector2(oldPos), this.retrieveTile());
                        this.setCurrentTile();
                        return true;
                    }
                    break;
                case 2:
                    if (deplacementGhost2(this._world.getPacman().getPosition())) {
                        this._world.set(new Vector2(oldPos), this.retrieveTile());
                        this.setCurrentTile();
                        return true;
                    }
                    break;
                case 3:
                    if (deplacementGhost3(this._world.getPacman().getPosition())) {
                        this._world.set(new Vector2(oldPos), this.retrieveTile());
                        this.setCurrentTile();
                        return true;
                    }
                    break;
                case 4:
                    if (deplacementGhost4(this._world.getPacman().getPosition())) {
                        this._world.set(new Vector2(oldPos), this.retrieveTile());
                        this.setCurrentTile();
                        return true;
                    }
                    break;
                default:
                    break;
            }
            return false;
        }
        // Le ghost n'a pas fini son déplacement
        else return false;
    }

    protected boolean updateCoords(int dir) {
        boolean hasMoved = false;
        switch(dir) {
            case Util.UPG:
                if(enHaut())
                    hasMoved = true;
                break;
            case Util.LEFTG:
                if(aGauche())
                    hasMoved = true;
                break;
            case Util.DOWNG:
                if(enBas())
                    hasMoved = true;
                break;
            case Util.RIGHTG:
                if(aDroite())
                    hasMoved = true;
                break;
            default:
                break;
        }

        return hasMoved;
    }

    private boolean deplacementGhost1 ()
    {
        if(isSorti())
        {
            do
            {
                dir = diceFour.getFace();
            } while (!updateCoords(dir));
            return true;
        }
        else
            return sortez();
    }

    private boolean deplacementGhost2 (Vector2 pos)
    {
        //System.out.println(this.getDir());
        //System.out.println(this.getWorld().getMaze().validTile(new Vector2(this.getPosition().x, this.getPosition().y + 1)));
        //System.out.println(this._world.getMaze().getCase(this.getPosition()));
        //if(this._world.getMaze().getCase(this.getPosition()) == 2)
        if(isSorti())
        {
            //Vector2 posPacman = new Vector2(pacman.getPosition());
            //System.out.println("X ghost : "+this.getPosition().x+" Y ghost : "+this.getPosition().y);
            //System.out.println("X pacman : "+posPacman.x+" Y pacman : "+posPacman.y);
            if(goDir(pos))
                return true;
            else
            {
                return deplacementGhost1();
            }
        }
        else
        {
            return sortez();
        }
    }

    private boolean deplacementGhost3 (Vector2 pos)
    {
        if (diceTwo.getFace() == 0)
            return deplacementGhost1();
        else
            return deplacementGhost2(pos);
    }

    private boolean deplacementGhost4 (Vector2 pos)
    {
        setSorti(true);
        return false;
    }

    private boolean sortez()
    {
        Vector2 pos = new Vector2(11, 14);
        if(goDir(pos))
        {
            if(getPosition().equals(pos))
                setSorti(true);
            return true;
        }
        if(getPosition().equals(pos))
            setSorti(true);
        return true;
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

    private boolean goDir(Vector2 pos)
    {
        if(this.getPosition().x > pos.x) {
            //System.out.println("F2 : "+showDir());
            this.setDir(Util.LEFTG);
            if(updateCoords(this.dir)) return true;
        }
        if(this.getPosition().x < pos.x)
        {
            //System.out.println("F2 : "+showDir());
            this.setDir(Util.RIGHTG);
            if(updateCoords(this.dir)) return true;
        }
        if(this.getPosition().y > pos.y)
        {
            //System.out.println("F2 : "+showDir());
            this.setDir(Util.DOWNG);
            if(updateCoords(this.dir)) return true;
        }
        if(this.getPosition().y < pos.y)
        {
            //System.out.println("F2 : "+showDir());
            this.setDir(Util.UPG);
            if(updateCoords(this.dir)) return true;
        }
        return false;
    }
}
