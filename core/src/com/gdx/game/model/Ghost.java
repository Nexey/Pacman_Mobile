package com.gdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.gdx.game.entities.Entity;
import com.gdx.game.utilities.DiceFour;
import com.gdx.game.utilities.DiceTwo;
import com.gdx.game.view.TextureFactory;

public class Ghost extends Entity {
    private int color;
    private DiceFour diceFour;
    private DiceTwo diceTwo;
    private int dir;

    public Ghost(Vector2 position, World world, int color) {
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
    public void move() {
        // 50% de chance de changer la direction
        if (diceTwo.getFace() == 1)
            dir = diceFour.getFace();
        updateCoords(dir);
    }
}
