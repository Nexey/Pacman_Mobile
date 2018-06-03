package com.gdx.game.controller.experts;

import com.badlogic.gdx.math.Vector2;
import com.gdx.game.model.GameElement;
import com.gdx.game.model.SuperGom;
import com.gdx.game.model.World;

public class SuperPelletCOR extends AbstractCOR {

    public SuperPelletCOR(AbstractCOR next) {
        super(next);
    }

    @Override
    public boolean canBuild(int elementType) {
        return (elementType == this._SUPERPELLET);
    }

    @Override
    public GameElement construct(World world, int x, int y) {
        return new SuperGom(new Vector2(x, y), world);
    }
}