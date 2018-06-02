package com.gdx.game.controller.deplacements;

import com.badlogic.gdx.math.Vector2;
import com.gdx.game.controller.utilities.DiceFour;
import com.gdx.game.controller.utilities.DiceTwo;
import com.gdx.game.controller.utilities.Util;
import com.gdx.game.model.Ghost;
import com.gdx.game.model.Pacman;

import static com.gdx.game.controller.deplacements.deplacement.updateCoords;
import static java.lang.Math.abs;

public class deplacementGhost {
    public static boolean move(Pacman pacman, Ghost ghost, int dep) {
        if(dep == 0) { // 50% de chance de changer la Ghost.getDir()ection
            if (DiceTwo.getFace() == 1)
                ghost.setDir(DiceFour.getFace());
            return updateCoords(ghost.getDir(), ghost);
        }
        else if (dep == 1)
        {
            return deplacementGhost1(ghost);
        }
        else if (dep == 2)
        {
            return deplacementGhost2(pacman, ghost);
        }
        else
            return deplacementGhost3(pacman, ghost);
    }

    private static boolean deplacementGhost1 (Ghost ghost)
    {
        if(ghost.getWorld().getMaze().getCase(ghost.getPosition()) == 2)
        {
            do {
                ghost.setDir(DiceFour.getFace());
            }while(!updateCoords(ghost.getDir(), ghost));
            return true;
        }
        else
            return updateCoords(ghost.getDir(), ghost);
    }

    private static boolean deplacementGhost2 (Pacman pacman, Ghost ghost)
    {
        if(ghost.getWorld().getMaze().getCase(ghost.getPosition()) == 2)
        {
            Vector2 posPacman = new Vector2(pacman.getPosition());
            if(abs(ghost.getPosition().x - posPacman.x) > abs(ghost.getPosition().y - posPacman.y))
            {
                if(ghost.getPosition().x > posPacman.x)
                    ghost.setDir(Util.LEFT);
                else
                    ghost.setDir(Util.RIGHT);
            }
            else
            {
                if(ghost.getPosition().y > posPacman.y)
                    ghost.setDir(Util.UP);
                else
                    ghost.setDir(Util.DOWN);
            }
            return updateCoords(ghost.getDir(), ghost);
        }
        else
            return updateCoords(ghost.getDir(), ghost);
    }

    private static boolean deplacementGhost3 (Pacman pacman, Ghost ghost)
    {
        if (DiceTwo.getFace() == 0)
            return deplacementGhost1(ghost);
        else
            return deplacementGhost2(pacman, ghost);
    }
}
