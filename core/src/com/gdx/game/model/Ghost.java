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

    public Ghost(Vector2 position, World world, int color, int dep) {
        super(position, world);
        this.color = color;
        diceFour = new DiceFour();
        diceTwo = new DiceTwo();
        dir = diceFour.getFace();
        this.dep = dep;
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

    public int getDep() {
        return dep;
    }
}
