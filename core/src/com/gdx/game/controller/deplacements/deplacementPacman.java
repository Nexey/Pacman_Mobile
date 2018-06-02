package com.gdx.game.controller.deplacements;

import com.gdx.game.controller.utilities.Util;
import com.gdx.game.model.Gom;
import com.gdx.game.model.Pacman;

import static com.gdx.game.controller.deplacements.deplacement.updateCoords;

public abstract class deplacementPacman{
    public static boolean move(Pacman pacman)
    {
        if (updateCoords(Util.currentDir, pacman)) {
            // Si c'est une gomme, on la remplace par une case vide et on incrémente le score
            if (pacman.retrieveTile().equals(Gom.class)) {
                Util.SCORE++;
                pacman.setDarkTile();
                System.out.println("SCORE : " + Util.SCORE);
            }
            return true;
        }
        return false;
    }
}
